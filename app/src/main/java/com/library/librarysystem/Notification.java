package com.library.librarysystem;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.sleep;

public class Notification extends Fragment {
    View root;
    Activity activity;
    Context context;
    BottomNavigationView bottomNavigationView;
    ConstraintLayout main;
    ListView list;
    DatabaseHandler myDB;
    Integer issuedCount = 0;
    SimpleDateFormat format;
    int i;
    String book1, book2, book3, book4, book5;
    Date currentDate, dueDate1,dueDate2, dueDate3, dueDate4, dueDate5;
    String a,b,c,d,e,onlyDateDue1, onlyDateDue2, onlyDateDue3, onlyDateDue4, onlyDateDue5,currentDateString;
    ArrayList<String> bookList = new ArrayList<String>();
    ArrayList<String> authorList = new ArrayList<String>();
    ArrayList<String> notifyList = new ArrayList<String>();
    ArrayList<String> daysLeftList = new ArrayList<String>();
    ArrayList<String> bookListPosition = new ArrayList<String>();
    ArrayList<String> indexValuePosition = new ArrayList<String>();

    String ip="raktadaan.000webhostapp.com";
    String CODETONAME_URL = "http://"+ip+"/codeToName.php?bookId1=";
    JSONArray result;
    int resultLength;
    String JSON_ARRAY = "result";
    String KEY_ID = "bookId";
    String KEY_TITLE = "bookTitle";
    String KEY_AUTHOR = "bookAuthor";
    String[] bookId,bookTitle,bookAuthor;




