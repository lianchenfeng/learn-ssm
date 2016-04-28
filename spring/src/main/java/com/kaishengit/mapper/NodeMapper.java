package com.kaishengit.mapper;


import com.kaishengit.pojo.Node;


public interface NodeMapper {

    void save(Node node);

    Node findById(Integer id);

}
