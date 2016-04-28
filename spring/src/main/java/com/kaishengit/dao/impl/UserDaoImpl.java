package com.kaishengit.dao.impl;


import com.kaishengit.dao.UserDao;

import javax.inject.Named;


@Named("userDao")
public class UserDaoImpl implements UserDao {

    public UserDaoImpl(){

        System.out.println("create UserDao ......");
    }

    public void save(){

        System.out.println("UserDao save......");
    }

    public int count() {

        System.out.println("count method...");
      /*  if(1==1){
            throw new RuntimeException("haha");
        }*/
        return 100;
    }

}
