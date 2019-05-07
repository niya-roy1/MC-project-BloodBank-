package com.example.blood;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class recivelist extends AppCompatActivity {
    Spinner BloodGroup,District,City;
    Button b3;
    SQLiteDatabase db;
    String group[] = {"O+", "O-", "A+", "B+", "A-", "B-", "AB+", "AB-"};
    String group1[] = {"Ernakulam", "Kottayam", "Palakkad", "Kozhikode", "Trivandrum"};
    String group2[] = {"Kaloor", "Changanacherry","Manarcard", "Mukkam", "Kazhakootam"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recivelist);
        BloodGroup=(Spinner)findViewById(R.id.sp1);
       District=(Spinner)findViewById(R.id.sp2);
        City=(Spinner)findViewById(R.id.sp3);
       b3=(Button) findViewById(R.id.b3);
        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,group);
        BloodGroup.setAdapter(arrayAdapter);
        ArrayAdapter<String> arrayAdapter1 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,group1);
        District.setAdapter(arrayAdapter1);
        ArrayAdapter<String> arrayAdapter2 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,group2);
        City.setAdapter(arrayAdapter2);
        db = openOrCreateDatabase("Blood", Context.MODE_PRIVATE, null);
        if (db != null) {
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        }

        db.execSQL("CREATE TABLE IF NOT EXISTS Dreg(Name ,Age VARCHAR,Address VARCHAR,Phonenum VARCHAR,District VARCHAR,City VARCHAR,Bloodgroup VARCHAR,username VARCHAR,password VARCHAR);");

        b3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int pos=BloodGroup.getSelectedItemPosition();
               String text =group[pos].toString();
               int pos1=District.getSelectedItemPosition();
               String text1 =group1[pos1].toString();int pos2=City.getSelectedItemPosition();
               String text2 =group2[pos2].toString();
              // Cursor c = db.rawQuery("SELECT * FROM Dreg WHERE BloodGroup='"+text+"' and District='"+text1+"'and City='"+text2+"'" ,null);
             // while (c.moveToFirst())
              // {
                   Intent in=new Intent(recivelist.this,Donorname.class);
                   in.putExtra("Bloodgroup",text);
                   in.putExtra("District",text1);
                   in.putExtra("City",text2);
                   startActivity(in);



               //}


           }
       });

        }


        }

