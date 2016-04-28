package com.kaishengit.dao.impl;


import com.kaishengit.dao.UserDao;

public class UserDaoImpl2 implements UserDao {

    public void init(){
        System.out.println("UserDao init......");
    }

    public void destory(){

        System.out.println("UserDao destory......");
    }

    public void save(){

        System.out.println("UserDao save@.@.@.@.@.@.");
    }

    public int count() {
        return 200;
    }
}
