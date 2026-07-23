package com.zzyl.vo;
import com.zzyl.constant.SuperConstant;
import com.zzyl.utils.EmptyUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 *  自定认证用户
 */
@Data
@NoArgsConstructor
public class UserAuth implements UserDetails {

    private String id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限内置
     */
    private Collection<SimpleGrantedAuthority> authorities;

    /**
     * 用户类型（00系统用户）
     */
    private String userType;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门编号【当前】
     */
    private String deptNo;

    /**
     * 职位编号【当前】
     */
    private String postNo;

    /**
     * 数据状态【0正常 1停用】，用于 isEnabled 判定
     */
    private String dataState;

    /**
     * 是否需要修改初始密码（使用系统默认口令登录时为 true，前端据此强制改密）
     */
    private Boolean needResetPwd = false;

    public UserAuth(UserVo userVo) {
        this.setId(userVo.getId().toString());
        this.setUsername(userVo.getUsername());
        this.setPassword(userVo.getPassword());
        if (!EmptyUtil.isNullOrEmpty(userVo.getResourceRequestPaths())) {
            authorities = new ArrayList<>();
            userVo.getResourceRequestPaths().forEach(resourceRequestPath -> authorities.add(new SimpleGrantedAuthority(resourceRequestPath)));
        }
        this.setUserType(userVo.getUserType());
        this.setNickName(userVo.getNickName());
        this.setEmail(userVo.getEmail());
        this.setRealName(userVo.getRealName());
        this.setMobile(userVo.getMobile());
        this.setSex(userVo.getSex());
        this.setCreateTime(userVo.getCreateTime());
        this.setCreateBy(userVo.getCreateBy());
        this.setUpdateTime(userVo.getUpdateTime());
        this.setUpdateBy(userVo.getUpdateBy());
        this.setRemark(userVo.getRemark());
        this.setDeptNo(userVo.getDeptNo());
        this.setPostNo(userVo.getPostNo());
        this.setDataState(userVo.getDataState());
        // 修改点：拷贝默认口令标记，供登录接口返回前端以强制改密
        this.setNeedResetPwd(userVo.getNeedResetPwd() != null ? userVo.getNeedResetPwd() : false);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 修改点：原为恒返回 true，导致被停用(data_state=1)的账号仍可绕过状态校验。
        // 虽然登录 SQL 已用 data_state='0' 过滤停用账号，但此处作为防御纵深，
        // 严格按 dataState 判定账号是否启用，避免任何绕过登录查询的认证路径放行停用账号。
        return SuperConstant.DATA_STATE_0.equals(this.dataState);
    }
}
