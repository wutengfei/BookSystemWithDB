package com.example.administrator.book.com.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/15.
 */
public class DBAdapter {
    private static final String DB_NAME = "bookManage.db";
    private static final String DB_TABLE = "student";
    private static final int DB_version = 1;

    private static final String KEY_ID = "_id";
    private static final String KEY_NO = "no";
    private static final String KEY_NAME = "name";
    private static final String KEY_MAJOR = "major";
    private static final String KEY_CLASS = "class";
    private static final String KEY_PHONE = "phone";

    public SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    private static class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        private static final String DB_CREATE = "create table " +
                DB_TABLE + "(" +
                KEY_ID + " integer primary key autoincrement," +//注意：字符串连接时要用空格来分开
                KEY_NO + " varchar(20)," +
                KEY_NAME + " varchar(20)," +
                KEY_CLASS + " varchar(20)," +
                KEY_MAJOR + " varchar(20)," +
                KEY_PHONE + " varchar(20)" +
                ")";

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS" + DB_TABLE);
            onCreate(_db);
        }

    }

    public DBAdapter(Context _context) {
        context = _context;
    }

    //打开数据库
    public void open() throws SQLiteException {
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_version);
        try {
            db = dbOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbOpenHelper.getReadableDatabase();
        }
    }

    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    public long insertStudent(Student s1) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_NO, s1.getNo());
        newValues.put(KEY_NAME, s1.getName());
        newValues.put(KEY_CLASS, s1.getClasses());
        newValues.put(KEY_MAJOR, s1.getMajor());
        newValues.put(KEY_PHONE, s1.getPhone());
        return db.insert(DB_TABLE, null, newValues);
    }

    public long deleteAll() {
        return (db.delete(DB_TABLE, null, null));
    }

    public long deleteStudent(String no) {
        return db.delete(DB_TABLE, KEY_NO + " like ? ", new String[]{no});
    }

    public long updateStudent(String no, Student student) {
        ContentValues updateValues = new ContentValues();
        updateValues.put(KEY_NO, student.getNo());
        updateValues.put(KEY_NAME, student.getName());
        updateValues.put(KEY_CLASS, student.getClasses());
        updateValues.put(KEY_MAJOR, student.getMajor());
        updateValues.put(KEY_PHONE, student.getPhone());

        return db.update(DB_TABLE, updateValues, KEY_NO + " like ? ", new String[]{no});

    }

    public Student[] queryStudent(String no) {
        Cursor cursor = db.query(DB_TABLE, new String[]{KEY_NO, KEY_NAME, KEY_CLASS, KEY_MAJOR, KEY_PHONE},
                KEY_NO + " like ? ", new String[]{no}, null, null, null, null);
        return ConvertToStudent(cursor);
    }

    public Student[] getAllStu() {
        Cursor cursor = db.query(DB_TABLE, new String[]{KEY_NO, KEY_NAME, KEY_CLASS, KEY_MAJOR, KEY_PHONE}, null, null, null, null, null);
        return ConvertToStudent(cursor);
    }

    private Student[] ConvertToStudent(Cursor cursor) {
        int resultCouunts = cursor.getCount();//@return the current cursor position
        if (resultCouunts == 0 || !cursor.moveToFirst()) return null;
        Student[] peoples = new Student[resultCouunts];
        for (int i = 0; i < resultCouunts; i++) {
            peoples[i] = new Student();
            peoples[i].setNo(cursor.getString(cursor.getColumnIndex(KEY_NO)));
            peoples[i].setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            peoples[i].setMajor(cursor.getString(cursor.getColumnIndex(KEY_MAJOR)));
            peoples[i].setClasses(cursor.getString(cursor.getColumnIndex(KEY_CLASS)));
            peoples[i].setPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            cursor.moveToNext();
        }
        return peoples;
    }
}