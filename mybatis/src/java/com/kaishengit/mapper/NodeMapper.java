package com.kaishengit.mapper;


import com.kaishengit.pojo.Node;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface NodeMapper {

    @Insert("INSERT into t_node(nodename) VALUES (#{nodename})")
    void save(Node node);

    void saveWithMap(Map<String,Object> map);

    Node findById(Integer id);

    @Select("SELECT * FROM t_node WHERE id = #{id}")
    Node findByIdNew(Integer id);

    @Delete("delect from t_node where id = #{id}")
    void del(Integer id);

    @Update("update t_node set nodename = #{nodename} where id = #{id}")
    void update(Node node);
}
