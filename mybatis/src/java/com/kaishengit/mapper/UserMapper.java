package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {

    User findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

    Map<String,Object> findMapById(Integer id);

    User findById(Integer id);
}
