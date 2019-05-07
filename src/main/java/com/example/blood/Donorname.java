package com.example.blood;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Donorname extends AppCompatActivity {


    ListView lv;
    SQLiteDatabase db;
    ArrayList<String> name=new ArrayList<String>();
   ArrayList<String> ph=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donorname);
        lv=(ListView)findViewById(R.id.lv);



        db = openOrCreateDatabase("Blood", Context.MODE_PRIVATE, null);
        if (db != null) {
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        }

        db.execSQL("CREATE TABLE IF NOT EXISTS Dreg(Name VARCHAR,Age VARCHAR,Address VARCHAR,Phonenum VARCHAR,District VARCHAR,City VARCHAR,Bloodgroup VARCHAR,username VARCHAR,password VARCHAR);");



        Intent in=getIntent();
        String text=in.getStringExtra("Bloodgroup");
        String text1=in.getStringExtra("District");
        String text2=in.getStringExtra("City");
        Cursor c = db.rawQuery("SELECT * FROM Dreg WHERE BloodGroup='"+text+"' and District='"+text1+"'and City='"+text2+"'" ,null);

        while(c.moveToNext())
        {
            String nm=c.getString(0);
            name.add(nm);
            String p = c.getString(3);
            ph.add(p);
        }

      ArrayAdapter<String> arrayAdapter  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //Toast.makeText(getApplicationContext(),"Name is "+name.get(position),Toast.LENGTH_LONG).show();
               Intent in=new Intent(Donorname.this,viewlist.class);
                in.putExtra("pos",position);
               in.putExtra("name",name.get(position));
               in.putExtra("ph",ph.get(position));
               startActivity(in);

            }
        });

    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}



