package com.example.middle_test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends ListActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {
    private List<Map<String, String>> data = new ArrayList<Map<String, String>>();
    private List<Map<String, String>> data2 = new ArrayList<Map<String, String>>();

    TextView showtotalmoney;
    ListView lv;
    int sum, position;
    SQLiteDatabase db2;
    Cursor cur2;
    SimpleAdapter adapter;

    static final String db_name2 = "DB2";
    static final String tb_name2= "order2";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        showtotalmoney = (TextView) findViewById(R.id.showtotalnumber);
        db2 = openOrCreateDatabase(db_name2, Context.MODE_PRIVATE, null);
        cur2 = db2.rawQuery("SELECT * FROM " + tb_name2, null);

        lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemClickListener(this);

        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("bigname", "(未有點餐紀錄)");
        data2.add(map1);

        requery();
    }

    public void requery(){
        cur2.moveToFirst();
        if(cur2.getCount() > 0) {
            sum = 0;
            do {
                Map<String, String> map1 = new HashMap<String, String>();
                String tem = cur2.getString(1);
                switch (tem.length()){
                    case 7:
                        while(tem.length()<27) tem += " ";
                        break;
                    case 8:
                        while(tem.length()<24) tem += " ";
                        break;
                    case 10:
                        while(tem.length()<18) tem += " ";
                        break;
                }
                map1.put("bigname", tem+ "X " +cur2.getString(3));
                map1.put("smallname", cur2.getString(2));
                data.add(map1);
                adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2,
                        new String[]{"bigname", "smallname"}, new int[]{android.R.id.text1, android.R.id.text2}){

                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        text1.setTextSize(20);
                        return view;
                    };
                };
                setListAdapter(adapter);
                sum += cur2.getInt(4);
            } while (cur2.moveToNext());
        }
        else {
            adapter = new SimpleAdapter(this, data2, android.R.layout.simple_list_item_2,
                    new String[]{"bigname", "smallname"}, new int[]{android.R.id.text1, android.R.id.text2}){

                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    text1.setTextSize(20);
                    return view;
                };
            };
            setListAdapter(adapter);
            sum = 0;
        }

        showtotalmoney.setText("總金額 : "+sum+"元");
    }

    public void over(View v){
        Intent itdone = new Intent(this, MainActivity3.class);
        startActivity(itdone);
    }

    public void back(View v){
        Intent itb = new Intent(this, MainActivity.class);
        startActivity(itb);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int where, long l) {
        position = where;
        TextView txv = view.findViewById(android.R.id.text1);
        String tem = txv.getText().toString();
        if(!tem.equals("(未有點餐紀錄)")){
            new AlertDialog.Builder(this)
                    .setMessage("是否要刪除選取的餐點？")
                    .setNegativeButton("是", this)
                    .setPositiveButton("否", null)
                    .setCancelable(false).show();
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == DialogInterface.BUTTON_NEGATIVE){
            data.clear();
            cur2.moveToPosition(position);
            db2.delete(tb_name2, "_id="+cur2.getInt(0), null);
            cur2 = db2.rawQuery("SELECT * FROM " + tb_name2, null);
            requery();
        }
    }
}