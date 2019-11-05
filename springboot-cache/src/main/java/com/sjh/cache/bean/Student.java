package com.sjh.cache.bean;

import java.io.Serializable;
import java.sql.Date;

public class Student implements Serializable {
    private Integer sno;
    private String sname;
    private Date sbrith;
    private String saddr;

    @Override
    public String toString() {
        return "Student{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", sbrith=" + sbrith +
                ", saddr='" + saddr + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(Integer sno, String sname, Date sbrith, String saddr) {
        this.sno = sno;
        this.sname = sname;
        this.sbrith = sbrith;
        this.saddr = saddr;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getSbrith() {
        return sbrith;
    }

    public void setSbrith(Date sbrith) {
        this.sbrith = sbrith;
    }

    public String getSaddr() {
        return saddr;
    }

    public void setSaddr(String saddr) {
        this.saddr = saddr;
    }
}
