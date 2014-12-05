package com.example.marius.mentionexercice;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternet {

    private Context mContext;

    public CheckInternet(Context context){
        this.mContext = context;
    }


    public boolean isConnected(){
    ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    boolean isConnected = activeNetwork != null &&
            activeNetwork.isConnectedOrConnecting();

        return  isConnected;
    }


}


