package com.example.marius.mentionexercice;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// Bien essayé d'avoir créer une classe mais elle aurait dû s'appeler Utils et contenir plusieurs méthodes static "utilitaires"
// Tu pourrais faire comme suit : Utils.isOnline()...
public class CheckInternet {

    private Context mContext;

    public CheckInternet(Context context){
        this.mContext = context;
    }

    // public static boolean isOnline() ...
    public boolean isConnected(){
    ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    boolean isConnected = activeNetwork != null &&
            activeNetwork.isConnectedOrConnecting();

        return  isConnected;
    }


}


