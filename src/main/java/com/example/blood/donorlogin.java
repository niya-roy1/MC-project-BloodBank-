package com.example.blood;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class donorlogin extends AppCompatActivity {

    EditText uname,pass;
    Button b;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donorlogin);

        uname=(EditText)findViewById(R.id.uname);
        pass=(EditText)findViewById(R.id.pass);

        b=(Button)findViewById(R.id.b);

        db = openOrCreateDatabase("Blood", Context.MODE_PRIVATE, null);
        if (db != null) {
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        }

        db.execSQL("CREATE TABLE IF NOT EXISTS Dreg(Name VARCHAR,Age VARCHAR,Address VARCHAR,Phonenum VARCHAR,District VARCHAR,City VARCHAR,Bloodgroup VARCHAR,username VARCHAR,password VARCHAR);");



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor c = db.rawQuery("SELECT * FROM Dreg WHERE username='"+uname.getText()+"' and password='"+pass.getText()+"'", null);
                final String un= uname.getText().toString();
                if (c.getCount() == 0)
                {
                    showMessage("Error", "Invalid Login!");
                    clearText();
                    return;
                }
                else
                {
                    showMessage("Success", "Login Success !");
                    clearText();
                    Intent in=new Intent(donorlogin.this,donormod.class);
                    in.putExtra("uname",un);
                    startActivity(in);
                    return;
                }

            }
        });

    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



    public void clearText() {
       uname.setText("");
        pass.setText("");



    }


}



