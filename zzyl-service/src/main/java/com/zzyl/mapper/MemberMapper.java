
package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会员Mapper接口
 */
@Mapper
public interface MemberMapper {

    /**
     * 保存会员信息
     * @param member 会员实体类
     * @return 返回保存结果
     */
    int save(Member member);

    /**
     * 根据ID查询会员信息
     * @param id 会员ID
     * @return 返回会员实体类
     */
    Member selectById(Long id);

    /**
     * 更新会员信息
     * @param member 会员实体类
     * @return 返回更新结果
     */
    int update(Member member);

    /**
     * 根据ID删除会员信息
     * @param id 会员ID
     * @return 返回删除结果
     */
    int deleteById(Long id);

    /**
     * 根据openid查询会员信息
     * @param openId 微信openid
     * @return 返回会员实体类
     */
    Member getByOpenid(String openId);

    /**
     * 分页
     * @param page 分页对象
     * @param phone phone
     * @param nickname nickname
     * @return 返回
     */
    IPage<Member> page(Page<Member> page, @Param("phone") String phone, @Param("name") String nickname);
}


