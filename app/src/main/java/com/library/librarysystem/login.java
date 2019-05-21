package com.library.librarysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class login extends AppCompatActivity {

    EditText ETregistratinNo, ETpassword;
    Button loginBut;

    DatabaseMaker myDB;
    DatabaseHandler myDB2;
    String url;
    Intent i;

    String ip="raktadaan.000webhostapp.com";
    String getProfile_URL = "http://"+ip+"/getProfile.php?studentId=";
    JSONArray result;
    String JSON_ARRAY = "result";
    int resultLength;

    String KEY_STUDENTID = "studentId";
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
    ExtraFunction checknet;
    String studentId, password, studentName, studentPassword, studentRollNo, studentBatch, studentProgram, book1, book2, book3, book4, book5, dueDate1, dueDate2, dueDate3, dueDate4, dueDate5, tokenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ETregistratinNo = (EditText) findViewById(R.id.ETregistrationNo);
        ETpassword = (EditText) findViewById(R.id.ETpassword);
        loginBut = (Button) findViewById(R.id.login);

        myDB = new DatabaseMaker(this);
        myDB2 = new DatabaseHandler(this);

        checknet=new ExtraFunction(this,this);

        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                studentId = ETregistratinNo.getText().toString();
                password = ETpassword.getText().toString();

                if (checknet.checkInternet("activity_login")) {

                    if( !studentId.equals("") && !password.equals("")) {
                        Log.e("apkflow", "condition satisfied");

                        getData();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Enter the credentials",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }


    private void getData(){

        tokenId = FirebaseInstanceId.getInstance().getToken();

        Log.e("apkflow", "token : "+ tokenId );


        try {
            url = getProfile_URL + URLEncoder.encode(studentId.trim(),"UTF-8")+ "&status=" + URLEncoder.encode("newLogin","UTF-8")+ "&password=" + URLEncoder.encode(password.trim(),"UTF-8")+ "&tokenId=" + URLEncoder.encode(tokenId.trim(),"UTF-8");
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
                        Toast.makeText(login.this, "Can't connect to database.", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    private void showJSON(String response){
        Log.e("apkflow","showJSON");

        try {
            JSONObject jsonObject = new JSONObject(response);
            result = jsonObject.getJSONArray(JSON_ARRAY);
            resultLength=result.length();
            JSONObject[] RaktadaanData=new JSONObject[resultLength];
            Log.e("apkflow","resultLength"+Integer.toString(resultLength));

            if (result.length()==0)
            {
                Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_SHORT).show();
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

                makeLocalDatabase();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void makeLocalDatabase(){
        myDB.insertStudentId(studentId);
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

        if(book1.equals("None"))
        myDB.updateNotifyStatus1("NotifyNo");
        else
        myDB.updateNotifyStatus1("NotifyYes");

        if(book2.equals("None"))
        myDB.updateNotifyStatus2("NotifyNo");
        else
        myDB.updateNotifyStatus2("NotifyYes");

        if(book3.equals("None"))
        myDB.updateNotifyStatus3("NotifyNo");
        else
        myDB.updateNotifyStatus3("NotifyYes");

        if(book4.equals("None"))
        myDB.updateNotifyStatus4("NotifyNo");
        else
        myDB.updateNotifyStatus4("NotifyYes");

        if(book5.equals("None"))
        myDB.updateNotifyStatus5("NotifyNo");
        else
        myDB.updateNotifyStatus5("NotifyYes");

        myDB.close();

        i = new Intent (this,MainActivity.class);
        startActivity(i);
    }
}
