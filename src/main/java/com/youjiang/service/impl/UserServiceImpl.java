package com.youjiang.service.impl;

import com.youjiang.beans.User;
import com.youjiang.dao.UserDao;
import com.youjiang.exception.*;
import com.youjiang.service.IUserService;
import com.youjiang.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by meng on 2017/5/3.
 */
@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    /*@Resource(name = "IStudentDao")    //注入指定接口对象*/
    private UserDao userDao;


    @Override
    public void add(User user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException,
            UserAireadyExistException, OtherThingsException {
        if (user == null) {
            throw new UserCanNotBeNullException("user can not be null!");
        }

        if (StringUtils.isEmpty(user.getLoginId())) {
            throw new UserNameCanNotBeNullException("user name can not be null!");
        }

        if (StringUtils.isEmpty(user.getPwd())) {
            throw new UserPwdCanNotBeNullException("user password can not be null!");
        }

        /*if (StringUtils.isEmpty(user.getDuty())
                || StringUtils.isEmpty(user.getSex())
                || user.getAge() > 18
                || StringUtils.isEmpty(user.getCellNumber())) {
            //其他综合异常
            throw new OtherThingsException("Some use's base info can not be null");
        }*/

        if (null != userDao.selectById(user.getLoginId())) {
            throw new UserAireadyExistException("注册用户失败，用户已经存在了！");
        }

        int result = 0;
        try {
            result = userDao.insert(user);
        } catch (Exception e) {
            System.out.println("添加用户失败，用户已经存在！");
            throw new OtherThingsException(e);
        }

        if (result > 0) {
            System.out.println("添加用户成功了！");
        }
    }

    public User find(User user) {

        return userDao.selectById(user.getLoginId());
    }

}
