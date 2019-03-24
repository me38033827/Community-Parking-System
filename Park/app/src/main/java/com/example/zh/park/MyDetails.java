package com.example.zh.park;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyDetails extends AppCompatActivity {
    private List<Park> parkList=new ArrayList<Park>();
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);

        initPark();
        DetailsAdapter detailsAdapter=new DetailsAdapter(this,R.layout.details_item,parkList);

        ListView listView=(ListView)findViewById(R.id.listView_details);
        listView.setAdapter(detailsAdapter);


    }

    private void initPark(){

        Intent intent=getIntent();
        int detailsId=intent.getIntExtra("detailsId",0);

        SharedPreferences sharedPreferences=getSharedPreferences("Park",MODE_PRIVATE);
        int userId=sharedPreferences.getInt("loginid",0);

        dbHelper=new DatabaseHelper(this,"Park.db",null,1);
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        if(detailsId==1){
        Cursor cursor=db.rawQuery("select * from Park where owner_id=? and tenant_id=?",new String[]{String.valueOf(userId),"0"});
        if(cursor.moveToFirst()){
            do{
                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                String str_price=String.valueOf(price);
                String remark=cursor.getString(cursor.getColumnIndex("remark"));
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                int tenant_id=cursor.getInt(cursor.getColumnIndex("tenant_id"));
                String courtname=cursor.getString(cursor.getColumnIndex("court_name"));
                if(tenant_id==0){
                    Park park=new Park(courtname,str_price,remark,id);
                    parkList.add(park);}

            }while(cursor.moveToNext());
        }
        }else if(detailsId==2){
            Cursor cursor=db.rawQuery("select * from Park where owner_id=? and tenant_id!=?",new String[]{String.valueOf(userId),"0"});
            if(cursor.moveToFirst()){
                do{
                    double price=cursor.getDouble(cursor.getColumnIndex("price"));
                    String str_price=String.valueOf(price);
                    String remark=cursor.getString(cursor.getColumnIndex("remark"));
                    int id=cursor.getInt(cursor.getColumnIndex("id"));
                    int tenant_id=cursor.getInt(cursor.getColumnIndex("tenant_id"));
                    String courtname=cursor.getString(cursor.getColumnIndex("court_name"));

                    Park park=new Park(courtname,str_price,remark,id);
                    parkList.add(park);

                }while(cursor.moveToNext());
            }
        }else{
            Cursor cursor=db.rawQuery("select * from Park where tenant_id=?",new String[]{String.valueOf(userId)});
            if(cursor.moveToFirst()){
                do{
                    double price=cursor.getDouble(cursor.getColumnIndex("price"));
                    String str_price=String.valueOf(price);
                    String remark=cursor.getString(cursor.getColumnIndex("remark"));
                    int id=cursor.getInt(cursor.getColumnIndex("id"));
                    int tenant_id=cursor.getInt(cursor.getColumnIndex("tenant_id"));
                    String courtname=cursor.getString(cursor.getColumnIndex("court_name"));

                    Park park=new Park(courtname,str_price,remark,id);
                    parkList.add(park);

                }while(cursor.moveToNext());
            }
        }
    }
}
