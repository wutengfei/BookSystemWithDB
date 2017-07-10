package com.example.administrator.book.com.control;

import android.content.Context;
import com.example.administrator.book.com.model.DBAdapter;
import com.example.administrator.book.com.model.StudentSet;
import com.example.administrator.book.com.model.Student;

/**
 * Created by Administrator on 2016/7/16.
 */
public class StudentControl {
    private static DBAdapter dbAdapter;
    private static StudentSet studentSet;
    private Context context;

    public StudentControl(Context context) {
        this.context = context;
        studentSet = StudentSet.getStudentList();
        dbAdapter = new DBAdapter(context);
        dbAdapter.open();
    }

    public void addStudent(Student s1) {
        dbAdapter.insertStudent(s1);
    }

    public void saveAll() {
        studentSet.readFile(context);
        //使用事务可以极大地加快插入速度，不再是一条一条插入，而是暂存在缓存区最后一起插入数据库
        dbAdapter.db.beginTransaction();//开启事务
        for (int i = 0; i < studentSet.size(); i++) {
            dbAdapter.insertStudent(studentSet.get(i));
        }
        dbAdapter.db.setTransactionSuccessful();// 设置事务标志为成功，当结束事务时就会提交事务
        dbAdapter.db.endTransaction();// 结束事务
    }

    public void deleteAll() {
        dbAdapter.deleteAll();
    }

    public boolean deleteStudent(String no) {
        Student s[] = dbAdapter.queryStudent(no);
        if (s != null) {
            dbAdapter.deleteStudent(no);
            return true;
        } else {
            return false;
        }
    }

    public void updateStudent(Student e) {
        String no = e.getNo();
        Student s[] = dbAdapter.queryStudent(no);
        if (s != null) {
            dbAdapter.updateStudent(no, e);
        }
    }

    public Student[] queryStudent(String no) {
        return dbAdapter.queryStudent(no);
    }

    public Student[] getAllStudent() {
        return dbAdapter.getAllStu();
    }
}
