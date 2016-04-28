package com.kaishengit.service.impl;


import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;

public class UserServiceImpl2 implements UserService{

    private UserDao userDao;

    public UserServiceImpl2(UserDao userDao){
        this.userDao = userDao;
    }

    public void save() {
        userDao.save();
        System.out.println("User Service 2~!!!");
    }
}
