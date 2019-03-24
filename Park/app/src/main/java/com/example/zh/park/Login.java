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
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper=new DatabaseHelper(this,"Park.db",null,1);
        TextView textView_register=(TextView)findViewById(R.id.text_register);
        textView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        Button button_login=(Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_phone=(EditText)findViewById(R.id.editphone);
                EditText editText_pwd=(EditText)findViewById(R.id.editpassword);
                String phone=editText_phone.getText().toString();
                String pwd=editText_pwd.getText().toString();
                if(phone.equals("")||pwd.equals(""))
                    Toast.makeText(Login.this,"手机或者密码为空！",Toast.LENGTH_SHORT).show();
                else {
                    SQLiteDatabase db= dbHelper.getReadableDatabase();
                    Cursor cursor=db.rawQuery("select * from User where phone=?",new String[]{phone});

                    if(cursor.moveToFirst()){
                        String realpwd=cursor.getString(cursor.getColumnIndex("password"));
                        if(pwd.equals(realpwd))
                        {
                            Toast.makeText(Login.this,"登录成功！",Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor=getSharedPreferences("Park",MODE_PRIVATE).edit();
                            editor.putInt("loginid",cursor.getInt(cursor.getColumnIndex("id")));
                            editor.commit();
                            cursor.close();
                            Intent intent=new Intent(Login.this,HomePage.class);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(Login.this,"密码错误！",Toast.LENGTH_SHORT).show();
                            cursor.close();
                        }
                    }else{
                        Toast.makeText(Login.this,"用户不存在！",Toast.LENGTH_SHORT).show();
                        cursor.close();
                    }
                }
            }
        });
    }
}
