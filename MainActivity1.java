package com.example.middle_test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener,
                                                CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener{
    TextView txvcheck,txvname, txvsmall, txvbig, totalnumber;
    int smallvalueI, bigvalue, sizemoney, totalmoney, sumtotal, count = 1, tempicture, position, position2;
    String name, bigname, smallname;
    String[] addthings={"炸地瓜         25 元", "炸青椒         25 元", "炸竹輪         30 元",
                        "炸蔬菜         30 元", "蔬菜可麗餅 30 元", "炸蝦             35 元",
                        "炸雞             35 元", "炸白身魚     35 元", "鮭魚飯糰     30 元",
                        "牛肉飯糰     35 元","豆皮壽司     25 元", "明太子飯糰 35 元",
                        "溫泉蛋         20 元", "滷蛋             20 元", "白飯             20 元",
                        "豆皮             40 元", "燙青菜         40 元", "豬肉             35 元",
                        "牛肉             65 元"};
    int[] addthingsmoney = {25, 25, 30, 30, 30, 35, 35, 35, 30, 35, 25, 35, 20, 20, 20, 40, 40, 35, 65};
    ArrayList<CompoundButton> selected = new ArrayList<>();
    ImageView imv1, imv2, imv3, imv4, imv5, imv6, imv7, imv8, imv9, imv10, imv11, imv12,
              ord1, ord2, ord3, ord4, ord5, ord6, ord7, ord8, ord9, ord10, ord11, ord12,
              imvplus, imvminus;
    RadioGroup pictureselect, size;
    SQLiteDatabase db,db2;
    Cursor cur, cur2;

    static final String db_name = "DB";
    static final String tb_name = "order1";
    static final String db_name2 = "DB2";
    static final String tb_name2= "order2";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
        db2 = openOrCreateDatabase(db_name2, Context.MODE_PRIVATE, null);

        cur = db.rawQuery("SELECT * FROM " + tb_name, null);
        cur2 = db2.rawQuery("SELECT * FROM " + tb_name2, null);

        Intent it = getIntent();
        position2 = cur2.getCount();
        position = it.getIntExtra("位置", 0);
        sumtotal = it.getIntExtra("sum", 0);
        txvname = (TextView) findViewById(R.id.Name);
        cur.moveToPosition(position*2);
        name = cur.getString(1);
        txvname.setText(name);
        smallvalueI = cur.getInt(3);
        cur.moveToNext();
        bigvalue = cur.getInt(3);

        pictureselect = (RadioGroup) findViewById(R.id.radiogroup1);
        size = (RadioGroup)findViewById(R.id.size);
        size.setOnCheckedChangeListener(this);
        totalnumber = (TextView)findViewById(R.id.totalnumber);
        txvsmall = (TextView)findViewById(R.id.smallsize);
        txvbig = (TextView) findViewById(R.id.bigsize);
        txvcheck = (TextView) findViewById(R.id.button7);
        txvbig.setText("大 "+ bigvalue +" 元");
        txvsmall.setText("小 "+smallvalueI+" 元");

        int[] chk_id = {R.id.checkBox1, R.id.checkBox2, R.id.checkBox3, R.id.checkBox4,
                        R.id.checkBox5, R.id.checkBox6, R.id.checkBox7, R.id.checkBox8,
                        R.id.checkBox9, R.id.checkBox10, R.id.checkBox11, R.id.checkBox12,
                        R.id.checkBox13, R.id.checkBox14, R.id.checkBox15, R.id.checkBox16,
                        R.id.checkBox17, R.id.checkBox18, R.id.checkBox19};
        for(int id:chk_id){
            CheckBox chk = (CheckBox) findViewById(id);
            chk.setOnCheckedChangeListener(this);
        }

        imv1 = findViewById(R.id.soup);
        imv2 = findViewById(R.id.axeyang);
        imv3 = findViewById(R.id.beef);
        imv4 = findViewById(R.id.hottonkotsu);
        imv5 = findViewById(R.id.mentaiko);
        imv6 = findViewById(R.id.thaipork);
        imv7 = findViewById(R.id.tonkotsu);
        imv8 = findViewById(R.id.tofuskin);
        imv9 = findViewById(R.id.beefegg);
        imv10 = findViewById(R.id.cornsoup);
        imv11 = findViewById(R.id.tomatochicken);
        imv12 = findViewById(R.id.vegetablepork);
        ord1 = findViewById(R.id.frenchfry);
        ord2 = findViewById(R.id.frydisgust);
        ord3 = findViewById(R.id.frywheel);
        ord4 = findViewById(R.id.fryfruit);
        ord5 = findViewById(R.id.cokecookie);
        ord6 = findViewById(R.id.frysrimp);
        ord7 = findViewById(R.id.frychicken);
        ord8 = findViewById(R.id.fryfish);
        ord9 = findViewById(R.id.fishrice);
        ord10 = findViewById(R.id.beefice);
        ord11 = findViewById(R.id.skinsusi);
        ord12 = findViewById(R.id.eggsusi);
        imvplus = findViewById(R.id.plus);
        imvminus = findViewById(R.id.minus);

        imvplus.setOnClickListener(this);
        imvminus.setOnClickListener(this);

        showpicture();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.plus){
            totalnumber.setText(""+ ++count);
        }
        else if(v.getId() == R.id.minus){
            if(count > 1)
                totalnumber.setText(""+ --count);
        }
    }

    public void check(View v){
        if(tempicture != pictureselect.getCheckedRadioButtonId())
            closepicture(tempicture);
        switch (pictureselect.getCheckedRadioButtonId()) {
            case R.id.radioButton9:
                ord1.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton10:
                ord2.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton11:
                ord3.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton12:
                ord4.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton13:
                ord5.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton14:
                ord6.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton15:
                ord7.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton16:
                ord8.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton17:
                ord9.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton18:
                ord10.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton19:
                ord11.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton20:
                ord12.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButton21:
                showpicture();
                break;
        }
        tempicture = pictureselect.getCheckedRadioButtonId();
    }

    public void closepicture(int tempicture){
        switch (tempicture) {
            case R.id.radioButton9:
                ord1.setVisibility(View.GONE);
                break;
            case R.id.radioButton10:
                ord2.setVisibility(View.GONE);
                break;
            case R.id.radioButton11:
                ord3.setVisibility(View.GONE);
                break;
            case R.id.radioButton12:
                ord4.setVisibility(View.GONE);
                break;
            case R.id.radioButton13:
                ord5.setVisibility(View.GONE);
                break;
            case R.id.radioButton14:
                ord6.setVisibility(View.GONE);
                break;
            case R.id.radioButton15:
                ord7.setVisibility(View.GONE);
                break;
            case R.id.radioButton16:
                ord8.setVisibility(View.GONE);
                break;
            case R.id.radioButton17:
                ord9.setVisibility(View.GONE);
                break;
            case R.id.radioButton18:
                ord10.setVisibility(View.GONE);
                break;
            case R.id.radioButton19:
                ord11.setVisibility(View.GONE);
                break;
            case R.id.radioButton20:
                ord12.setVisibility(View.GONE);
                break;
            case R.id.radioButton21:
                closepicture1();
                break;
        }
    }

    public void showpicture(){
        switch (txvname.getText().toString()){
            case "湯烏龍麵":
                imv1.setVisibility(View.VISIBLE);
                break;
            case "釜揚烏龍麵":
                imv2.setVisibility(View.VISIBLE);
                break;
            case "牛肉烏龍麵":
                imv3.setVisibility(View.VISIBLE);
                break;
            case "香辣豚骨烏龍麵":
                imv4.setVisibility(View.VISIBLE);
                break;
            case "明太蛋拌烏龍麵":
                imv5.setVisibility(View.VISIBLE);
                break;
            case "泰式豬肉烏龍麵":
                imv6.setVisibility(View.VISIBLE);
                break;
            case "豚骨烏龍麵":
                imv7.setVisibility(View.VISIBLE);
                break;
            case "豆皮烏龍麵":
                imv8.setVisibility(View.VISIBLE);
                break;
            case "牛肉蛋拌烏龍麵":
                imv9.setVisibility(View.VISIBLE);
                break;
            case "溫玉濃湯烏龍麵":
                imv10.setVisibility(View.VISIBLE);
                break;
            case "番茄雞肉烏龍麵":
                imv11.setVisibility(View.VISIBLE);
                break;
            case "時蔬豬肉烏龍麵":
                imv12.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void closepicture1(){
        switch (txvname.getText().toString()){
            case "湯烏龍麵":
                imv1.setVisibility(View.GONE);
                break;
            case "釜揚烏龍麵":
                imv2.setVisibility(View.GONE);
                break;
            case "牛肉烏龍麵":
                imv3.setVisibility(View.GONE);
                break;
            case "香辣豚骨烏龍麵":
                imv4.setVisibility(View.GONE);
                break;
            case "明太蛋拌烏龍麵":
                imv5.setVisibility(View.GONE);
                break;
            case "泰式豬肉烏龍麵":
                imv6.setVisibility(View.GONE);
                break;
            case "豚骨烏龍麵":
                imv7.setVisibility(View.GONE);
                break;
            case "豆皮烏龍麵":
                imv8.setVisibility(View.GONE);
                break;
            case "牛肉蛋拌烏龍麵":
                imv9.setVisibility(View.GONE);
                break;
            case "溫玉濃湯烏龍麵":
                imv10.setVisibility(View.GONE);
                break;
            case "番茄雞肉烏龍麵":
                imv11.setVisibility(View.GONE);
                break;
            case "時蔬豬肉烏龍麵":
                imv12.setVisibility(View.GONE);
                break;
        }
    }

    public void button(View v){
        if(size.getCheckedRadioButtonId() == R.id.smallsize | size.getCheckedRadioButtonId() == R.id.bigsize) {
            Intent it2 = new Intent(this, MainActivity2.class);
            for (CompoundButton chk : selected) {
                if(smallname == null) smallname = chk.getText().toString().split(" ")[0];
                else smallname += ", "+chk.getText().toString().split(" ")[0];
            }
            for (int i = 0; i < selected.size(); i++)
                for (int j = 0; j < 19; j++)
                    if (selected.get(i).getText().equals(addthings[j])) {
                        totalmoney += addthingsmoney[j];
                        break;
                    }
            totalmoney = (totalmoney + sizemoney) * count;
            addData(bigname, smallname, count, totalmoney);
            startActivity(it2);
        }
    }

    public void back(View v){
        Intent itb = new Intent(this, MainActivity.class);
        startActivity(itb);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked) selected.add(compoundButton);
        else selected.remove(compoundButton);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(size.getCheckedRadioButtonId() == R.id.smallsize){
            bigname = name+"(小)";
            sizemoney = smallvalueI;
        }
        else {
            bigname = name+"(大)";
            sizemoney = bigvalue;
        }
    }

    private void addData(String name, String size, Integer number, Integer money){
        ContentValues cv = new ContentValues(4);
        cv.put("bigname", name);
        cv.put("smallname", size);
        cv.put("number", number);
        cv.put("money", money);

        db2.insert(tb_name2, null, cv);
    }
}