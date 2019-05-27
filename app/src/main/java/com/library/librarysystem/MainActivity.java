package com.library.librarysystem;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    DatabaseHandler myDB;
    Date currentDate, dueDate1,dueDate2, dueDate3, dueDate4, dueDate5;
    long DaysLeft1, DaysLeft2, DaysLeft3, DaysLeft4, DaysLeft5;
    String a,b,c,d,e,onlyDateDue1, onlyDateDue2, onlyDateDue3, onlyDateDue4, onlyDateDue5, notifyStatus1, notifyStatus2, notifyStatus3, notifyStatus4, notifyStatus5, message, currentDateString, tokenId;
    SimpleDateFormat format;
    ExtraFunction checknet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("apkflow","MainActivity fragment");

        myDB = new DatabaseHandler(this);
        checknet=new ExtraFunction(this,this);


        notifyStatus1 = myDB.getNotifyStatus1();
            notifyStatus2 = myDB.getNotifyStatus2();
            notifyStatus3 = myDB.getNotifyStatus3();
            notifyStatus4 = myDB.getNotifyStatus4();
            notifyStatus5 = myDB.getNotifyStatus5();

        checkDueDate();

        setFragment(new home());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("apkflow","onResume Started");

        tokenId = FirebaseInstanceId.getInstance().getToken();
        Log.e("apkflow", "token : "+ tokenId );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("apkflow","onPause Started");
    }

    private void checkDueDate() {

        format = new SimpleDateFormat("yyyy-MM-dd");

        try {

            String pattern = "yyyy-MM-dd";
            currentDateString =new SimpleDateFormat(pattern).format(new Date());
            currentDate = format.parse(currentDateString);

            a = myDB.getDueDate1();
            onlyDateDue1 = a.substring(0,10);
            dueDate1 = format.parse(onlyDateDue1);
            DaysLeft1 = (dueDate1.getTime() - currentDate.getTime()) / (24*60*60*1000);

            b = myDB.getDueDate2();
            onlyDateDue2 = b.substring(0,10);
            dueDate2 = format.parse(onlyDateDue2);
            DaysLeft2 = (dueDate2.getTime() - currentDate.getTime()) / (24*60*60*1000);

            c = myDB.getDueDate3();
            onlyDateDue3 = c.substring(0,10);
            dueDate3 = format.parse(onlyDateDue3);
            DaysLeft3 = (dueDate3.getTime() - currentDate.getTime()) / (24*60*60*1000);

            d = myDB.getDueDate4();
            onlyDateDue4 = d.substring(0,10);
            dueDate4 = format.parse(onlyDateDue4);
            DaysLeft4 = (dueDate4.getTime() - currentDate.getTime()) / (24*60*60*1000);

            e = myDB.getDueDate5();
            onlyDateDue5 = e.substring(0,10);
            dueDate5 = format.parse(onlyDateDue5);
            DaysLeft5 = (dueDate5.getTime() - currentDate.getTime()) / (24*60*60*1000);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.e("apkflow", "CurrentDate:" + currentDate);
        Log.e("apkflow", "daysLeft1:" + DaysLeft1);
        Log.e("apkflow", "daysLeft2:" + DaysLeft2);
        Log.e("apkflow", "daysLeft3:" + DaysLeft3);
        Log.e("apkflow", "daysLeft4:" + DaysLeft4);
        Log.e("apkflow", "daysLeft5:" + DaysLeft5);

        if ((DaysLeft1 == 3 && notifyStatus1.equals("NotifyYes")) || (DaysLeft2 == 3 && notifyStatus2.equals("NotifyYes")) || (DaysLeft3 == 3 && notifyStatus3.equals("NotifyYes")) || (DaysLeft4 == 3 && notifyStatus4.equals("NotifyYes")) || (DaysLeft5 == 3 && notifyStatus5.equals("NotifyYes"))) {
            message = "Note: You have got 3 days to renew the books.";
        }
        if ((DaysLeft1 == 2 && notifyStatus1.equals("NotifyYes")) || (DaysLeft2 == 2 && notifyStatus2.equals("NotifyYes")) || (DaysLeft3 == 2 && notifyStatus3.equals("NotifyYes")) || (DaysLeft4 == 2 && notifyStatus4.equals("NotifyYes")) || (DaysLeft5 == 3 && notifyStatus5.equals("NotifyYes"))) {
            message = "Note: You have got 2 days to renew the books.";
        }
        if ((DaysLeft1 == 1 && notifyStatus1.equals("NotifyYes")) || (DaysLeft2 == 1 && notifyStatus2.equals("NotifyYes")) || (DaysLeft3 == 1 && notifyStatus3.equals("NotifyYes")) || (DaysLeft4 == 1 && notifyStatus4.equals("NotifyYes")) || (DaysLeft5 == 1 && notifyStatus5.equals("NotifyYes"))) {
            message = "Note: You have got 1 day to renew the books.";
        }
        if ((DaysLeft1 == 0 && notifyStatus1.equals("NotifyYes")) || (DaysLeft2 == 0 && notifyStatus2.equals("NotifyYes")) || (DaysLeft3 == 0 && notifyStatus3.equals("NotifyYes")) || (DaysLeft4 == 0 && notifyStatus4.equals("NotifyYes")) || (DaysLeft5 == 0 && notifyStatus5.equals("NotifyYes"))) {
            message = "Note: Today is the last day to renew the books.";
        }
        if ((DaysLeft1 < 0 && notifyStatus1.equals("NotifyYes")) || (DaysLeft2 < 0 && notifyStatus2.equals("NotifyYes")) || (DaysLeft3 < 0 && notifyStatus3.equals("NotifyYes")) || (DaysLeft4 < 0 && notifyStatus4.equals("NotifyYes")) || (DaysLeft5 < 0 && notifyStatus5.equals("NotifyYes"))) {
            message = "Note: You are already late to renew the books.";
        }

        if(message != null){
            alertBox();
        }

    }

    private void alertBox() {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Please renew the books !!");
        builder.setMessage(message).setCancelable(false)

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //No button clicked

                        setFragment(new home());

                    }
                })

                .setPositiveButton("Check Due date", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Yes button clicked
                        if (checknet.checkInternet("activity_main")) {
                            setFragment(new Notification());
                        }
                    }
                });

        builder.show();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment(new home());
                    return true;
                case R.id.navigation_dashboard:
                    if (checknet.checkInternet("activity_main")) {
                        setFragment(new dashboard());
                    }
                    return true;
                case R.id.navigation_notifications:
                    if (checknet.checkInternet("activity_main")) {
                        setFragment(new Notification());
                    }
                    return true;
            }
            return false;
        }
    };

    public void setFragment(Fragment newFragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, newFragment);
        fragmentTransaction.addToBackStack("tag");
        fragmentTransaction.commit();
    }
}
