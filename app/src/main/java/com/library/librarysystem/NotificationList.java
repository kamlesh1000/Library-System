package com.library.librarysystem;


import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationList extends ArrayAdapter<String> {

    private Context context;
    private Activity activity;

    ArrayList<String> bookList = new ArrayList<String>();
    ArrayList<String> authorList = new ArrayList<String>();
    ArrayList<String> notifyList = new ArrayList<String>();
    ArrayList<String> daysLeftList = new ArrayList<String>();
    ArrayList<String> bookListPosition = new ArrayList<String>();
    ArrayList<String> indexValuePosition = new ArrayList<String>();


    Animation animListView;
    ConstraintLayout singleNotification;
    TextView books, authors, daysLefts,textview_daysLeftsText2, textview_daysLeftsText1;
    View rootView;
    Integer issuedCount, bookListPos;
    DatabaseHandler myDB;
    String notifyStatus1, notifyStatus2, notifyStatus3, notifyStatus4, notifyStatus5;
    ViewHolder holder;

    public NotificationList(Activity activity, Context context, ArrayList<String> bookList, ArrayList<String> authorList,  ArrayList<String> notifyList, ArrayList<String> daysLeftList, ArrayList<String> bookListPosition, ArrayList<String> indexValuePosition, Integer issuedCount) {
        super(context, R.layout.list_notification_single, bookList);

        this.activity=activity;
        this.context = context;
        this.bookList = bookList;
        this.authorList = authorList;
        this.notifyList = notifyList;
        this.daysLeftList = daysLeftList;
        this.issuedCount = issuedCount;
        this.bookListPosition = bookListPosition;
        this.indexValuePosition = indexValuePosition;

        myDB = new DatabaseHandler(context);
        notifyStatus1 = myDB.getNotifyStatus1();
        notifyStatus2 = myDB.getNotifyStatus2();
        notifyStatus3 = myDB.getNotifyStatus3();
        notifyStatus4 = myDB.getNotifyStatus4();
        notifyStatus5 = myDB.getNotifyStatus5();

        Log.e("apkflow","Sqlite1 : "+myDB.getNotifyStatus1());
        Log.e("apkflow","Sqlite2 : "+myDB.getNotifyStatus2());
        Log.e("apkflow","Sqlite3 : "+myDB.getNotifyStatus3());
        Log.e("apkflow","Sqlite4 : "+myDB.getNotifyStatus4());
        Log.e("apkflow","Sqlite5 : "+myDB.getNotifyStatus5());

        Log.e("apkflow","1 : "+notifyList.get(0));
        Log.e("apkflow","2 : "+notifyList.get(1));
        Log.e("apkflow","3 : "+notifyList.get(2));
        Log.e("apkflow","4 : "+notifyList.get(3));
        Log.e("apkflow","5 : "+notifyList.get(4));

    }

    static class ViewHolder {
        ImageView IVtickcross;
        Button notify;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        final ViewHolder viewHolder;


        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rootView= inflater.inflate(R.layout.list_notification_single, null, true);

            singleNotification = (ConstraintLayout) rootView.findViewById(R.id.singleNotification);
            books= (TextView) rootView.findViewById(R.id.textview_books);
            authors= (TextView) rootView.findViewById(R.id.textview_authors);
            daysLefts= (TextView) rootView.findViewById(R.id.textview_daysLefts);
            textview_daysLeftsText1 = (TextView) rootView.findViewById(R.id.textview_daysLeftsText1);
            textview_daysLeftsText2 = (TextView) rootView.findViewById(R.id.textview_daysLeftsText2);


            viewHolder = new ViewHolder();
            viewHolder.IVtickcross = (ImageView) rootView.findViewById(R.id.tickCross);
            viewHolder.notify = (Button) rootView.findViewById(R.id.notify);

            viewHolder.notify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("apkflow","button "+(position+1)+" Clicked");


                    bookListPos = Integer.parseInt(bookListPosition.get(position));

                    if (notifyList.get(position).equals("NotifyYes")) {
                        Log.e("apkflow","notifyYes Clicked");

                        switch (bookListPos) {
                            case(1):
                                Log.e("apkflow","case1");
                                myDB.updateNotifyStatus1("NotifyNo");
                                notifyList.set(Integer.parseInt(indexValuePosition.get(0)),"NotifyNo");
                                break;
                            case(2):
                                Log.e("apkflow","case2");
                                myDB.updateNotifyStatus2("NotifyNo");
                                notifyList.set(Integer.parseInt(indexValuePosition.get(1)),"NotifyNo");
                                break;
                            case(3):
                                Log.e("apkflow","case3");
                                myDB.updateNotifyStatus3("NotifyNo");
                                notifyList.set(Integer.parseInt(indexValuePosition.get(2)),"NotifyNo");
                                break;
                            case(4):
                                Log.e("apkflow","case4");
                                myDB.updateNotifyStatus4("NotifyNo");
                                notifyList.set(Integer.parseInt(indexValuePosition.get(3)),"NotifyNo");
                                break;
                            case(5):
                                Log.e("apkflow","case5");
                                myDB.updateNotifyStatus5("NotifyNo");
                                notifyList.set(Integer.parseInt(indexValuePosition.get(4)),"NotifyNo");
                                break;
                        }
                        viewHolder.IVtickcross.setImageResource(R.drawable.crosscircle);
                        viewHolder.notify.setText("Notification Disallowed");
                    }
                    else {
                        Log.e("apkflow","notifyNo Clicked");

                        switch (bookListPos) {
                            case(1):
                                Log.e("apkflow","case1");
                                myDB.updateNotifyStatus1("NotifyYes");
                                notifyList.set(Integer.parseInt(indexValuePosition.get(0)),"NotifyYes");
                                break;
                            case(2):
                                Log.e("apkflow","case2");
                                myDB.updateNotifyStatus2("NotifyYes");
                                notifyList.set(Integer.parseInt(indexValuePosition.get(1)),"NotifyYes");
                                break;
                            case(3):
                                Log.e("apkflow","case3");
                                myDB.updateNotifyStatus3("NotifyYes");
                                notifyList.set(Integer.parseInt(indexValuePosition.get(2)),"NotifyYes");
                                break;
                            case(4):
                                Log.e("apkflow","case4");
                                myDB.updateNotifyStatus4("NotifyYes");
                                notifyList.set(Integer.parseInt(indexValuePosition.get(3)),"NotifyYes");
                                break;
                            case(5):
                                Log.e("apkflow","case5");
                                myDB.updateNotifyStatus5("NotifyYes");
                                notifyList.set(Integer.parseInt(indexValuePosition.get(4)),"NotifyYes");
                                break;
                        }
                        viewHolder.IVtickcross.setImageResource(R.drawable.tick);
                        viewHolder.notify.setText("Notification Allowed");
                    }
                    Log.e("apkflow","Sqlite1 : "+myDB.getNotifyStatus1());
                    Log.e("apkflow","Sqlite2 : "+myDB.getNotifyStatus2());
                    Log.e("apkflow","Sqlite3 : "+myDB.getNotifyStatus3());
                    Log.e("apkflow","Sqlite4 : "+myDB.getNotifyStatus4());
                    Log.e("apkflow","Sqlite5 : "+myDB.getNotifyStatus5());
                    Log.e("apkflow","1 : "+notifyList.get(0));
                    Log.e("apkflow","2 : "+notifyList.get(1));
                    Log.e("apkflow","3 : "+notifyList.get(2));
                    Log.e("apkflow","4 : "+notifyList.get(3));
                    Log.e("apkflow","5 : "+notifyList.get(4));
                }
            });

            rootView.setTag(viewHolder);
        }

       holder = (ViewHolder) rootView.getTag();


        if(!bookList.get(position).equals("None")) {
            if (Integer.valueOf(daysLeftList.get(position)) < 0) {
                textview_daysLeftsText2.setText("Delayed");
            } else {
                textview_daysLeftsText2.setText("Remaining");
            }

            if (notifyList.get(position).equals("NotifyYes")) {
                holder.notify.setText("Notification Allowed");
                holder.IVtickcross.setImageResource(R.drawable.tick);
            } else {
                holder.notify.setText("Notification Disallowed");
                holder.IVtickcross.setImageResource(R.drawable.crosscircle);
            }

            books.setText(bookList.get(position));
            authors.setText(authorList.get(position));
            daysLefts.setText(daysLeftList.get(position).replace("-",""));
            textview_daysLeftsText1.setText("Days");
        }




       animListView = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.slide_left);
        animListView.setFillBefore(true);
        animListView.setFillAfter(true);
        animListView.setRepeatCount(Animation.INFINITE);
        animListView.setRepeatMode(Animation.INFINITE);
        singleNotification.startAnimation(animListView);

        return rootView;
    }

}
