package com.library.librarysystem;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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

public class home extends Fragment implements AdapterView.OnItemSelectedListener {
    Activity activity;
    Context context;
    View root;
    Spinner spSearchTitle;
    String valueSearchTitle="title";
    Button bookSearch;
    JSONArray result;
    int resultLength;

    String[] id, title, author, section ;

    String KEY_ID = "id";
    String KEY_TITLE = "title";
    String KEY_AUTHOR = "author";
    String KEY_SECTION = "section";
    String JSON_ARRAY = "result";

    ListView list;
    EditText EditTextBook;
    String book_value= "";
    ImageView bookImage;

    String ip="raktadaan.000webhostapp.com";
    String SEARCH_URL = "http://"+ip+"/searchbook.php?title=";

    ExtraFunction checknet;
    ImageButton cross;
    boolean doubleBackToExitPressedOnce = false;
    ConstraintLayout mainLayout,test;
    BottomNavigationView bottomNavigationView;



    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        Log.e("apkflow","Home Fragment");
        activity = getActivity();
        context = getContext();


     /*   bottomNavigationView = (BottomNavigationView) root.findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);*/

        mainLayout= (ConstraintLayout) root.findViewById(R.id.fragment_home);
        spSearchTitle = (Spinner) root.findViewById(R.id.searchTitle);
        bookSearch = (Button) root.findViewById(R.id.btnsearch);
        bookImage = (ImageView) root.findViewById(R.id.book);
        EditTextBook = (EditText) root.findViewById(R.id.searchValue);
        list=(ListView) root.findViewById(R.id.booklist);
        cross = (ImageButton)  root.findViewById(R.id.cross);


        // Create an ArrayAdapter using the string array and a default spinnerlist layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(context, R.array.bookSearch, R.layout.spinnerlist);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(R.layout.spinnerlist);
        // Apply the adapter to the spinnerlist
        spSearchTitle.setAdapter(adapter2);
        spSearchTitle.setOnItemSelectedListener(this);

        valueSearchTitle= "Title";

        spSearchTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                valueSearchTitle= spSearchTitle.getSelectedItem().toString();
                Log.e("apkflow","spinnerItenClicked"+"= "+valueSearchTitle);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {



            }
        });


        checknet=new ExtraFunction(context,activity);

        bookSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboardFrom(context,root);
                book_value = EditTextBook.getText().toString();

              /*  list.setVisibility(View.INVISIBLE);
                bookImage.setVisibility(View.VISIBLE);
                bookSearch.setVisibility(View.VISIBLE);*/

                if (checknet.checkInternet("activity_main")&& !book_value.equals("")) {

                    getData();

                }
            }
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTextBook.setText("");
                book_value = "";
                list.setVisibility(View.INVISIBLE);
                bookImage.setVisibility(View.VISIBLE);
                bookSearch.setVisibility(View.VISIBLE);
            }
        });




        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void getData(){

        String url = null;
        try {
            url = SEARCH_URL + valueSearchTitle.trim() + "&name=" + URLEncoder.encode(book_value.trim(),"UTF-8");
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
            id=new String [resultLength];
            title=new String [resultLength];
            author = new String [resultLength];
            section = new String [resultLength];
            if (result.length()==0)
            {
                Toast.makeText(context, "No Books Record Found", Toast.LENGTH_SHORT).show();
            }
            else {
                for (int i = 0; i < result.length(); i++) {

                    RaktadaanData[i] = result.getJSONObject(i);
                    Log.e("apkflow", "Records:"+RaktadaanData[i].toString());

                    id[i] = RaktadaanData[i].getString(KEY_ID);
                    title[i] = RaktadaanData[i].getString(KEY_TITLE);
                    author[i] = RaktadaanData[i].getString(KEY_AUTHOR);
                    section[i] = RaktadaanData[i].getString(KEY_SECTION);
                }

                bookImage.setVisibility(View.INVISIBLE);
                bookSearch.setVisibility(View.INVISIBLE);
                list.setVisibility(View.VISIBLE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomList adapter=new CustomList(activity,context,id,title,author,section);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(context, "You Clicked at " +title[+ position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onResume() {
        super.onResume();
        mainLayout.setFocusableInTouchMode(true);
        mainLayout.requestFocus();
        mainLayout.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    Log.e("apkflow","back");


                    if (doubleBackToExitPressedOnce) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    doubleBackToExitPressedOnce = true;
                    Toast.makeText(context, "Please click BACK again to Exit", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce=false;
                        }
                    }, 2000);
                    return true;
                }
                return false;
            }
        });
    }
}
