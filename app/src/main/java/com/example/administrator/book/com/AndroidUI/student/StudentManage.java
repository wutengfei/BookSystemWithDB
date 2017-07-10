package com.example.administrator.book.com.AndroidUI.student;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.book.R;
import com.example.administrator.book.com.control.StudentControl;
import com.example.administrator.book.com.model.Student;

public class StudentManage extends AppCompatActivity implements View.OnClickListener {
    private EditText edit;
    private StudentControl studentControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manage);
        studentControl = new StudentControl(StudentManage.this);
    }

    @Override
    public void onClick(View v) {
        //插入
        if (v.getId() == R.id.button6) {
            Intent intent = new Intent();
            intent.setClass(StudentManage.this, StudentInsert.class);
            this.startActivity(intent);
        }
        //删除
        if (v.getId() == R.id.button5) {
            edit = new EditText(StudentManage.this);
            new AlertDialog.Builder(StudentManage.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setTitle("请输入")
                    .setMessage("学号")
                    .setView(edit)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String no = edit.getText().toString();
                            if (studentControl.deleteStudent(no))
                                Toast.makeText(StudentManage.this, "删除成功", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(StudentManage.this, "无这个人", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
        //更新
        if (v.getId() == R.id.button9) {
            Intent intent = new Intent();
            intent.setClass(StudentManage.this, StudentUpdate.class);
            this.startActivity(intent);
        }
        //查询
        if (v.getId() == R.id.button8) {
            edit = new EditText(StudentManage.this);
            new AlertDialog.Builder(StudentManage.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setTitle("请输入")
                    .setMessage("学号")
                    .setView(edit)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String na = edit.getText().toString();
                            Student s[] = studentControl.queryStudent(na);
                            if (s != null)
                                new AlertDialog.Builder(StudentManage.this)
                                        .setTitle("结果为")
                                        .setMessage(s[0].getNo() + "\n" +
                                                s[0].getName() + "\n" +
                                                s[0].getMajor() + "\n" +
                                                s[0].getClasses() + "\n" +
                                                s[0].getPhone())
                                        .show();
                            else
                                Toast.makeText(StudentManage.this, "没有符合的记录", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
        //全部保存(读文件)
        if (v.getId() == R.id.buttonSaveAll) {
            studentControl.saveAll();
            Toast.makeText(StudentManage.this, "保存成功", Toast.LENGTH_SHORT).show();
        }
        //全部删除
        if (v.getId() == R.id.buttonDeleteAll) {
            studentControl.deleteAll();
            Toast.makeText(StudentManage.this, "删除成功", Toast.LENGTH_SHORT).show();
        }
        //输出全部信息
        if (v.getId() == R.id.button7) {
            Intent intent = new Intent();
            intent.setClass(StudentManage.this, StudentShow.class);
            this.startActivity(intent);
        }
    }
}
