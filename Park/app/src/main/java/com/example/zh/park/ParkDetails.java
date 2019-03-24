package com.example.zh.park;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkDetails extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private String courtName;
    private double price;
    private String remark;
    private int parkId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_details);

        parkId=getIntent().getIntExtra("parkId",0);

        dbHelper=new DatabaseHelper(this,"Park.db",null,1);
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from Park where id=?",new String[]{String.valueOf(parkId)});
        cursor.moveToFirst();
        courtName=cursor.getString(cursor.getColumnIndex("court_name"));
        price=cursor.getDouble(cursor.getColumnIndex("price"));
        remark=cursor.getString(cursor.getColumnIndex("remark"));
        cursor.close();

        TextView textView_courtname=(TextView)findViewById(R.id.textView_courtName);
        TextView textView_price=(TextView)findViewById(R.id.textView_Price);
        TextView textView_remark=(TextView)findViewById(R.id.textView_Remark);

        textView_courtname.setText(courtName);
        textView_price.setText(String.valueOf(price));
        textView_remark.setText(remark);

        Button button=(Button)findViewById(R.id.button_rent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("Park",MODE_PRIVATE);
                int userId=sharedPreferences.getInt("loginid",0);
                SQLiteDatabase sqLiteDatabase1=dbHelper.getWritableDatabase();
                sqLiteDatabase1.execSQL("update Park set tenant_id=? where id=?",new String[]{String.valueOf(userId),String.valueOf(parkId)});

                Date date=new Date();
                DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=format.format(date);
                sqLiteDatabase1.execSQL("insert into Orders(park_id,date,tenant_id,price) values(?,?,?,?)",new String[]{String.valueOf(parkId),String.valueOf(time),String.valueOf(userId),String.valueOf(price)});

                Intent intent=new Intent(ParkDetails.this,HomePage.class);
                startActivity(intent);

            }
        });

    }
}
