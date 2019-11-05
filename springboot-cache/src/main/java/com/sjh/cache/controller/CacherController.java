package com.sjh.cache.controller;

import com.sjh.cache.bean.Student;
import com.sjh.cache.service.StudentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class CacherController {

    @Autowired
    private StudentManagerService studentManagerService;


    @GetMapping("/stu/{id}")
    @ResponseBody
    public Student getStudent(@PathVariable("id") Integer id){
        return studentManagerService.getStudentById(id);
    }

    @GetMapping("/stu")
    @ResponseBody
    public Student getStudent(Integer sno,String sname,String sbrith,String saddr){
        Student student=new Student();
        student.setSno(sno);
        student.setSname(sname);
        try {
            student.setSbrith(new Date(new SimpleDateFormat("yyy/MM/dd").parse(sbrith).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        student.setSaddr(saddr);
        return  studentManagerService.updateStudent(student);
    }

    @GetMapping("/studel/{id}")
    @ResponseBody
    public String delStudent(@PathVariable("id") Integer id){
        try {
            studentManagerService.deleteStudentById(id);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
