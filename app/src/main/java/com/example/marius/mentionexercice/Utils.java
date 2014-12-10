package com.example.marius.mentionexercice;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

public class Utils {


    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isOnline = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isOnline;
    }

    public static void insertFooter(Context context, ListView listView) {

        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.footer, listView, false);
        listView.addFooterView(footer, null, false);

    }

    public static void isNotOnline(Activity activity) {
        final ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressbar2);
        final Button button = (Button) activity.findViewById(R.id.button_reload);
        progressBar.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.populateMentionsList();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}

