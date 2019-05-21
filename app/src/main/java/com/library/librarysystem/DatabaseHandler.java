package com.library.librarysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "userinfo.db";

    private static final String TABLE_NAME = "InfoTable";

    SQLiteDatabase db;

    Cursor res;
    String studentId,studentName, studentPassword, studentRollNo, studentBatch, studentProgram, book1, book2, book3, book4, book5, dueDate1, dueDate2, dueDate3, dueDate4, dueDate5, notifyStatus1, notifyStatus2, notifyStatus3, notifyStatus4, notifyStatus5, tokenId;

    public static final String COL_1 = "STUDENT_ID";
    public static final String COL_2 = "STUDENT_NAME";
    public static final String COL_3 = "STUDENT_PASSWORD";
    public static final String COL_4 = "STUDENT_ROLL_NO";
    public static final String COL_5 = "STUDENT_BATCH";
    public static final String COL_6 = "STUDENT_PROGRAM";
    public static final String COL_7 = "BOOK_1";
    public static final String COL_8 = "BOOK_2";
    public static final String COL_9 = "BOOK_3";
    public static final String COL_10 = "BOOK_4";
    public static final String COL_11 = "BOOK_5";
    public static final String COL_12 = "DUEDATE_1";
    public static final String COL_13 = "DUEDATE_2";
    public static final String COL_14 = "DUEDATE_3";
    public static final String COL_15 = "DUEDATE_4";
    public static final String COL_16 = "DUEDATE_5";
    public static final String COL_17 = "NOTIFYSTATUS_1";
    public static final String COL_18 = "NOTIFYSTATUS_2";
    public static final String COL_19 = "NOTIFYSTATUS_3";
    public static final String COL_20 = "NOTIFYSTATUS_4";
    public static final String COL_21 = "NOTIFYSTATUS_5";
    public static final String COL_22 = "TOKEN_ID";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME,null,2);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+ TABLE_NAME+ "(STUDENT_ID VARCHAR(50),STUDENT_NAME VARCHAR(50),STUDENT_PASSWORD VARCHAR(50),STUDENT_ROLL_NO VARCHAR(50),STUDENT_BATCH VARCHAR(50),STUDENT_PROGRAM VARCHAR(50),BOOK_1 VARCHAR(50),BOOK_2 VARCHAR(50),BOOK_3 VARCHAR(50),BOOK_4 VARCHAR(50),BOOK_5 VARCHAR(50),DUEDATE_1 VARCHAR(50),DUEDATE_2 VARCHAR(50),DUEDATE_3 VARCHAR(50),DUEDATE_4 VARCHAR(50),DUEDATE_5 VARCHAR(50),NOTIFYSTATUS_1 VARCHAR(50),NOTIFYSTATUS_2 VARCHAR(50),NOTIFYSTATUS_3 VARCHAR(50),NOTIFYSTATUS_4 VARCHAR(50),NOTIFYSTATUS_5 VARCHAR(50),TOKEN_ID VARCHAR(250))");
    }


    public String getStudentId(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT STUDENT_ID FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            studentId = res.getString(0);
        }
        return studentId;
    }

    public String getStudentName(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT STUDENT_NAME FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            studentName = res.getString(0);
        }
        return studentName;
    }

    public String getStudentPassword(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT STUDENT_PASSWORD FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            studentPassword = res.getString(0);
        }
        return studentPassword;
    }

    public String getStudentRollNo(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT STUDENT_ROLL_NO FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            studentRollNo = res.getString(0);
        }
        return studentRollNo;
    }

    public String getStudentBatch(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT STUDENT_BATCH FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            studentBatch = res.getString(0);
        }
        return studentBatch;
    }

    public String getStudentProgram(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT STUDENT_PROGRAM FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            studentProgram = res.getString(0);
        }
        return studentProgram;
    }

    public String getBook1(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT BOOK_1 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            book1 = res.getString(0);
        }
        return book1;
    }

    public String getBook2(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT BOOK_2 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            book2 = res.getString(0);
        }
        return book2;
    }

    public String getBook3(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT BOOK_3 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            book3 = res.getString(0);
        }
        return book3;
    }

    public String getBook4(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT BOOK_4 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            book4 = res.getString(0);
        }
        return book4;
    }

    public String getBook5(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT BOOK_5 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            book5 = res.getString(0);
        }
        return book5;
    }

    public String getDueDate1(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT DUEDATE_1 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            dueDate1 = res.getString(0);
        }
        return dueDate1;
    }

    public String getDueDate2(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT DUEDATE_2 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            dueDate2 = res.getString(0);
        }
        return dueDate2;
    }

    public String getDueDate3(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT DUEDATE_3 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            dueDate3 = res.getString(0);
        }
        return dueDate3;
    }

    public String getDueDate4(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT DUEDATE_4 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            dueDate4 = res.getString(0);
        }
        return dueDate4;
    }

    public String getDueDate5(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT DUEDATE_5 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            dueDate5 = res.getString(0);
        }
        return dueDate5;
    }

    public String getNotifyStatus1(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT NOTIFYSTATUS_1 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            notifyStatus1 = res.getString(0);
        }
        return notifyStatus1;
    }

    public String getNotifyStatus2(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT NOTIFYSTATUS_2 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            notifyStatus2 = res.getString(0);
        }
        return notifyStatus2;
    }

    public String getNotifyStatus3(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT NOTIFYSTATUS_3 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            notifyStatus3 = res.getString(0);
        }
        return notifyStatus3;
    }

    public String getNotifyStatus4(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT NOTIFYSTATUS_4 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            notifyStatus4 = res.getString(0);
        }
        return notifyStatus4;
    }

    public String getNotifyStatus5(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT NOTIFYSTATUS_5 FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            notifyStatus5 = res.getString(0);
        }
        return notifyStatus5;
    }

    public String getTokenId(){
        db = this.getWritableDatabase();
        res = db.rawQuery("SELECT TOKEN_ID FROM InfoTable",null);
        res.moveToNext();
        if( res != null && res.moveToFirst() ) {
            tokenId = res.getString(0);
        }
        return tokenId;
    }




    public void insertStudentId(String studentId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,studentId);
        db.insert(TABLE_NAME,null,contentValues);
    }

    public  void updateStudentName(String studentName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,studentName);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public  void updateStudentPassword(String studentPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3,studentPassword);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public  void updateStudentRollNo(String studentRollNo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4,studentRollNo);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateStudentBatch(String studentBatch){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_5,studentBatch);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateStudentProgram(String studentProgram){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_6,studentProgram);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateBook1(String book1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_7,book1);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateBook2(String book2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_8,book2);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateBook3(String book3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_9,book3);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateBook4(String book4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_10,book4);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateBook5(String book5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_11,book5);
        db.update(TABLE_NAME,contentValues,null,null);
    }


    public void updateDueDate1(String dueDate1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_12,dueDate1);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateDueDate2(String dueDate2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_13,dueDate2);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateDueDate3(String dueDate3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_14,dueDate3);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateDueDate4(String dueDate4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_15,dueDate4);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateDueDate5(String dueDate5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_16,dueDate5);
        db.update(TABLE_NAME,contentValues,null,null);
    }


    public void updateNotifyStatus1(String notifyStatus1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_17,notifyStatus1);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateNotifyStatus2(String notifyStatus2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_18,notifyStatus2);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateNotifyStatus3(String notifyStatus3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_19,notifyStatus3);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateNotifyStatus4(String notifyStatus4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_20,notifyStatus4);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateNotifyStatus5(String notifyStatus5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_21,notifyStatus5);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateTokenId(String tokenId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_22,tokenId);
        db.update(TABLE_NAME,contentValues,null,null);
    }

}