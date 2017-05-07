package com.youjiang.service;

import com.youjiang.beans.User;

/**
 * Created by meng on 2017/5/3.
 */
public interface IUserService extends BaseService<User> {

    void add(User user) throws Exception;

    User find(User user) throws Exception;
}
