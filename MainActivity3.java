package com.example.middle_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity3 extends AppCompatActivity {

    SQLiteDatabase db2;
    Cursor cur2;

    static final String db_name2 = "DB2";
    static final String tb_name2= "order2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        db2 = openOrCreateDatabase(db_name2, Context.MODE_PRIVATE, null);
        cur2 = db2.rawQuery("SELECT * FROM " + tb_name2, null);

        while(cur2.getCount() != 0){
            cur2.moveToFirst();
            db2.delete(tb_name2, "_id=" + cur2.getInt(0), null);
            cur2 = db2.rawQuery("SELECT * FROM " + tb_name2, null);
        }
        db2.close();
    }

    public void back(View v){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}