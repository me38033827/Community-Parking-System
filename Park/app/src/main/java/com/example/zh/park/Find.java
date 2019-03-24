package com.example.zh.park;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Find extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private String courtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        dbHelper=new DatabaseHelper(this,"Park.db",null,1);


        Button button=(Button)findViewById(R.id.button_find);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText=(EditText)findViewById(R.id.editText_find);
                courtName=editText.getText().toString();

                SQLiteDatabase db= dbHelper.getReadableDatabase();
                Cursor cursor=db.rawQuery("select * from Park where court_name=?",new String[]{courtName});
                if(cursor.moveToFirst())
                {
                    cursor.close();
                    Intent intent=new Intent(Find.this,ParkList.class);
                    intent.putExtra("courtName",courtName);
                    startActivity(intent);
                }else
                    Toast.makeText(Find.this,"该小区没有可用车位！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
