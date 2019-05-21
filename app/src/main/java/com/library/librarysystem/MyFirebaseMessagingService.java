package com.library.librarysystem;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e("apkflow","onMessageReceived Started");

        Map<String, String> data = remoteMessage.getData();
        String val1 = data.get("myKey1");
        String val2 = data.get("myKey2");

        Log.e("apkflow","val1=" + val1 + " val2=" + val2);

    }


}
