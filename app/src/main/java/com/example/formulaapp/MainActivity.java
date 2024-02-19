package com.example.formulaapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    FirebaseUser user;
    TextView textView;
    Button AddTeam;

    Button viewTeams;
    Button championship;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.userDetails);
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        AddTeam = findViewById(R.id.AddF1Team);
       AddTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(getApplicationContext(), AddteamActivity.class);
                startActivity(intentAdd);
                finish();

            }
        });

        viewTeams = findViewById(R.id.ViewF1Team);
        viewTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentView = new Intent(getApplicationContext(),DisplayF1Teams.class);
                startActivity(intentView);
                finish();
            }
        });

        championship = findViewById(R.id.ChampionShip);
        championship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cham = new Intent(getApplicationContext(),ChampionShips.class);
                startActivity(cham);
                finish();
            }
        });

    }
}