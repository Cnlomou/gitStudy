package com.sjh.springboot;

import com.sjh.springboot.template.MyTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class SpringbootJpaApplicationTests {

    @Autowired
    ApplicationContext ioc;
    @Test
    void contextLoads() {
        ExtendInterface extendInterface=ExtendProxy.getProxy(new ExtendInterfaceImpl());
        extendInterface.printf();
        extendInterface.toString(Arrays.asList("obj1","obj2","obj3"));
        extendInterface.hashCode();
    }

    @Test
    public void templateTest(){
        Method[] methods=MyTemplate.class.getDeclaredMethods();
        Parameter[] parameters=null;
        System.out.println(MyTemplate.class.getName()+"中有"+methods.length+"个方法");
        for(Method method:methods){
            System.out.println("method:  "+method.getName());
            parameters=method.getParameters();
            if(parameters.length>0)
                for(Parameter parameter: parameters){
                    if(parameter.getType()==String.class)
                        System.out.println(method.getName()+"------");
                }
        }


    }

    @Test
    public void propertiesTest(){
        WebMvcProperties webMvcProperties=ioc.getBean(WebMvcProperties.class);
        if(webMvcProperties!=null)
            System.out.println("webMvcProperties在ioc容器中");
        Set<String> set=new HashSet<>();
    }

}
