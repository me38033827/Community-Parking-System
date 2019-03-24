package com.example.zh.park;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper=new DatabaseHelper(this,"Park.db",null,1);
        final EditText editText_phone=(EditText)findViewById(R.id.editPhone);
        final EditText editText_pwd=(EditText)findViewById(R.id.editPassword);
        final EditText editText_pwd1=(EditText)findViewById(R.id.editPassword1);
        Button button_register=(Button)findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=editText_phone.getText().toString();
                String pwd=editText_pwd.getText().toString();
                String pwd1=editText_pwd1.getText().toString();
                if(phone.equals("")||pwd.equals("")||pwd1.equals(""))
                    Toast.makeText(Register.this,"手机号或者密码为空！",Toast.LENGTH_SHORT).show();
                else if(!pwd.equals(pwd1))
                    Toast.makeText(Register.this,"密码不一致!",Toast.LENGTH_SHORT).show();
                else{

                    SQLiteDatabase db=dbHelper.getWritableDatabase();
                    ContentValues values =new ContentValues();
                    values.put("phone",Long.parseLong(phone));
                    values.put("password",pwd);
                    db.insert("User",null,values);


                    Toast.makeText(Register.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Register.this,Login.class);
                    startActivity(intent);
                }
            }
        });
    }
}
