package com.kaishengit.service.impl;


import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Properties;


public class UserServiceImpl implements UserService{

    private UserDao userDao;
    private String name;
    private int age;
    private List<String> list;
    private Map<String,Object> maps;
    private Properties properties;

    public void setMyUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void save() {
        System.out.println("----------------");
        userDao.save();
        System.out.println("----------------");
    }
}
