package com.example.blood;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class donormod extends AppCompatActivity {
    Button update;
    EditText edit, phonenum, Add, city, district;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donormod);
        update = (Button) findViewById(R.id.update);
        //delete = (Button) findViewById(R.id.delete);
        edit = (EditText) findViewById(R.id.name);
        phonenum = (EditText) findViewById(R.id.phonenum);
        Add = (EditText) findViewById(R.id.Add);
        city = (EditText) findViewById(R.id.city);
        district = (EditText) findViewById(R.id.district);
        Intent i = getIntent();
        final String ee = i.getStringExtra("uname");


        db = openOrCreateDatabase("Blood", Context.MODE_PRIVATE, null);
        if (db != null) {
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        }

        db.execSQL("CREATE TABLE IF NOT EXISTS Dreg(Name VARCHAR,Age VARCHAR,Address VARCHAR,Phonenum VARCHAR,District VARCHAR,City VARCHAR,Bloodgroup VARCHAR,username VARCHAR,password VARCHAR);");


        Cursor c = db.rawQuery("SELECT * FROM Dreg WHERE username ='" + ee + "'", null);
        if (c.moveToFirst()) {
            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
            edit.setText(c.getString(0));
            phonenum.setText(c.getString(3));
            Add.setText(c.getString(2));
            city.setText(c.getString(5));
            district.setText(c.getString(4));

        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na = edit.getText().toString();
                String pp = phonenum.getText().toString();
                String ad = Add.getText().toString();
                String ds = district.getText().toString();
                String ct = city.getText().toString();

                db.execSQL("UPDATE Dreg SET Name='" + na + "',Phonenum ='" + pp + "',Address='" + ad + "',District='" + ds + "',City='" + ct + "' WHERE username='" + ee + "' ");

                showMessage("Success", "Record Modified");
                clearText();

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

    public void clearText() {
        edit.setText("");
        Add.setText("");
        phonenum.setText("");
        district.setText("");
        city.setText("");




    }



}



