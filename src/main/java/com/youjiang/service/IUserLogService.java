package com.youjiang.service;

import com.youjiang.beans.UserLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by meng on 2017/5/6.
 */
public interface IUserLogService {
    void add(HttpServletRequest request) throws Exception;

    List<UserLog> findAll(int pageNum,int pageSize);

    int getAllCount();
}
