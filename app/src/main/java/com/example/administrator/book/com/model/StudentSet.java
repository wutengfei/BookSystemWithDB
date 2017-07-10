package com.example.administrator.book.com.model;

/**
 * Created by lsy on 2016/7/14.
 */

import android.content.Context;
import com.example.administrator.book.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StudentSet extends ArrayList<Student> {
    private static StudentSet studentList = null;//定义存储唯一学生集合类的引用变量

    private StudentSet() {//封装构造函数
    }
//静态成员方法，单例模式，

    public static StudentSet getStudentList() {//用静态函数生成集合对象
        studentList = new StudentSet();
        return studentList;
    }

    //从文件读取学生信息，并将读取的信息转换成学术类的对象，存储到容器arraylist中
    public boolean readFile(Context io) {
        try {
            InputStream in = io.getResources().openRawResource(R.raw.student1);

            //把字节流转换成字符流并设置编码为国标码
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "gbk"));
            String temp;
            while ((temp = br.readLine()) != null) {
                //读取txt文件里的内容
                String[] s = temp.split(" ");
                String studentNo = s[0];
                String studentName = s[1];
                String studentMajor = s[2];
                String studentClass = s[3];
                String studentMobile = s[4];
                Student student = new Student(studentNo, studentName, studentMajor, studentClass, studentMobile);
                studentList.add(student);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取失败！");
        }

        System.out.println("读取成功！");
        return true;
    }

}
