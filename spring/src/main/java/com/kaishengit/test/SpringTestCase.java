package com.kaishengit.test;


import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestCase {

    private ApplicationContext context;

    @Before
    public void setUp(){

        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testGetBean(){

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDao userDao = (UserDao) context.getBean("userDao");

        userDao.save();
        userDao.save();
        userDao.save();

        Assert.assertNotNull(userDao);

    }

    @Test
    public void testScope(){
        //交给Spring管理的类，将会变成单例类
        //改变配置文件中的scope属性，由默认的singleton修改为prototype

        UserDao userDao = (UserDao) context.getBean("userDao");
        UserDao userDao2 = (UserDao) context.getBean("userDao");

        System.out.println(userDao == userDao2);
    }

    @Test
    public void testLazyLoad(){

        //交给Spring管理的bean，默认会在容器加载时创建对象
        //修改配置文件中的lazy_init属性为true,则对象会进行懒加载
        //如果scope的值为propotype,则对象也是懒加载操作
        UserDao userDao = (UserDao) context.getBean("userDao");

    }

    @Test
     public void testIoc(){
        UserService userService= (UserService) context.getBean("userService");
        userService.save();
    }

    @Test
    public void testAop(){
        UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.save();
    }
}
