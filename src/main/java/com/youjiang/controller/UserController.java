package com.youjiang.controller;

import com.alibaba.fastjson.JSON;
import com.youjiang.beans.ResponseObj;
import com.youjiang.beans.User;
import com.youjiang.service.impl.UserServiceImpl;
import com.youjiang.utils.GsonUtils;
import com.youjiang.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    private ResponseObj responseObj;

    @RequestMapping(value = "/register")
    public ModelAndView register(HttpServletRequest request, User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/state/home.jsp");

        if (user == null) {
            modelAndView.addObject("message", "用户信息不能为空!");
        }

        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPwd())) {
            modelAndView.addObject("message", "用户名或密码不能为空!");
        }

        if (null != userService.find(user)) {
            modelAndView.addObject("message", "用户已经存在了！");
        }

        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("message", "用户其他信息填写错误");
        }
        modelAndView.addObject("message", "恭喜您注册成功！");
        request.getSession().setAttribute("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, HttpSession session, User user) {
        Object result;

        if (null == user) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("登录信息不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result; //返回页面
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        //查找用户
        User user1 = userService.find(user);
        if (null == user1) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("未找到该用户");
            result = new GsonUtils().toJson(responseObj);
        } else {
            if (user.getPwd().equals(user1.getPwd())) {
                user1.setPwd(session.getId());
                user1.setNextUrl(request.getContextPath() + "/admin/home");
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg(ResponseObj.OK_STR);
                responseObj.setData(user1);
                session.setAttribute("userInfo", user1);
                /*result = new GsonUtils().toJson(responseObj);*/
                result = JSON.toJSONString(responseObj);
            } else {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("用户密码错误");
                user.setNextUrl("http://www.baidu.com");
                responseObj.setData(user);
                session.setAttribute("userInfo",user);
                result = new GsonUtils().toJson(responseObj);
            }
        }

        return result;
    }

    @RequestMapping(value = "/uploadHeadPic"
            , method = RequestMethod.POST
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object uploadHeadPic(@RequestParam(required = false) MultipartFile file, HttpServletRequest request) {
        //在这里面文件存储的方案一般是：收到文件→获取文件名→在本地存储目录建立防重名文件→写入文件→返回成功信息
        //如果上面的步骤中在结束前任意一步失败，那就直接失败了。
        if (null == file || file.isEmpty()) {
            responseObj = new ResponseObj();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("文件不能为空");
            return new GsonUtils().toJson(responseObj);
        }
        responseObj = new ResponseObj();
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("文件长度为：" + file.getSize());
        return new GsonUtils().toJson(responseObj);
    }
}
