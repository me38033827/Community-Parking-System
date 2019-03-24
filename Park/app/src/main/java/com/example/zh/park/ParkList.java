package com.example.zh.park;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ParkList extends AppCompatActivity {
    private List<Park>parkList=new ArrayList<Park>();
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_list);
        initPark();
        ParkAdapter parkAdapter=new ParkAdapter(this,R.layout.park_item,parkList);

        ListView listView=(ListView)findViewById(R.id.listView_park);
        listView.setAdapter(parkAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Park park=parkList.get(i);
                int id=park.getId();

                Intent intent=new Intent(ParkList.this,ParkDetails.class);
                intent.putExtra("parkId",id);

                startActivity(intent);
            }
        });



    }

    private void initPark(){

        Intent intent=getIntent();
        String courtName=intent.getStringExtra("courtName");

        dbHelper=new DatabaseHelper(this,"Park.db",null,1);
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from Park where court_name=?",new String[]{courtName});

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
    }
}
