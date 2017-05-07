package com.youjiang.service.impl;

import com.youjiang.beans.User;
import com.youjiang.exception.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class UserServiceImplTest {

    @Autowired
    @Qualifier(value = "userService")
    private UserServiceImpl userService;

    @Test
    public void add() throws Exception {
        User user = new User("qianli", "男", "pw1376472001", "123456", "运营经理", 29, "13767472001", "haha", true);
        try {
            userService.add(user);
        } catch (UserCanNotBeNullException e) {
            e.printStackTrace();
        } catch (UserNameCanNotBeNullException e) {
            e.printStackTrace();
        } catch (UserPwdCanNotBeNullException e) {
            e.printStackTrace();
        } catch (UserAireadyExistException e) {
            e.printStackTrace();
        } catch (OtherThingsException e) {
            e.printStackTrace();
        }
    }

}