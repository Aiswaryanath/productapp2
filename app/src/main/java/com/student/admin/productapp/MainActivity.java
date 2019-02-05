package com.student.admin.productapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    Button b,b1;
    String p1,p2,p3,p4,p5,p6,p7;
    Producthelper producthelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.pmodel);
        ed2 = (EditText) findViewById(R.id.pcode);
        ed3 = (EditText) findViewById(R.id.pname);
        ed4 = (EditText) findViewById(R.id.psellername);
        ed5 = (EditText) findViewById(R.id.prize);
        ed6 = (EditText) findViewById(R.id.oname);
        ed7 = (EditText) findViewById(R.id.omobileno);
        producthelper=new Producthelper(this);
        producthelper.getWritableDatabase();
        b = (Button) findViewById(R.id.submit);
        b1 = (Button) findViewById(R.id.search);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1 = ed1.getText().toString();
                p2 = ed2.getText().toString();
                p3 = ed3.getText().toString();
                p4 = ed4.getText().toString();
                p5 = ed5.getText().toString();
                p6 = ed6.getText().toString();
                p7 = ed7.getText().toString();
                Log.d("pmodel", p1);
                Log.d("pcode", p2);
                Log.d("pname", p3);
                Log.d("psellername", p4);
                Log.d("prize", p5);
                Log.d("oname", p6);
                Log.d("omobileno", p7);

                boolean status = producthelper.insertData(p1,p2,p3,p4,p5,p6,p7);
                if (status == true) {
                    Toast.makeText(getApplicationContext(), "successfully inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplication(),SearchActivity.class);
                startActivity(i);
            }
        });

    }}
