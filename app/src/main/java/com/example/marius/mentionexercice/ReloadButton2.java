package com.example.marius.mentionexercice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ReloadButton2 extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reload_button2);



        Button b = (Button) findViewById(R.id.reload);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("click", "sur le button");
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                finish();

                startActivity(startIntent);
            }
        });
    }
}
