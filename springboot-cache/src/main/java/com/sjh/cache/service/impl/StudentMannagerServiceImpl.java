package com.sjh.cache.service.impl;

import com.sjh.cache.bean.Student;
import com.sjh.cache.mapper.StudentMapper;
import com.sjh.cache.service.StudentManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentMannagerServiceImpl implements StudentManagerService {
    Logger logger= LoggerFactory.getLogger(StudentMannagerServiceImpl.class);

    @Resource
    StudentMapper studentMapper;


    @Override
    @Cacheable(cacheNames = {"stu"},key = "#id")
    public Student getStudentById(Integer id) {
        Student student=studentMapper.getStudentById(id);
        logger.info("从数据库中查得"+student.toString());
        return student;
    }

    @CachePut(cacheNames = {"stu"},key = "#result.sno")
    public Student updateStudent(Student student){
        studentMapper.updateStudent(student);
        return student;
    }

    @CacheEvict(cacheNames = {"stu"},key ="#id" )
    public void deleteStudentById(Integer id){
        logger.info("执行了删除操作");
        //studentMapper.deleteStudentById(id);
    }
}
