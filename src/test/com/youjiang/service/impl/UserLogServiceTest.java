package com.youjiang.service.impl;

import com.youjiang.beans.UserLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by meng on 2017/5/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class UserLogServiceTest {

    @Autowired
    @Qualifier(value = "userLogService")
    private UserLogServiceImpl userLogService;

    @Test
    public void add() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
        List<UserLog> logs = userLogService.findAll(2, 10);
        for (UserLog log : logs) {
            System.out.println(log);
        }
    }

}