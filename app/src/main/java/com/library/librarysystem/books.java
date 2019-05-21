package com.library.librarysystem;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class books extends Fragment {
    View root;
    Activity activity;
    Context context;
    DatabaseHandler myDB;
    Integer issuedCount = 0, remainingSlot;

    String book1, book2, book3, book4, book5, dueDate1, dueDate2, dueDate3, dueDate4, dueDate5;


    TextView TVbookIssued, TVremainingSlots, TVbook1, TVbook2, TVbook3, TVbook4, TVbook5, TVdueDate1, TVdueDate2, TVdueDate3, TVdueDate4, TVdueDate5;


    public books() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_books, container, false);

        activity = getActivity();
        context = getContext();

        TVbookIssued = (TextView) root.findViewById(R.id.noOfBookIssuedValue);
        TVremainingSlots = (TextView) root.findViewById(R.id.remainingSlotsValue);
        TVbook1 = (TextView) root.findViewById(R.id.book1Value);
        TVbook2 = (TextView) root.findViewById(R.id.book2Value);
        TVbook3 = (TextView) root.findViewById(R.id.book3Value);
        TVbook4 = (TextView) root.findViewById(R.id.book4Value);
        TVbook5 = (TextView) root.findViewById(R.id.book5Value);
        TVdueDate1 = (TextView) root.findViewById(R.id.dueDate1);
        TVdueDate2 = (TextView) root.findViewById(R.id.dueDate2);
        TVdueDate3 = (TextView) root.findViewById(R.id.dueDate3);
        TVdueDate4 = (TextView) root.findViewById(R.id.dueDate4);
        TVdueDate5 = (TextView) root.findViewById(R.id.dueDate5);

        myDB = new DatabaseHandler(context);
        book1 = myDB.getBook1();
        book2 = myDB.getBook2();
        book3 = myDB.getBook3();
        book4 = myDB.getBook4();
        book5 = myDB.getBook5();

        dueDate1 = myDB.getDueDate1();
        dueDate2 = myDB.getDueDate2();
        dueDate3 = myDB.getDueDate3();
        dueDate4 = myDB.getDueDate4();
        dueDate5 = myDB.getDueDate5();


        if(!book1.equals("None")){
            ++issuedCount;
        }
        if(!book2.equals("None")){
            ++issuedCount;
        }
        if(!book3.equals("None")){
            ++issuedCount;
        }
        if(!book4.equals("None")){
            ++issuedCount;
        }
        if(!book5.equals("None")){
            ++issuedCount;
        }

        remainingSlot = 5-issuedCount;

        Log.e("apkflow","Issued Books"+issuedCount);
        Log.e("apkflow","Remaining Slots"+remainingSlot);
        Log.e("apkflow","Book1"+book1);
        Log.e("apkflow","Book2"+book2);
        Log.e("apkflow","Book3"+book3);
        Log.e("apkflow","Book4"+book4);
        Log.e("apkflow","Book5"+book5);
        Log.e("apkflow","DueDate1"+dueDate1);
        Log.e("apkflow","DueDate2"+dueDate2);
        Log.e("apkflow","DueDate3"+dueDate3);
        Log.e("apkflow","DueDate4"+dueDate4);
        Log.e("apkflow","DueDate5"+dueDate5);

        TVbookIssued.setText(issuedCount.toString());
        TVremainingSlots.setText(remainingSlot.toString());
        TVbook1.setText(book1);
        TVbook2.setText(book2);
        TVbook3.setText(book3);
        TVbook4.setText(book4);
        TVbook5.setText(book5);

        if(dueDate1.equals("9999-12-30")){
            dueDate1 = "---";
        }
        if(dueDate2.equals("9999-12-30")){
            dueDate2 = "---";
        }
        if(dueDate3.equals("9999-12-30")){
            dueDate3 = "---";
        }
        if(dueDate4.equals("99999-12-30")){
            dueDate4 = "---";
        }
        if(dueDate5.equals("9999-12-30")){
            dueDate5 = "---";
        }

        TVdueDate1.setText(dueDate1);
        TVdueDate2.setText(dueDate2);
        TVdueDate3.setText(dueDate3);
        TVdueDate4.setText(dueDate4);
        TVdueDate5.setText(dueDate5);

        return  root;
    }

}
