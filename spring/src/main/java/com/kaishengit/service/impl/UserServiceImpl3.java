package com.kaishengit.service.impl;


import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Value;


import javax.inject.Inject;
import javax.inject.Named;

@Named("userService")
public class UserServiceImpl3 implements UserService{

    @Inject
    private UserDao userDao;

    @Value("${qiniu.sk}")
    private String qiniuSk;

    public void save() {
        System.out.println(qiniuSk);
        userDao.count();
    }
}
