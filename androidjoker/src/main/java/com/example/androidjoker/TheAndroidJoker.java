package com.example.androidjoker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by sam on 1/25/18.
 */

public class TheAndroidJoker extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_joker);

        String jokeFromJava;


        Intent intent = getIntent();
        jokeFromJava = intent.getStringExtra("jokefromcloud");

        TextView textview = findViewById(R.id.android_joker);

        textview.setText(jokeFromJava);

    }

}









