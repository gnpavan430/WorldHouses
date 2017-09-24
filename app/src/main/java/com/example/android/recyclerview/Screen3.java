package com.example.android.recyclerview;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Database.DbHelper;

public class Screen3 extends AppCompatActivity  {
    TextView nameTextView,descriptionTextView,latTextView,longTextView,cityTextView;
    DbHelper dbHelper;
    Button mapButton;
    static String latitude;
    static String longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);
        dbHelper = new DbHelper(getApplicationContext());
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        TextView title = (TextView) findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));
        title.setText("Information");

    }

}
