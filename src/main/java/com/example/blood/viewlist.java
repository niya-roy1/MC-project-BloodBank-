package com.example.blood;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class viewlist extends AppCompatActivity {
    TextView name;
    ImageButton img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewlist);

     name=(TextView)findViewById(R.id.name);
     img=(ImageButton)findViewById(R.id.img);


        Intent in=getIntent();
        String nam = in.getStringExtra("name");
        final String phn= in.getStringExtra("ph");

        name.setText(nam);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent in =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phn));
            startActivity(in);

            }
        });

    }
}
