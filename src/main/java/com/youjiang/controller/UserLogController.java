package com.youjiang.controller;

import com.alibaba.fastjson.JSON;
import com.youjiang.beans.ResponseList;
import com.youjiang.beans.ResponseObj;
import com.youjiang.beans.UserLog;
import com.youjiang.service.impl.UserLogServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by meng on 2017/5/6.
 */
@Controller
@RequestMapping(value = "/userLog")
public class UserLogController {

    @Autowired
    private UserLogServiceImpl service;

    @RequestMapping(value = "/findList",produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object findLog(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize) {
        if (pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize <= 0) {
            pageSize = 10;
        }

        int totalNum;
        ResponseList<UserLog> responseList = new ResponseList<>();

        try {
            totalNum = service.getAllCount();
            totalNum = totalNum % pageSize > 0 ? totalNum / pageSize + 1 : totalNum / pageSize;

            List<UserLog> results = service.findAll(pageNum, pageSize);

            responseList.setPageNum(pageNum);
            responseList.setTotalNum(totalNum);
            responseList.setPageSize(pageSize);

            if (results == null || results.size() == 0) {
                responseList.setCode(ResponseObj.EMPTY);
                responseList.setMsg("查询数据为空！");

                return JSON.toJSONString(responseList);
            }

            responseList.setCode(ResponseObj.OK);
            responseList.setMsg("用户访问日志查询成功");
            responseList.setData(results);

            return JSON.toJSONString(responseList);
        } catch (Exception e) {

            e.printStackTrace();
            responseList.setCode(ResponseObj.FAILED);
            responseList.setMsg("用户访问日志查询失败");
            return JSON.toJSONString(responseList);
        }

    }
}
