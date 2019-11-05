package com.sjh.cache.service;

import com.sjh.cache.bean.Student;

public interface StudentManagerService {

    Student getStudentById(Integer id );

    Student updateStudent(Student student);
    void deleteStudentById(Integer id);
}
