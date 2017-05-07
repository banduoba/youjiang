package com.youjiang.dao;

import com.youjiang.beans.UserLog;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;


public interface UserLogDao extends Dao<UserLog> {
    int insert(UserLog userLog);

    UserLog findOneById(Serializable Id);

    List<UserLog> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    int countAll();
}
