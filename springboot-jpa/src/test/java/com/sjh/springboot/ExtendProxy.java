package com.sjh.springboot;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//动态代理工具类
public class ExtendProxy {
    private static ExtendInterfaceImpl extendInterface;
    public static  ExtendInterface getProxy(ExtendInterfaceImpl obj){
        ExtendProxy.extendInterface=extendInterface=obj;
        return (ExtendInterface) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), ExtendInterfaceImpl.class.getInterfaces(),
            (o,method,objects)->{//1.8新规范
                Object object=method.invoke(extendInterface,objects);
                System.out.println(o.getClass().getName()+"invoke:"+method.getName());
                return object;
            });
    }
}