    public Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_notification, container, false);
        Log.e("apkflow","Notification fragment");

        activity = getActivity();
        context = getContext();

       /* main = (ConstraintLayout) root.findViewById(R.id.activity_main);
        bottomNavigationView = (BottomNavigationView) main.findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_notifications);*/

        list=(ListView) root.findViewById(R.id.notificationList);
        myDB = new DatabaseHandler(context);

        book1 = myDB.getBook1();
        book2 = myDB.getBook2();
        book3 = myDB.getBook3();
        book4 = myDB.getBook4();
        book5 = myDB.getBook5();

        getBooksdata();

        return root;
    }

    private void getBooksdata() {

        Log.e("apkflow","getBooksdata");
        String url = null;
        try {
            url = CODETONAME_URL + URLEncoder.encode(book1.trim(),"UTF-8")+ "&bookId2=" + URLEncoder.encode(book2.trim(),"UTF-8")
                    + "&bookId3=" + URLEncoder.encode(book3.trim(),"UTF-8")+ "&bookId4=" + URLEncoder.encode(book4.trim(),"UTF-8")
                    + "&bookId5=" + URLEncoder.encode(book5.trim(),"UTF-8");
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
                        Toast.makeText(context, "Can't connect to database.", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        Log.e("apkflow","showJson");

        try {
            JSONObject jsonObject = new JSONObject(response);
            result = jsonObject.getJSONArray(JSON_ARRAY);
            resultLength=result.length();
            JSONObject[] RaktadaanData=new JSONObject[resultLength];
            Log.e("apkflow","resultLength: "+Integer.toString(resultLength));
            bookId=new String [resultLength];
            bookTitle=new String [resultLength];
            bookAuthor = new String [resultLength];
            if (result.length()==0)
            {
                Toast.makeText(context, "No Books Record Found", Toast.LENGTH_SHORT).show();
            }
            else {
                for (int j = 0; j < result.length(); j++) {

                    // Log.e("apkflow",Integer.toString(j));
                    RaktadaanData[j] = result.getJSONObject(j);
                    Log.e("apkflow", "Records:"+RaktadaanData[j].toString());

                    bookId[j] = RaktadaanData[j].getString(KEY_ID);
                    bookTitle[j] = RaktadaanData[j].getString(KEY_TITLE);
                    bookAuthor[j] = RaktadaanData[j].getString(KEY_AUTHOR);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        checkDueDate();
    }

    private void checkDueDate() {

        Log.e("apkflow","checkDueDate");

        format = new SimpleDateFormat("yyyy-MM-dd");

        try {

            String pattern = "yyyy-MM-dd";
            currentDateString = new SimpleDateFormat(pattern).format(new Date());
            currentDate = format.parse(currentDateString);

            if(!book1.equals("None")){
                a = myDB.getDueDate1();
                onlyDateDue1 = a.substring(0, 10);
                dueDate1 = format.parse(onlyDateDue1);
                daysLeftList.add(String.valueOf((dueDate1.getTime() - currentDate.getTime()) / (24 * 60 * 60 * 1000)));
            }
            if(!book2.equals("None")){
                b = myDB.getDueDate2();
                onlyDateDue2 = b.substring(0, 10);
                dueDate2 = format.parse(onlyDateDue2);
                daysLeftList.add(String.valueOf((dueDate2.getTime() - currentDate.getTime()) / (24 * 60 * 60 * 1000)));
            }
            if(!book3.equals("None")){
                c = myDB.getDueDate3();
                onlyDateDue3 = c.substring(0, 10);
                dueDate3 = format.parse(onlyDateDue3);
                daysLeftList.add(String.valueOf((dueDate3.getTime() - currentDate.getTime()) / (24 * 60 * 60 * 1000)));
            }
            if(!book4.equals("None")){
                d = myDB.getDueDate4();
                onlyDateDue4 = d.substring(0, 10);
                dueDate4 = format.parse(onlyDateDue4);
                daysLeftList.add(String.valueOf((dueDate4.getTime() - currentDate.getTime()) / (24 * 60 * 60 * 1000)));
            }
            if(!book5.equals("None")){
                e = myDB.getDueDate5();
                onlyDateDue5 = e.substring(0, 10);
                dueDate5 = format.parse(onlyDateDue5);
                daysLeftList.add(String.valueOf((dueDate5.getTime() - currentDate.getTime()) / (24 * 60 * 60 * 1000)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        BookCheck();
    }

    private void BookCheck() {
        Log.e("apkflow","BookCheck");
    try {
        if (!book1.equals("None")) {
            bookList.add(bookTitle[i]);
            notifyList.add(myDB.getNotifyStatus1());
            authorList.add(bookAuthor[i]);
            bookListPosition.add("1");
            indexValuePosition.add(String.valueOf(i));
        } else {
            indexValuePosition.add(String.valueOf(99));
        }
        if (!book2.equals("None")) {
            ++i;
            bookList.add(bookTitle[i]);
            notifyList.add(myDB.getNotifyStatus2());
            authorList.add(bookAuthor[i]);
            bookListPosition.add("2");
            indexValuePosition.add(String.valueOf(i));
        } else {
            indexValuePosition.add(String.valueOf(99));
        }
        if (!book3.equals("None")) {
            ++i;
            bookList.add(bookTitle[i]);
            notifyList.add(myDB.getNotifyStatus3());
            authorList.add(bookAuthor[i]);
            bookListPosition.add("3");
            indexValuePosition.add(String.valueOf(i));
        } else {
            indexValuePosition.add(String.valueOf(99));
        }
        if (!book4.equals("None")) {
            ++i;
            bookList.add(bookTitle[i]);
            notifyList.add(myDB.getNotifyStatus4());
            authorList.add(bookAuthor[i]);
            bookListPosition.add("4");
            indexValuePosition.add(String.valueOf(i));
        } else {
            indexValuePosition.add(String.valueOf(99));
        }
        if (!book5.equals("None")) {
            ++i;
            bookList.add(bookTitle[i]);
            notifyList.add(myDB.getNotifyStatus5());
            authorList.add(bookAuthor[i]);
            bookListPosition.add("5");
            indexValuePosition.add(String.valueOf(i));
        } else {
            indexValuePosition.add(String.valueOf(99));
        }

        while (notifyList.size() != 5) {
            notifyList.add("NofifyNo");
        }
    }
    catch (Exception e){
        e.fillInStackTrace();
    }

        notificationPage();
    }

    private void notificationPage() {
        Log.e("apkflow","notificationPage");
        NotificationList adapter=new NotificationList(activity,context,bookList,authorList,notifyList,daysLeftList,bookListPosition,indexValuePosition,issuedCount);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(context, "You Clicked at " +bookList.get(+ position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
