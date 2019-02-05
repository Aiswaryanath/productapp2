package com.student.admin.productapp;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed8,ed9;
    Button b,b1,b2;
    String p1,p2,p3,p4,p5,p6,p7,p10,p11,p12,p13,p14,getid,getpname,getprize,getpmodel,getpsellername,getoname,getomobileno;
    Producthelper producthelper;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        builder=new AlertDialog.Builder(this);
        builder.setTitle("confirm");
        builder.setMessage("Are you sure you want to delete?");
      builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
              boolean status=producthelper.DeleteData(getid);
              if (status==true)
              {
                  Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();
              }
              else
              {
                  Toast.makeText(getApplicationContext(),"not deleted",Toast.LENGTH_LONG).show();
              }
              Toast.makeText(getApplicationContext(),"yes clicked",Toast.LENGTH_LONG).show();
              dialogInterface.dismiss();
          }
      });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"no clicked",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
            }
        });
        ed1 = (EditText) findViewById(R.id.pcode);
        ed2=(EditText)findViewById(R.id.pname);
        ed3=(EditText)findViewById(R.id.prize);
        ed4=(EditText)findViewById(R.id.pmodel);
        ed5=(EditText)findViewById(R.id.psellername);
        ed8=(EditText)findViewById(R.id.oname);
        ed9=(EditText)findViewById(R.id.omobileno);

        producthelper=new Producthelper(this);
        producthelper.getWritableDatabase();
        b=(Button)findViewById(R.id.search);
        b1=(Button)findViewById(R.id.update);
        b2=(Button)findViewById(R.id.delete);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1 = ed1.getText().toString();
                p4 = ed2.getText().toString();
                p5 = ed3.getText().toString();
                p6 = ed4.getText().toString();
                p7 = ed5.getText().toString();

                p10 = ed8.getText().toString();
                p11= ed9.getText().toString();

                Log.d("pcode",p1);

                Cursor cursor=producthelper.SearchData(p1);
                if (cursor.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_LONG).show();;
                }
                else
                {
                    while (cursor.moveToNext())
                    {
                        p2=cursor.getString(3);
                        p3=cursor.getString(5);
                        p11=cursor.getString(1);
                        p12=cursor.getString(4);
                        p13=cursor.getString(6);
                        p14=cursor.getString(7);

                       getid=cursor.getString(0);
                        Toast.makeText(getApplicationContext(),getid,Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),p2,Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),p3,Toast.LENGTH_LONG).show();

                        ed2.setText(p2);
                        ed3.setText(p3);
                        ed4.setText(p11);
                        ed5.setText(p12);
                        ed8.setText(p13);
                        ed9.setText(p14);

                    }
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpname=ed2.getText().toString();
                getprize=ed3.getText().toString();
                getpmodel=ed4.getText().toString();
                getpsellername=ed5.getText().toString();
                getoname=ed8.getText().toString();
                getomobileno=ed9.getText().toString();
                boolean status=producthelper.UpdateData(getid,getpname,getprize,getpmodel,getpsellername,getoname,getomobileno);
                if (status==true)
                {
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

    }
}
