package com.sjh.cache;

import com.sjh.cache.bean.Student;
import com.sjh.cache.date.StatThread;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringbootCacheApplicationTests {
    Logger logger= LoggerFactory.getLogger(SpringbootCacheApplicationTests.class);


    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ObjectProvider<Student> objectProvider;




    @Test
    public void reflectTest(){
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        Class<?> clazz=SpringbootCacheApplicationTests.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f : declaredFields){
            if(f.getName().equals("objectProvider")){
                System.out.println(f.getType().getName());
                ParameterizedType genericType = (ParameterizedType) f.getGenericType();
                String typeName=genericType.getActualTypeArguments()[0].getTypeName();
                System.out.println(typeName);
                try {
                    System.out.println("url:"+getUrl(typeName));
                    Object object=classLoader.loadClass(typeName).getDeclaredConstructor().newInstance();
                    System.out.println("class:"+object);
                    logger.info(classLoader.getResource(getUrl(typeName)+".class").getPath());
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String getUrl(String typeName){
        if(typeName==null||typeName=="")
            throw new RuntimeException("非法的类名");
        if(typeName.startsWith("class")){
            typeName=typeName.substring("class".length());
            System.out.println(typeName);
            typeName=typeName.replace('.','/');
            return typeName;
        }
        return typeName.replace('.','/');
    }

    /*
        ObjectProvider 能从ioc容器中找到对应的bean对象，若是没有返回null
        若是通常的依赖注入，若没有该bean对象，最会抛出异常
     */
    @Test
    void objectProvicderTest(){
        if(objectProvider.getIfAvailable()==null)
            System.out.println("工厂中没有该bean对象");
    }

    @Test
    void redisTest(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.append("msg","hhh");
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        listOperations.leftPush("zhan","fbq");
        //ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream());
    }






    @Test
    void contextLoads() throws ParseException {
        DateFormat dateFormat1=new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() );
        calendar.add(Calendar.HOUR_OF_DAY,2);
        String addAgter=dateFormat1.format(calendar.getTime());
        logger.info(addAgter);
        logger.info(Boolean.toString(calendar.after(Calendar.getInstance())));
    }


    /*
        wait与notify方法只能在同步块种使用，
     */
    @Test
    public void threadTest(){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,3);
        StatThread statThread=new StatThread(calendar);
        statThread.start();
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
