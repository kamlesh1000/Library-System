package com.library.librarysystem;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {

    private final Context context;
    private final Activity activity;
    private final String[] id,title,author,section;

    Animation animListView;
    LinearLayout singleRecord;
    TextView ids, titles, authors,sections;
    View rowView;

    public CustomList(Activity activity, Context context, String[] id, String[] title, String[] author, String[] section) {
        super(context, R.layout.list_single, id);

        this.activity=activity;
        this.context = context;
        this.id = id;
        this.title = title;
        this.author = author;
        this.section = section;

    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        rowView= inflater.inflate(R.layout.list_single, null, true);

        singleRecord = (LinearLayout) rowView.findViewById(R.id.singleRecord);



        ids= (TextView) rowView.findViewById(R.id.textview_id);
        titles= (TextView) rowView.findViewById(R.id.textview_title);
        authors= (TextView) rowView.findViewById(R.id.textview_author);
        sections= (TextView) rowView.findViewById(R.id.textview_section);

        ids.setText(id[position]);
        titles.setText(title[position]);
        authors.setText(author[position]);
        sections.setText(section[position]);



        animListView = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.slide_left);
        animListView.setFillBefore(true);
        animListView.setFillAfter(true);
        animListView.setRepeatCount(Animation.INFINITE);
        animListView.setRepeatMode(Animation.INFINITE);
        singleRecord.startAnimation(animListView);

        return rowView;
    }

}
