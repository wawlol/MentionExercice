package com.example.marius.mentionexercice;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isOnline = activeNetwork != null &&
            activeNetwork.isConnectedOrConnecting();

        return isOnline;
    }
}