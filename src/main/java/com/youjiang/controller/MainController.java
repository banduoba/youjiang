package com.youjiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by meng on 2017/5/3.
 */
@Controller
@RequestMapping(value = "/admin")
public class MainController {

    @RequestMapping("/login")
    public String index() {
        return "/user/login.jsp";
    }

    @RequestMapping(value = "/home")
    public String admin() {
        return "/WEB-INF/admin/index.jsp";
    }

    @RequestMapping("/listLog")
    public String listLog() {
        return "/WEB-INF/admin/list_log.jsp";
    }

}
