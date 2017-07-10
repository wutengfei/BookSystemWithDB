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

public class StudentUpdate extends AppCompatActivity implements View.OnClickListener {
    private EditText major;
    private EditText phone;
    private EditText no;
    private EditText name;
    private EditText classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);
        major = (EditText) findViewById(R.id.etupdatamajor);
        phone = (EditText) findViewById(R.id.etupdatamobile);
        no = (EditText) findViewById(R.id.etupdatano);
        classes = (EditText) findViewById(R.id.etupdataclass);
        name = (EditText) findViewById(R.id.etupdataname);
    }

    @Override
    public void onClick(View v) {

        String studentNo = no.getText().toString().trim();
        String studentName = name.getText().toString().trim();
        String studentMajor = major.getText().toString().trim();
        String studentClass = classes.getText().toString().trim();
        String studentPhone = phone.getText().toString().trim();

        Student s = new Student(studentNo, studentName, studentMajor, studentClass, studentPhone);
        StudentControl stucontrol = new StudentControl(StudentUpdate.this);

        if (studentNo.equals("") || studentMajor.equals("") || studentPhone.equals("") || studentClass.equals("")
                || studentName.equals("")) {
            new AlertDialog.Builder(StudentUpdate.this).setMessage("不能有空行").show();
        } else {
            if (stucontrol.queryStudent(studentNo) != null) {
                stucontrol.updateStudent(s);
                no.setText("");
                major.setText("");
                phone.setText("");
                name.setText("");
                classes.setText("");
                buildDialog();
            } else {
                Toast.makeText(StudentUpdate.this, "该学生不存在", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(StudentUpdate.this,
                android.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle("更新成功，是否继续更新");
        builder.setNegativeButton("返回上一页", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        builder.setPositiveButton("继续更新", null);
        builder.show();
    }
}
