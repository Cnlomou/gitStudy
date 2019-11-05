package com.sjh.springboot;

public class ExtendInterfaceImpl implements ExtendInterface {
    @Override
    public void printf() {
        System.out.println(ExtendInterfaceImpl.class.getSimpleName());
    }

    @Override
    public <T> String toString(T obj) {
        System.out.println("这里调用了toString方法");
        return null;
    }
}
