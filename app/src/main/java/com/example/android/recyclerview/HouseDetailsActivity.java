package com.example.android.recyclerview;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Database.DbHelper;

public class HouseDetailsActivity extends AppCompatActivity {

    TextView nameTextView,descriptionTextView,latTextView,longTextView,cityTextView;
    DbHelper dbHelper;
    Button mapButton;
    static String latitude;
    static String longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        dbHelper=new DbHelper(getApplicationContext());
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title=(TextView)findViewById(getResources().getIdentifier("action_bar_title","id",getPackageName()));
        title.setText("House Details");
        int extraValue=getIntent().getIntExtra("Extra name",1)+1;
        Log.i("Extra value is",""+extraValue);
        nameTextView=(TextView)findViewById(R.id.nameTextView);
        descriptionTextView=(TextView)findViewById(R.id.descriptionTextView);
        latTextView=(TextView)findViewById(R.id.latTextView);
        longTextView=(TextView)findViewById(R.id.longTextView);
        cityTextView=(TextView)findViewById(R.id.cityTextView);
        nameTextView.setText(extraValue+"");
        mapButton=(Button)findViewById(R.id.mapButton);
        final Cursor cursor = dbHelper.getSpecificData(extraValue);
//        Log.i("Cursor Element",cursor.getString(0)+"");
        while(cursor.moveToNext()){
            Log.i("Cursor element",cursor.getString(1)+""+cursor.getString(0)+cursor.getString(2)+cursor.getString(3)+cursor.getString(4)+cursor.getString(5));
            latitude=cursor.getString(3);
            longitude=cursor.getString(4);
            nameTextView.setText(cursor.getString(1));
            descriptionTextView.setText(cursor.getString(2));
            latTextView.setText(cursor.getString(3));
            longTextView.setText(cursor.getString(4));
            cityTextView.setText(cursor.getString(5));
        }
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String geoMap= "geo:"+latitude+","+longitude;
                Uri geoLocation = Uri.parse(geoMap);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(geoLocation);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
