package com.sjh.springboot.controller;

import com.sjh.springboot.entity.User;
import com.sjh.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class JpaTestController {
    @Autowired
    UserRepository userRepository;


    @ResponseBody
    @GetMapping("/jpatest")
    public User japTest(){
        User user=userRepository.save(new User(1,"sjh","1246269795@qq.com"));
        return user;
    }
}
