package com.youjiang.dao;

import com.youjiang.beans.User;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class) //spring的单元测试
@ContextConfiguration({"classpath:spring/spring-*.xml"})    //上下文配置
public class UserDaoTest {

    @Setter
    @Autowired
    private UserDao userDao;

    /**
     * 添加用户的单元测试，添加成功与否会有对应的提示。
     * 当然按照我这个配置一般会正确，如果说出错就需要你一步一步的看错误的提示代码了。
     * 添加同样的LoginId的用户会添加失败，因为在上面把LoginId作为了数据库表的主键。
     */
    @Test
    public void insert() throws Exception {
        User user = new User();
        user.setLoginId("pc1479523768");
        user.setName("雨下一s整8夜");
        user.setPwd("123456");
        user.setAge(22);
        user.setSex("女");


        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加用户失败");
        }
        if (result > 0)
            System.out.println("添加用户成功");
    }


    @Test
    public void delete() throws Exception {
        User user = new User();
        user.setLoginId("pc147852369");
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除用户失败");
        }
        if (result>0)
            System.out.println("删除用户成功");
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void selectById() throws Exception {
    }

}