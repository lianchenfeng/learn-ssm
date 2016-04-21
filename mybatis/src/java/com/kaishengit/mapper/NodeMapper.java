package com.kaishengit.mapper;


import com.kaishengit.pojo.Node;

import java.util.Map;

public interface NodeMapper {

    void save(Node node);

    void saveWithMap(Map<String,Object> map);

    Node findById(Integer id);

    Node findByIdNew(Integer id);
}
