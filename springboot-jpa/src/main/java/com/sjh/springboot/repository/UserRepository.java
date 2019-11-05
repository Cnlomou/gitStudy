package com.sjh.springboot.repository;

import com.sjh.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
}
