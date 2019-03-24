package com.example.zh.park;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        Button button_forrent=(Button)findViewById(R.id.button_forrent);
        Button button_rend=(Button)findViewById(R.id.button_rend);
        Button button_rentin=(Button)findViewById(R.id.button_rentin);

        button_forrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyList.this,MyDetails.class);
                intent.putExtra("detailsId",1);
                startActivity(intent);
            }
        });

        button_rend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyList.this,MyDetails.class);
                intent.putExtra("detailsId",2);
                startActivity(intent);
            }
        });

        button_rentin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyList.this,MyDetails.class);
                intent.putExtra("detailsId",3);
                startActivity(intent);
            }
        });


        Button button_logout=(Button)findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=getSharedPreferences("Park",MODE_PRIVATE).edit();
                editor.remove("loginid");
                editor.commit();

                Intent intent=new Intent(MyList.this,HomePage.class);
                startActivity(intent);
            }
        });
    }
}
