package com.example.administrator.book.com.AndroidUI.student;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.book.R;
import com.example.administrator.book.com.control.StudentControl;
import com.example.administrator.book.com.model.Student;

public class StudentInsert extends AppCompatActivity implements View.OnClickListener {
    //定义控件对象
    private EditText name;
    private EditText no;
    private EditText major;
    private EditText classes;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_insert);
        //绑定控件
        no = (EditText) findViewById(R.id.etNo);
        name = (EditText) findViewById(R.id.etName);
        classes = (EditText) findViewById(R.id.etClass);
        major = (EditText) findViewById(R.id.etMajor);
        phone = (EditText) findViewById(R.id.etMobile);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.insert) {
            //获取EditText控件上用户输入的文本
            String studentNo = no.getText().toString().trim();
            String studentName = name.getText().toString().trim();
            String studentclasses = classes.getText().toString().trim();
            String studentMajor = major.getText().toString().trim();
            String studentMobile = phone.getText().toString().trim();
            // 封装成student对象
            Student student = new Student(studentNo, studentName, studentMajor, studentclasses, studentMobile);

            StudentControl studentControl = new StudentControl(StudentInsert.this);

            if (studentName.equals("") || studentNo.equals("") || studentMajor.equals("") ||
                    studentclasses.equals("") || studentMobile.equals("")) {
                Toast.makeText(StudentInsert.this, "请填写完整", Toast.LENGTH_SHORT).show();
            } else {
                if (studentControl.queryStudent(studentNo) != null) {
                    Toast.makeText(StudentInsert.this, "该学生已经存在", Toast.LENGTH_SHORT).show();
                } else {
                    studentControl.addStudent(student);
                    no.setText("");
                    name.setText("");
                    major.setText("");
                    classes.setText("");
                    phone.setText("");
                    buildDialog();
                }
            }
        }
    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(StudentInsert.this);
        builder.setTitle("插入成功，是否继续插入");
        builder.setNegativeButton("返回上一页", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        builder.setPositiveButton("继续插入", null);
        builder.show();
    }
}
