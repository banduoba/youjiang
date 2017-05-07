package com.youjiang.dao;

import com.youjiang.beans.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by meng on 2017/5/3.
 */
public interface UserDao extends Dao<User> {

    int insert(User user);

    int delete(User user);

    int update(User user);

    User selectById(Serializable Id);

    List<User> selectAll();

}
