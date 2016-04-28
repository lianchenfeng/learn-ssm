package com.kaishengit.mapper;

import com.kaishengit.pojo.Topic;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@CacheNamespace
public interface TopicMapper {

    Topic findById(Integer id);

    @Select("select * from t_topic where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "createtime",property = "createtime"),
            @Result(column = "userid",property = "userid"),
            @Result(column = "nodeid",property = "nodeid"),
            @Result(column = "viewnum",property = "viewnum"),
            @Result(column = "replynum",property = "replynum"),
            @Result(column = "likenum",property = "likenum"),
            @Result(column = "favnum",property = "favnum"),
            @Result(column = "replytime",property = "replytime"),
            @Result(column = "userid",property = "user",one = @One(select = "com.kaishengit.mapper.UserMapper.findById")),
            @Result(column = "nodeid",property = "node",one = @One(select = "com.kaishengit.mapper.NodeMapper.findByIdNew"))
    })
    @Options(useCache = false)
    Topic findByIdNew(Integer id);

    List<Topic> findByParam(Map<String,Object> param);

    List<Topic> findByNodeId(List<Integer> nodeIds);


}
