package com.sjh.springboot.template;

public interface TemplateInterface<T> {
    String getClassName(T obj);
}
