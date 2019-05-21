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


public class profile extends Fragment {
    View root;
    Activity activity;
    Context context;
    DatabaseHandler myDB;
    String studentId, studentName, studentPassword, studentRollNo, studentBatch, studentProgram;

    TextView TVname, TVid, TVpassword, TVrollNo, TVbatch, TVprogram;

    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        activity  = getActivity();
        context = getContext();

        TVname = (TextView) root.findViewById(R.id.nameValue);
        TVid = (TextView) root.findViewById(R.id.idValue);
        TVpassword = (TextView) root.findViewById(R.id.passwordValue);
        TVrollNo = (TextView) root.findViewById(R.id.rollNoValue);
        TVbatch = (TextView) root.findViewById(R.id.batchValue);
        TVprogram = (TextView) root.findViewById(R.id.programValue);


        myDB = new DatabaseHandler(context);
        studentId = myDB.getStudentId();
        studentName = myDB.getStudentName();
        studentPassword = myDB.getStudentPassword();
        studentRollNo = myDB.getStudentRollNo();
        studentBatch = myDB.getStudentBatch();
        studentProgram = myDB.getStudentProgram();

        Log.e("apkflow",studentId);
        Log.e("apkflow",studentName);
        Log.e("apkflow",studentPassword);
        Log.e("apkflow",studentRollNo);
        Log.e("apkflow",studentBatch);
        Log.e("apkflow",studentProgram);

        TVname.setText(studentName);
        TVid.setText(studentId);
        TVpassword.setText(studentPassword);
        TVrollNo.setText(studentRollNo);
        TVbatch.setText(studentBatch);
        TVprogram.setText(studentProgram);



        return  root;
    }
}
