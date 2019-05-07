package com.example.blood;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Array;
import java.util.jar.Attributes;

public class donorreg extends AppCompatActivity {
     EditText name,Age,Address,Phonenum,District,City,Username,Password;
     Button b2;
     Spinner sp;
    SQLiteDatabase db;

    String group[] = {"O+", "O-", "A+", "B+", "A-", "B-", "AB+", "AB-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donorreg);
        name = (EditText) findViewById(R.id.e1);
        Age = (EditText) findViewById(R.id.e2);
        Address = (EditText) findViewById(R.id.e3);
        Phonenum = (EditText) findViewById(R.id.e4);
        District = (EditText) findViewById(R.id.e5);
        City = (EditText) findViewById(R.id.e6);
        Username = (EditText) findViewById(R.id.e8);
        Password = (EditText) findViewById(R.id.e9);
        b2 = (Button) findViewById(R.id.b2);
        sp = (Spinner) findViewById(R.id.sp);


        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,group);
        sp.setAdapter(arrayAdapter);

        db = openOrCreateDatabase("Blood", Context.MODE_PRIVATE, null);
        if (db != null) {
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        }

        db.execSQL("CREATE TABLE IF NOT EXISTS Dreg(Name VARCHAR,Age VARCHAR,Address VARCHAR,Phonenum VARCHAR,District VARCHAR,City VARCHAR,Bloodgroup VARCHAR,username VARCHAR,password VARCHAR);");

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int pos=sp.getSelectedItemPosition();
                String text =group[pos].toString();

                if (name.getText().toString().trim().length() == 0 ||
                        Age.getText().toString().trim().length() == 0 ||
                        Address.getText().toString().trim().length() == 0 ||
                        Phonenum.getText().toString().trim().length() == 0 ||
                        District.getText().toString().trim().length() == 0 ||
                        City.getText().toString().trim().length() == 0 ||
                        Username.getText().toString().trim().length() == 0 ||
                        Password.getText().toString().trim().length() == 0 ){

                        showMessage("Error", "Please enter all values");
                return;
                }


                db.execSQL("INSERT INTO Dreg VALUES('" + name.getText() + "','" + Age.getText() + "','" + Address.getText() + "','" + Phonenum.getText() + "','" + District.getText() + "','" + City.getText() + "','" + text + "','" + Username.getText() + "','" + Password.getText() + "');");

                //db.execSQL("INSERT INTO Reg VALUES('" +name.getText()+ "','" +Age.getText()+
                       // "','" +Address.getText()+ "','" +Phonenum.getText()+ "','" +District.getText()+ "','" +City.getText()+ "','"+text+"','" +Username.getText()+ "','" +Password.getText()+ "');");

                if(db!=null) {
                    showMessage("Success", "Record added");
                    clearText();
                }
                else
                {
                    showMessage("Error", "error");
                    clearText();
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
            name.setText("");
            Age.setText("");

            Address.setText("");
            Phonenum.setText("");
            District.setText("");
            City.setText("");
            Username.setText("");
            Phonenum.setText("");
            name.requestFocus();


        }
    }




