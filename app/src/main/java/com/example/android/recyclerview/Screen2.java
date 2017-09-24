package com.example.android.recyclerview;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Database.DbHelper;

public class Screen2 extends AppCompatActivity {
    TextView title,textView;
    Button insertButton,viewAllButton,updateButton;
    EditText editText1,editText2,editText3,editText4,editText5;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        editText1=(EditText) findViewById(R.id.editText1);
        editText2=(EditText) findViewById(R.id.editText2);
        editText3=(EditText) findViewById(R.id.editText3);
        editText4=(EditText)findViewById(R.id.editText4);
        editText5=(EditText)findViewById(R.id.editText5);

        updateButton=(Button) findViewById(R.id.updateButton);
        textView=(TextView) findViewById(R.id.displayResultsTextView);
        viewAllButton=(Button) findViewById(R.id.viewAllButton);
        dbHelper=new DbHelper(getApplicationContext());


        //getSupportActionBar().setTitle("Screen2");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        title=(TextView)findViewById(getResources().getIdentifier("action_bar_title","id",getPackageName()));
        title.setText("Add House");
        insertButton=(Button)findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inserted=dbHelper.insertData(dbHelper.Table_Name,editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString(),editText5.getText().toString());
                if(inserted){
                    Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                    editText5.setText("");

                }
                else{
                    Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_LONG).show();
                }


            }
        });
        viewAllData();
        updateData();

    }
    public void viewAllData(){
        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = dbHelper.getAllData(dbHelper.MyHouses_Table);
                if(res.getCount()==0){
                    textView.setText("No Results Found");
                }
                else{
                    StringBuffer stringBuffer=new StringBuffer();
                    while(res.moveToNext()){
                        stringBuffer.append("\n\n"+"ID:"+res.getString(0)+"\n"+"Name:"+res.getString(1)+"\n"+"Surname:"+res.getString(2)+"\n"+"Marks:"+res.getString(3));


                    }
                    textView.setText(stringBuffer.toString());
                }
            }
        });

    }
    public void updateData(){
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // boolean updated=dbHelper.updateData("4","Pavan","Kumar","100");
            }
        });
    }

}
