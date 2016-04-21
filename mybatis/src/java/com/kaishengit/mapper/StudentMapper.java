package com.kaishengit.mapper;


import com.kaishengit.pojo.Student;

import java.util.List;

public interface StudentMapper {

    void save(Student student);

    void update(Student student);

    void del(Integer id);

    Student findById(Integer id);

    List<Student> findAll();
}
