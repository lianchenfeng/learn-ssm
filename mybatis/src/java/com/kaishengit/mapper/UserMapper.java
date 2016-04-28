package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface UserMapper {

    @Select("SELECT * FROM t_user WHERE username=#{username} and password=#{password}")
    User findByUserNameAndPassword(@Param("username") String username,@Param("password") String password);

    Map<String,Object> findMapById(Integer id);

    @Select("select * from t_user where id = #{id}")
    User findById(Integer id);

}
