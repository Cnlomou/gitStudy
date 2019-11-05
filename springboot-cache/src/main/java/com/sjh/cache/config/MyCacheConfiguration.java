package com.sjh.cache.config;

import com.sjh.cache.bean.Student;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

@Configuration
public class MyCacheConfiguration {

//    @Bean("myKeyGenerator")
//    public KeyGenerator keyGenerator(){
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object o, Method method, Object... objects) {
//                return o.getClass().getName()+'-'+method.getName()+'-'+Arrays.asList(objects).toString();
//            }
//        };
//    }
//    @Bean
//    public Student student(){
//        return new Student();
//    }

}
