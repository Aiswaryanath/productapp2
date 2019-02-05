package com.student.admin.productapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 1/30/2019.
 */
public class Producthelper extends SQLiteOpenHelper {
    public static final String Dbname = "MyP.db";
    public static final String TableName = "Product";
    public static final String col1 = "id";
    public static final String col2 = "pmodel";
    public static final String col3 = "pcode";
    public static final String col4 = "pname";
    public static final String col5 = "psellername";
    public static final String col6 = "prize";
    public static final String col7 = "oname";
    public static final String col8 = "omobileno";


    public Producthelper(Context context) {
        super(context, Dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + TableName + "(" + col1 + " integer primary key autoincrement," + col2 + " text," + col3 + " text," + col4 + " text," + col5 + " text," + col6 + " text," + col7 + " text," + col8 + " text)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "drop table if exists " + TableName;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String pmodel, String pcode, String pname, String psellername, String prize, String oname, String omobileno) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, pmodel);
        contentValues.put(col3, pcode);
        contentValues.put(col4, pname);
        contentValues.put(col5, psellername);
        contentValues.put(col6, prize);
        contentValues.put(col7, oname);
        contentValues.put(col8, omobileno);


        long status=sqLiteDatabase.insert(TableName,null,contentValues);
        if (status==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor SearchData(String pcode)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TableName + " WHERE " + col3 + "='" + pcode + "'", null);
        return cursor;
    }
    public boolean UpdateData(String id,String pmodel,String pname,String psellername,String prize,String oname,String omobileno){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,pmodel);
        contentValues.put(col4,pname);
        contentValues.put(col5,psellername);
        contentValues.put(col6,prize);
        contentValues.put(col7,oname);
        contentValues.put(col8,omobileno);
        long status=db.update(TableName,contentValues,col1+"="+id,null);
        if (status==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean DeleteData(String pcode){
        SQLiteDatabase db=this.getWritableDatabase();
        long status=db.delete(TableName,col1+"="+pcode,null);
        if (status==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}