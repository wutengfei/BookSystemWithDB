package com.example.administrator.book.com.model;

/**
 * Created by lsy on 2016/7/14.
 */
public class Student {
    //属性
    private String no; //学号
    private String name;
    private String major;
    private String classes;
    private String phone;
    //构造方法
    public Student(String no, String name, String major, String classes, String phone) {
        super();
        this.no = no;
        this.name = name;
        this.major = major;
        this.classes = classes;
        this.phone = phone;
    }
    public Student(){};
    //getter and setter
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }




}
