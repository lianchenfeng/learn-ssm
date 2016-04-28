package com.kaishengit.pojo;

import java.io.Serializable;


public class Node implements Serializable {

    private Integer id;
    private String nodename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }
}
