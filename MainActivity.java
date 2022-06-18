package com.example.middle_test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txv1, txv2, txv3, txv4, txv5, txv6, txv7, txv8, txv9, txv10, txv11, txv12;
    int i;
    String name1, name2, name3;
    Button button1;
    SQLiteDatabase db, db2;
    Cursor c, cur2;
    static final String db_name = "DB";
    static final String tb_name = "order1";
    static final String db_name2 = "DB2";
    static final String tb_name2= "order2";

    String name[] = {"湯烏龍麵", "湯烏龍麵", "豚骨烏龍麵", "豚骨烏龍麵", "釜揚烏龍麵", "釜揚烏龍麵",
                     "豆皮烏龍麵", "豆皮烏龍麵", "牛肉烏龍麵", "牛肉烏龍麵", "溫玉濃湯烏龍麵", "溫玉濃湯烏龍麵",
                     "香辣豚骨烏龍麵", "香辣豚骨烏龍麵", "牛肉蛋拌烏龍麵", "牛肉蛋拌烏龍麵", "明太蛋拌烏龍麵",
                     "明太蛋拌烏龍麵", "番茄雞肉烏龍麵", "番茄雞肉烏龍麵", "泰式豬肉烏龍麵",
                     "泰式豬肉烏龍麵", "時蔬豬肉烏龍麵", "時蔬豬肉烏龍麵",};
    String size[] = {"小", "大", "小", "大", "小", "大", "小", "大", "小", "大", "小", "大",
                     "小", "大", "小", "大", "小", "大", "小", "大", "小", "大", "小", "大"};
    Integer money[] = {69, 99, 129, 159, 69, 99, 109, 139, 134, 164, 89, 119,
                       139, 169, 124, 154, 104, 134, 129, 159, 104, 134, 124, 154};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS "+ tb_name
                +"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name VARCHAR(32),"+ "size VARCHAR(32),"
                + "money VARCHAR(32))";
        db.execSQL(sql);
        c = db.rawQuery("SELECT * FROM "+ tb_name, null);
        if(c.getCount() == 0) for(i = 0; i<24; i++) addData(name[i], size[i], money[i]);
        db.close();

        db2 = openOrCreateDatabase(db_name2, Context.MODE_PRIVATE, null);
        String sql2 = "CREATE TABLE IF NOT EXISTS "+ tb_name2 +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "bigname VARCHAR(32),"
                + "smallname VARCHAR(32),"
                + "number VARCHAR(32),"
                + "money VARCHAR(32))";
        db2.execSQL(sql2);
        cur2 = db2.rawQuery("SELECT * FROM " + tb_name2, null);

        button1 = (Button)findViewById(R.id.button);
        txv1 = (TextView) findViewById(R.id.order1);
        txv2 = (TextView) findViewById(R.id.order2);
        txv3 = (TextView) findViewById(R.id.order3);
        txv4 = (TextView) findViewById(R.id.order4);
        txv5 = (TextView) findViewById(R.id.order5);
        txv6 = (TextView) findViewById(R.id.order6);
        txv7 = (TextView) findViewById(R.id.order7);
        txv8 = (TextView) findViewById(R.id.order8);
        txv9 = (TextView) findViewById(R.id.order9);
        txv10 = (TextView) findViewById(R.id.order10);
        txv11 = (TextView) findViewById(R.id.order11);
        txv12 = (TextView) findViewById(R.id.order12);

        //setOnClickListener set
        txv1.setOnClickListener(this);txv2.setOnClickListener(this);txv3.setOnClickListener(this);txv4.setOnClickListener(this);
        txv5.setOnClickListener(this);txv6.setOnClickListener(this);txv7.setOnClickListener(this);txv8.setOnClickListener(this);
        txv9.setOnClickListener(this);txv10.setOnClickListener(this);txv11.setOnClickListener(this);txv12.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    private void addData(String name, String size, Integer money){
        ContentValues cv = new ContentValues(3);
        cv.put("name", name);
        cv.put("size", size);
        cv.put("money", money);

        db.insert(tb_name, null, cv);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){
        Intent it1 = new Intent(this, MainActivity1.class);

        switch (v.getId()){
            case R.id.order1:
                it1.putExtra("位置",0);
                startActivity(it1);
                break;
            case R.id.order2:
                it1.putExtra("位置",1);
                startActivity(it1);
                break;
            case R.id.order3:
                it1.putExtra("位置",2);
                startActivity(it1);
                break;
            case R.id.order4:
                it1.putExtra("位置",3);
                startActivity(it1);
                break;
            case R.id.order5:
                it1.putExtra("位置",4);
                startActivity(it1);
                break;
            case R.id.order8:
                it1.putExtra("位置",5);
                startActivity(it1);
                break;
            case R.id.order7:
                it1.putExtra("位置",6);
                startActivity(it1);
                break;
            case R.id.order6:
                it1.putExtra("位置",7);
                startActivity(it1);
                break;
            case R.id.order9:
                it1.putExtra("位置",8);
                startActivity(it1);
                break;
            case R.id.order10:
                it1.putExtra("位置",9);
                startActivity(it1);
                break;
            case R.id.order11:
                it1.putExtra("位置",10);
                startActivity(it1);
                break;
            case R.id.order12:
                it1.putExtra("位置",11);
                startActivity(it1);
                break;
            case R.id.button:
                Intent it2 = new Intent(this, MainActivity2.class);
                startActivity(it2);
        }
    }
}