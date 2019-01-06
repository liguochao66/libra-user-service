package com.struggle.libra.user.service.mybatis;

import com.struggle.libra.user.service.entities.model.user.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository(value = "userDao")
public interface UserDao {

    /**
     * 新增用户信息到数据存储系统
     *
     * @param info 用户信息
     * @return 新增操作所影响的行数
     */
    int insert(@Param("info") UserInfo info);

    /**
     * 更新用户信息到数据存储系统
     *
     * @param info 用户信息
     * @return 更新操作所影响的行数
     */
    int update(@Param("info") UserInfo info);

    /**
     * 从数据存储系统查询用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserInfo getUserInfo(@Param("id") String id);
}