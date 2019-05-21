package com.library.librarysystem;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {

        Log.e("apkflow","MyFirebaseInstanceIDService Started");

    }
}
