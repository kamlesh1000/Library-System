package com.library.librarysystem;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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

public class dashboard extends Fragment {
    View root;
    ImageButton profileBut,booksBut,mapsBut,imageView4But;
    FragmentTransaction fragmentTransaction;
    Intent i;
    Activity activity;
    Context context;
    BottomNavigationView bottomNavigationView;
    ConstraintLayout main;
    Button logout;
    ExtraFunction checknet;
    String url;
    String ip="librarysystem101.000webhostapp.com";
    String logout_URL = "http://"+ip+"/logout.php?studentId=";
    DatabaseHandler myDB;



    public dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Log.e("apkflow","dashboard fragment");

        activity = getActivity();
        context = getContext();

        checknet=new ExtraFunction(context,activity);

        myDB = new DatabaseHandler(context);

      /*  main = (ConstraintLayout) root.findViewById(R.id.activity_main);
        bottomNavigationView = (BottomNavigationView) main.findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);*/

        profileBut = (ImageButton) root.findViewById(R.id.profile);
        booksBut = (ImageButton) root.findViewById(R.id.books);
        //mapsBut = (ImageButton) root.findViewById(R.id.maps);
        //imageView4But = (ImageButton) root.findViewById(R.id.imageView4);
        logout = (Button) root.findViewById(R.id.logout);


        profileBut.setOnClickListener(new clickListener());
        booksBut.setOnClickListener(new clickListener());
      //  mapsBut.setOnClickListener(new clickListener());
       // imageView4But.setOnClickListener(new clickListener());
        logout.setOnClickListener(new clickListener());



        return root;
    }


    public class clickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.profile:

                    setFragment(new profile());

                    break;
                case R.id.books:

                    setFragment(new books());

                    break;

                /*case R.id.maps:

                    i =new Intent(activity,MapsActivity.class);
                    startActivity(i);

                    break;

                case R.id.imageView4:

                  //  setFragment(new imageView());

                    break;*/

                case R.id.logout:

                    if (checknet.checkInternet("activity_main")) {

                        String StdId = myDB.getStudentId();

                        try {
                            url = logout_URL + URLEncoder.encode(StdId.trim(),"UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        Log.e("apkflow",url);

                        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

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

                        context.deleteDatabase("userinfo.db");
                        Intent i = new Intent(activity,login.class);
                        startActivity(i);
                        activity.finish();

                    }


                    break;
            }
        }
    }

    public void setFragment(Fragment newFragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, newFragment);
        fragmentTransaction.addToBackStack("tag");
        fragmentTransaction.commit();
    }

}
