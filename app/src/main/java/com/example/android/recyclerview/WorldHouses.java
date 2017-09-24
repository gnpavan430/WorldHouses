package com.example.android.recyclerview;

/**
 * Created by X085271 on 9/19/2017.
 */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class WorldHouses extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_houses);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.screen1){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);


                }
                else if(item.getItemId()==R.id.screen2){
                    Intent intent = new Intent(getApplicationContext(),Screen2.class);
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.screen3){
                    Intent intent = new Intent(getApplicationContext(),Screen3.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.bottom_nav_items,menu);
        return true;
    }


}
