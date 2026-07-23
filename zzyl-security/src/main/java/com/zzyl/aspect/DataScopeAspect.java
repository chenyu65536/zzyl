package com.zzyl.aspect;

import cn.hutool.json.JSONUtil;
import com.zzyl.base.BaseDto;
import com.zzyl.base.DataScope;
import com.zzyl.utils.NoProcessing;
import com.zzyl.utils.StringUtils;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.RoleVo;
import com.zzyl.vo.UserVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * DataScopeAspect
 * @author itheima
 **/
@Slf4j
@Aspect
@Component
public class DataScopeAspect {

    /**
     * 修改点：部门编号白名单校验（仅允许纯数字）。
     * 数据权限 SQL 由字符串拼接生成并经 MyBatis ${} 注入最终 SQL，
     * 虽然 deptNo 来源于服务端登录态而非直接的请求参数，
     * 但若用户数据被污染（如注册/导入环节写入恶意 deptNo），仍可能造成二次 SQL 注入。
     * 拼接前强制校验为纯数字，可彻底阻断该注入面。
     */
    private static final Pattern DEPT_NO_PATTERN = Pattern.compile("^\\d{1,20}$");

    /**
     * 自定数据权限
     */
    public static final String DATA_SCOPE_CUSTOM = "0";

    /**
     * 仅本人数据权限
     */
    public static final String DATA_SCOPE_SELF = "1";

    /**
     * 部门及以下数据权限
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "2";

    /**
     * 部门数据权限
     */
    public static final String DATA_SCOPE_DEPT = "3";

    /**
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "4";


    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    // 配置织入点
    @Pointcut("@annotation(com.zzyl.base.DataScope)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) throws Throwable {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint) {
        // 获得注解
        DataScope controllerDataScope = getAnnotationLog(joinPoint);
        if (controllerDataScope == null) {
            return;
        }
        // 获取当前的用户
        String subject = UserThreadLocal.getSubject();
        UserVo userVo = JSONUtil.toBean(subject, UserVo.class);
        // 如果是超级管理员，则不过滤数据
        if (StringUtils.isNotNull(userVo) && !userVo.getUsername().equals("admin")) {
            dataScopeFilter(joinPoint, userVo, controllerDataScope.deptAlias(),
                    controllerDataScope.userAlias());
        }
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     * @param user      用户
     * @param userAlias 别名
     */
    public static void dataScopeFilter(JoinPoint joinPoint, UserVo user, String deptAlias, String userAlias) {
        // 修改点：System.out.println 替换为 SLF4J 日志（规范要求，且避免生产环境污染标准输出）
        log.debug("数据权限过滤开始，userId={}", user.getId());
        StringBuilder sqlString = new StringBuilder();

        for (RoleVo role : user.getRoleList()) {
            String dataScope = role.getDataScope();//拥有的数据权限
            // 如果是全部数据权限，则不过滤数据
            if (DATA_SCOPE_ALL.equals(dataScope)) {
                sqlString = new StringBuilder();
                break;
                // 如果是自定数据权限，则只查看自己的数据
            } else if (DATA_SCOPE_CUSTOM.equals(dataScope)) {
                // role.getId() 为 Long 类型，拼接后必然是数字，无注入风险
                sqlString.append(" OR dept_no IN ( SELECT dept_no FROM sys_role_dept WHERE role_id = " + role.getId() + " ) ");
                // 如果是部门数据权限，则只查看本部门数据
            } else if (DATA_SCOPE_DEPT.equals(dataScope)) {
                // 修改点：deptNo 为字符串，拼接进 ${dataScope} 前先做纯数字白名单校验，阻断二次 SQL 注入
                sqlString.append(" OR dept_no = " + safeDeptNo(user.getDeptNo()) + " ");
                // 如果是部门及以下数据权限，则查看本部门以及下级数据
            } else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope)) {
                // 修改点：同上，deptNo 参与两处拼接（等值 + like 前缀），均先经白名单校验
                String safeDeptNo = safeDeptNo(user.getDeptNo());
                String str = NoProcessing.processString(safeDeptNo) + "%";
                sqlString.append(
                        " OR dept_no IN ( SELECT dept_no FROM sys_dept WHERE dept_no = " + safeDeptNo + " or dept_no like  '" + str + "')");
                // 如果是仅本人数据权限，则只查看本人的数据
            } else if (DATA_SCOPE_SELF.equals(dataScope)) {//  or u.user_id = 登录用户id
                // user.getId() 为 Long 类型，无注入风险
                sqlString.append(" OR create_by = " + user.getId());
            }
        }

        if (StringUtils.isNotBlank(sqlString.toString())) {
            Object params = joinPoint.getArgs()[0];  //获取第一个参数  要求一定得是一个BaseEntity  在Service执行前 则就已经加上了 Sql   or u.user_id = 登录用户id
            if (StringUtils.isNotNull(params) && params instanceof BaseDto) {
                BaseDto baseDto = (BaseDto) params;
                baseDto.getParams().put(DATA_SCOPE, "(" + sqlString.substring(4) + ")");
            }
        }
    }

    /**
     * 修改点：新增部门编号安全校验方法。
     * 校验 deptNo 为纯数字（1~20 位），不通过则抛出异常终止本次查询，
     * 防止被污染的部门编号经字符串拼接 + MyBatis ${} 造成 SQL 注入。
     *
     * @param deptNo 部门编号
     * @return 校验通过的部门编号原值
     */
    private static String safeDeptNo(String deptNo) {
        if (deptNo == null || !DEPT_NO_PATTERN.matcher(deptNo).matches()) {
            log.error("数据权限过滤检测到非法部门编号，已拒绝执行，deptNo={}", deptNo);
            throw new IllegalStateException("非法的部门编号，数据权限过滤已终止");
        }
        return deptNo;
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataScope getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }
}
