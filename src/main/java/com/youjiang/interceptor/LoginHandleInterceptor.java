package com.youjiang.interceptor;

import com.youjiang.service.impl.UserLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by meng on 2017/5/5.
 */
public class LoginHandleInterceptor extends HandlerInterceptorAdapter {
    String NO_INTERCEPTOR_PATH = ".*/((login)|(reg)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*";

    @Autowired
    private UserLogServiceImpl service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (!path.matches(".*/((static)|(login)|(register)).*")) service.add(request);
        if (path.matches(NO_INTERCEPTOR_PATH)) {    //匹配正则表达式的不拦截
            return true;
        } else {    //不匹配的进行处理
            try {
                if (request.getSession().getAttribute("userInfo") == null) { //session中是否存在用户信息，不存在则是未登录状态
                    response.sendRedirect(request.getContextPath() + "/admin/login");
                    return false;
                }
            } catch (IOException e) {
                response.sendRedirect(request.getContextPath() + "/admin/login");
                e.printStackTrace();
                return false;
            }
        }
        return true;    //默认是不拦截···当然具体的还看一些需求设计啊  之类的
    }

}
