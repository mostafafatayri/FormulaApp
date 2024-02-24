package com.example.newf1app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
//
{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
         navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

       if (savedInstanceState == null) {
           Log.d("system check: ","check it ");
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
           // navigationView.setCheckedItem(R.id.nav_home);
        }
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_aboutTeams)
        {

            Intent intentAdd = new Intent(getApplicationContext(), viewTeams.class);
            startActivity(intentAdd);
           // finish();
        }
        if(id == R.id.nav_addTeam)
        {
            Log.d("system check the press button ","check it ");
            Intent intentAdd = new Intent(getApplicationContext(), addF1team.class);
            startActivity(intentAdd);
            finish();
        }


      if(id == R.id.nav_viewCham)
      {
          Intent intentAdd = new Intent(getApplicationContext(), Champions.class);
          startActivity(intentAdd);
          finish();
      }
      if(id == R.id.nav_logout)
      {
          FirebaseAuth.getInstance().signOut();
          Intent intent = new Intent(getApplicationContext(), Login.class);
          startActivity(intent);
          finish();


      }
      if(id == R.id.nav_arbic)
      {
          setLocale("ar");

      }
        if(id == R.id.nav_english)
        {

            setLocale("en");
        }






        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        recreate(); // Restart activity to apply the new language
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}