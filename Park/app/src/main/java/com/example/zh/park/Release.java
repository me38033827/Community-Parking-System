package com.example.zh.park;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Release extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);

        dbHelper=new DatabaseHelper(this,"Park.db",null,1);

        Button button=(Button)findViewById(R.id.button_release);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_courtname=(EditText)findViewById(R.id.editText_courtname);
                EditText editText_parkprice=(EditText)findViewById(R.id.editText_parkprice);
                EditText editText_remark=(EditText)findViewById(R.id.editText_remark);
                String courtName=editText_courtname.getText().toString();
                Double parkPrice=Double.parseDouble(editText_parkprice.getText().toString());
                String remark=editText_remark.getText().toString();

                SharedPreferences sharedPreferences=getSharedPreferences("Park",MODE_PRIVATE);
                int ownerId=sharedPreferences.getInt("loginid",0);

                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values =new ContentValues();
                values.put("owner_id",ownerId);
                values.put("tenant_id",0);
                values.put("price",parkPrice);
                values.put("remark",remark);
                values.put("court_name",courtName);
                db.insert("Park",null,values);

                Toast.makeText(Release.this,"发布成功！",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Release.this,HomePage.class);
                startActivity(intent);


            }
        });
    }
}
