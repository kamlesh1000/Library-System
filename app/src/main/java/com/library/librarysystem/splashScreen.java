package com.library.librarysystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static java.lang.Thread.sleep;


public class splashScreen extends AppCompatActivity {

    DatabaseHandler myDB;
    Context context;
    String url;

    String ip="raktadaan.000webhostapp.com";
    String getProfile_URL = "http://"+ip+"/getProfile.php?studentId=";
    JSONArray result;
    String JSON_ARRAY = "result";
    int resultLength;

    String KEY_STUDENTNAME = "studentName";
    String KEY_STUDENTPASSWORD = "studentPassword";
    String KEY_STUDENTROLLNO = "studentRollNo";
    String KEY_STUDENTBATCH = "studentBatch";
    String KEY_STUDENTPROGRAM = "studentProgram";
    String KEY_BOOK1 = "book1";
    String KEY_BOOK2 = "book2";
    String KEY_BOOK3 = "book3";
    String KEY_BOOK4 = "book4";
    String KEY_BOOK5 = "book5";
    String KEY_DUEDATE1 = "dueDate1";
    String KEY_DUEDATE2 = "dueDate2";
    String KEY_DUEDATE3 = "dueDate3";
    String KEY_DUEDATE4 = "dueDate4";
    String KEY_DUEDATE5 = "dueDate5";



    String studentId, studentName, studentPassword, studentRollNo, studentBatch, studentProgram, book1, book2, book3, book4, book5, dueDate1, dueDate2, dueDate3, dueDate4, dueDate5, sqLiteBook1, sqLiteBook2, sqLiteBook3, sqLiteBook4 ,sqLiteBook5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        context = getApplicationContext();

        if(doesDatabaseExist(context,"userinfo.db")){

            myDB = new DatabaseHandler(context);
            studentId= myDB.getStudentId();
            studentPassword= myDB.getStudentPassword();
            sqLiteBook1 = myDB.getBook1();
            sqLiteBook2 = myDB.getBook2();
            sqLiteBook3 = myDB.getBook3();
            sqLiteBook4 = myDB.getBook4();
            sqLiteBook5 = myDB.getBook5();

            Log.e("apkflow", "sqlitebook1:"+sqLiteBook1);
            Log.e("apkflow", "sqlitebook2:"+sqLiteBook2);
            Log.e("apkflow", "sqlitebook3:"+sqLiteBook3);
            Log.e("apkflow", "sqlitebook4:"+sqLiteBook4);
            Log.e("apkflow", "sqlitebook5:"+sqLiteBook5);

            Log.e("apkflow", "InitialNotify1:"+myDB.getNotifyStatus1());
            Log.e("apkflow", "InitialNotify2:"+myDB.getNotifyStatus2());
            Log.e("apkflow", "InitialNotify3:"+myDB.getNotifyStatus3());
            Log.e("apkflow", "InitialNotify4:"+myDB.getNotifyStatus4());
            Log.e("apkflow", "InitialNotify5:"+myDB.getNotifyStatus5());

            getData();
        }


        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(2000);

