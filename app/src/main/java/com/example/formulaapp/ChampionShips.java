package com.example.formulaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChampionShips extends AppCompatActivity {
    Button btn ;

    Button btn2;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_ships);

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true).add(R.id.FrameCon,BlankFragment1.class,null).
                    commit();
        }




        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true).replace(R.id.FrameCon,BlankFragment1.class,null).
                        commit();
            }
        });

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true).replace(R.id.FrameCon,BlankFragment2.class,null)
                        .commit();
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(back);
                finish();
            }
        });


    }


}