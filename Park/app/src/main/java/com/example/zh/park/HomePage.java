package com.example.zh.park;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        Button button_find=(Button)findViewById(R.id.find);
        button_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this,Find.class);
                startActivity(intent);
            }
        });

        Button button_rent=(Button)findViewById(R.id.rent);
        button_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("Park",MODE_PRIVATE);
                int loginId=sharedPreferences.getInt("loginid",0);
                if(loginId!=0)
                {
                    Intent intent=new Intent(HomePage.this,Release.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(HomePage.this,Login.class);
                    startActivity(intent);
                }
            }
        });

        Button button_my=(Button)findViewById(R.id.my);
        button_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("Park",MODE_PRIVATE);
                int loginId=sharedPreferences.getInt("loginid",0);
                if(loginId!=0)
                {
                    Intent intent=new Intent(HomePage.this,MyList.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(HomePage.this,Login.class);
                    startActivity(intent);
                }
            }
        });

    }

}