                    if(doesDatabaseExist(context,"userinfo.db")){
                        Intent i = new Intent(context, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(context, login.class);
                        startActivity(intent);
                        finish();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();


    }

    public boolean doesDatabaseExist(Context context,String dbname){
        File dbfile = context.getDatabasePath(dbname);
        return dbfile.exists();
    }



    private void getData(){

        try {
            url = getProfile_URL + URLEncoder.encode(studentId.trim(),"UTF-8")+ "&status=" + URLEncoder.encode("alreadyLoggedIn","UTF-8")+ "&password=" + URLEncoder.encode(studentPassword.trim(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        Log.e("apkflow", "url:" + url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }



    private void showJSON(String response){


        try {
            JSONObject jsonObject = new JSONObject(response);
            result = jsonObject.getJSONArray(JSON_ARRAY);
            resultLength=result.length();
            JSONObject[] RaktadaanData=new JSONObject[resultLength];
            Log.e("apkflow","resultLength"+Integer.toString(resultLength));

            if (result.length()==0)
            {
                Toast.makeText(context, "No Books Record Found", Toast.LENGTH_SHORT).show();
            }
            else {

                RaktadaanData[0] = result.getJSONObject(0);
                Log.e("apkflow", "Records:"+RaktadaanData[0].toString());

                studentName = RaktadaanData[0].getString(KEY_STUDENTNAME);
                studentPassword = RaktadaanData[0].getString(KEY_STUDENTPASSWORD);
                studentRollNo = RaktadaanData[0].getString(KEY_STUDENTROLLNO);
                studentBatch = RaktadaanData[0].getString(KEY_STUDENTBATCH);
                studentProgram = RaktadaanData[0].getString(KEY_STUDENTPROGRAM);
                book1 = RaktadaanData[0].getString(KEY_BOOK1);
                book2 = RaktadaanData[0].getString(KEY_BOOK2);
                book3 = RaktadaanData[0].getString(KEY_BOOK3);
                book4 = RaktadaanData[0].getString(KEY_BOOK4);
                book5 = RaktadaanData[0].getString(KEY_BOOK5);
                dueDate1 = RaktadaanData[0].getString(KEY_DUEDATE1);
                dueDate2 = RaktadaanData[0].getString(KEY_DUEDATE2);
                dueDate3 = RaktadaanData[0].getString(KEY_DUEDATE3);
                dueDate4 = RaktadaanData[0].getString(KEY_DUEDATE4);
                dueDate5 = RaktadaanData[0].getString(KEY_DUEDATE5);

                Log.e("apkflow", "Databasebook1:"+book1);
                Log.e("apkflow", "Databasebook2:"+book2);
                Log.e("apkflow", "Databasebook3:"+book3);
                Log.e("apkflow", "Databasebook4:"+book4);
                Log.e("apkflow", "Databasebook5:"+book5);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        updateLocalDatabase();
    }

    public void updateLocalDatabase(){
        myDB.updateStudentName(studentName);
        myDB.updateStudentPassword(studentPassword);
        myDB.updateStudentRollNo(studentRollNo);
        myDB.updateStudentBatch(studentBatch);
        myDB.updateStudentProgram(studentProgram);
        myDB.updateBook1(book1);
        myDB.updateBook2(book2);
        myDB.updateBook3(book3);
        myDB.updateBook4(book4);
        myDB.updateBook5(book5);
        myDB.updateDueDate1(dueDate1);
        myDB.updateDueDate2(dueDate2);
        myDB.updateDueDate3(dueDate3);
        myDB.updateDueDate4(dueDate4);
        myDB.updateDueDate5(dueDate5);

        if(sqLiteBook1.equals("None") && !book1.equals("None")){
            myDB.updateNotifyStatus1("NotifyYes");
        }
        if(sqLiteBook2.equals("None") && !book2.equals("None")){
            myDB.updateNotifyStatus2("NotifyYes");
        }
        if(sqLiteBook3.equals("None") && !book3.equals("None")){
            myDB.updateNotifyStatus3("NotifyYes");
        }
        if(sqLiteBook4.equals("None") && !book4.equals("None")){
            myDB.updateNotifyStatus4("NotifyYes");
        }
        if(sqLiteBook5.equals("None") && !book5.equals("None")){
            myDB.updateNotifyStatus5("NotifyYes");
        }

        if(!sqLiteBook1.equals("None") && book1.equals("None")){
            myDB.updateNotifyStatus1("NotifyNo");
        }
        if(!sqLiteBook2.equals("None") && book2.equals("None")){
            myDB.updateNotifyStatus2("NotifyNo");
        }
        if(!sqLiteBook3.equals("None") && book3.equals("None")){
            myDB.updateNotifyStatus3("NotifyNo");
        }
        if(!sqLiteBook4.equals("None") && book4.equals("None")){
            myDB.updateNotifyStatus4("NotifyNo");
        }
        if(!sqLiteBook5.equals("None") && book5.equals("None")){
            myDB.updateNotifyStatus5("NotifyNo");
        }


        Log.e("apkflow", "FinalNotify1:"+myDB.getNotifyStatus1());
        Log.e("apkflow", "FinalNotify2:"+myDB.getNotifyStatus2());
        Log.e("apkflow", "FinalNotify3:"+myDB.getNotifyStatus3());
        Log.e("apkflow", "FinalNotify4:"+myDB.getNotifyStatus4());
        Log.e("apkflow", "FinalNotify5:"+myDB.getNotifyStatus5());

        myDB.close();
    }
}
