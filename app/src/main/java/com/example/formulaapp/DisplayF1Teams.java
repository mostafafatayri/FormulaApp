package com.example.formulaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

class F1Constructor{
    private String Name;
    private int HighestFinish;
    private int PolePositon;
    private int FastestLap;
    private   int ConstructorsChampion;
    private String Base;
    private String TeamChief;
    private String TC;
    private String PowerUnit;
    //
    private String CarImage;
    private String DriverImageOne;

    public String getDriverImageOne() {
        return DriverImageOne;
    }

    public void setDriverImageOne(String driverImageOne) {
        DriverImageOne = driverImageOne;
    }

    public String getDriverImageTwo() {
        return DriverImageTwo;
    }

    public void setDriverImageTwo(String driverImageTwo) {
        DriverImageTwo = driverImageTwo;
    }

    private String DriverImageTwo;
    public String getCarImage() {
        return CarImage;
    }

    public void setCarImage(String carImage) {
        CarImage = carImage;
    }



    private int HighestFinishValue;

    public String getPowerUnit() {
        return PowerUnit;
    }

    public void setPowerUnit(String powerUnit) {
        PowerUnit = powerUnit;
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

    public String getTeamChief() {
        return TeamChief;
    }

    public void setTeamChief(String teamChief) {
        TeamChief = teamChief;
    }

    public String getBase() {
        return Base;
    }

    public void setBase(String base) {
        Base = base;
    }

    public int getConstructorsChampion() {
        return ConstructorsChampion;
    }

    public void setConstructorsChampion(int constructorsChampion) {
        ConstructorsChampion = constructorsChampion;
    }

    public int getFastestLap( ) {
        return FastestLap;
    }

    public void setFastestLap(int fastestLap) {
        FastestLap = fastestLap;
    }

    public int getPolePositon() {
        return PolePositon;
    }

    public void setPolePositon(int polePositon) {
        PolePositon = polePositon;
    }

    public int getHighestFinish() {
        return HighestFinish;
    }

    public void setHighestFinish(int highestFinish) {
        HighestFinish = highestFinish;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setHighestFinishValue(int highestFinishValue) {
        HighestFinishValue = highestFinishValue;
    }

    public int getHighestFinishValue() {
        return HighestFinishValue;
    }
}


public class DisplayF1Teams extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    Map<String, Map<String, Object>> All_Teams_Data = new HashMap<>();
    TextView highPosition;
    TextView highNb;

    TextView BaseTeam;
    TextView TeamChiefValue;

    TextView TCValue;
    TextView PuValue;

    TextView CSValue;
    TextView PolePositionsNb;

    TextView FLapNb;

    ImageView CarImage;

    ImageView imageView1;

    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_f1_teams);
        Log.d("hello wolrd", "test print");
        //db connect:
        fstore = FirebaseFirestore.getInstance();
        fstore.collection("F1Teams")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String team = document.get("F1_Team").toString();
                            All_Teams_Data.put(team, document.getData());


                            // Print the data to the console or logcat
                            for (Map.Entry<String, Object> entry : document.getData().entrySet()) {
                                Log.d("FirestoreData", entry.getKey() + ": " + entry.getValue());
                            }
                        }
                        // Now that you have populated All_Teams_Data, you can create an array of team names
                        String[] teamNames = All_Teams_Data.keySet().toArray(new String[0]);

                        // Use the array of team names to set up the Spinner
                        Spinner spinner = findViewById(R.id.spinner);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, teamNames);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);


                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                 Log.d("check", All_Teams_Data.toString());
                                // Log.d("Red bull", String.valueOf(All_Teams_Data.get("Red Bull Racing")));
                                // Log.d("team principle ",String.valueOf(All_Teams_Data.get("Red Bull Racing").get("TeamChief")));

                                String selectedTeam = teamNames[position];

                                F1Constructor team = new F1Constructor();
                                team.setBase(String.valueOf(All_Teams_Data.get(selectedTeam).get("Base")));
                                team.setName(String.valueOf(All_Teams_Data.get(selectedTeam).get("F1_Team")));
                                team.setTeamChief(String.valueOf(All_Teams_Data.get(selectedTeam).get("TeamChief")));


                                CarImage = findViewById(R.id.CarImage);
                                team.setCarImage(String.valueOf(All_Teams_Data.get(selectedTeam).get("TeamLogoURL")));///
                                String imageUrl = String.valueOf(All_Teams_Data.get(selectedTeam).get("TeamLogoURL"));
                                Picasso.get().load(imageUrl).into(CarImage);

                                // drive 1:
                                imageView1=findViewById(R.id.imageView1);
                                team.setDriverImageOne(String.valueOf(All_Teams_Data.get(selectedTeam).get("DriverOneURL")));
                                String driverURL = String.valueOf(All_Teams_Data.get(selectedTeam).get("DriverOneURL"));
                                Picasso.get().load(driverURL).into(imageView1);

                                //
                                imageView2=findViewById(R.id.imageView2);
                                team.setDriverImageOne(String.valueOf(All_Teams_Data.get(selectedTeam).get("DriverTwoURL")));
                                String driverURLTwo = String.valueOf(All_Teams_Data.get(selectedTeam).get("DriverTwoURL"));
                                Picasso.get().load(driverURLTwo).into(imageView2);
                              //  Log.d("the image ",String.valueOf(All_Teams_Data.get(selectedTeam).get("ImageURL")));


                                String stringChamp = String.valueOf(All_Teams_Data.get(selectedTeam).get("ChampionShips"));
                                int champInt = Integer.parseInt(stringChamp);
                                team.setConstructorsChampion(champInt);
                                String stringHighestFinish = String.valueOf(All_Teams_Data.get(selectedTeam).get("HighestFinish"));
                                int hfinsh = Integer.parseInt(stringHighestFinish);
                                team.setHighestFinish(hfinsh);

                                String numberFinish = String.valueOf(All_Teams_Data.get(selectedTeam).get("HighestFinishValue"));
                                int intFinish = Integer.parseInt(numberFinish);
                                team.setHighestFinishValue(intFinish);


                                String Pole = String.valueOf(All_Teams_Data.get(selectedTeam).get("Pole_Position"));
                                int PoleValue = Integer.parseInt(Pole);
                                team.setPolePositon(PoleValue);


                                String FastestLap = String.valueOf(All_Teams_Data.get(selectedTeam).get("FastestLap"));
                                int FastestLapInt = Integer.parseInt(FastestLap);
                                team.setFastestLap(FastestLapInt);

                                team.setPowerUnit(String.valueOf(All_Teams_Data.get(selectedTeam).get("PU")));
                                team.setTC(String.valueOf(All_Teams_Data.get(selectedTeam).get("TechnicalChief")));

                                highNb = findViewById(R.id.HighNB);
                                highNb.setText(String.valueOf(team.getHighestFinish()) + "X" + String.valueOf(team.getHighestFinishValue()));

                                // working
                                BaseTeam = findViewById(R.id.BaseValue);
                                BaseTeam.setText(team.getBase());

                                TeamChiefValue = findViewById(R.id.TeamChiefValue);
                                TeamChiefValue.setText(team.getTeamChief());

                                TCValue = findViewById(R.id.TCValue);
                                TCValue.setText(team.getTC());

                                PuValue = findViewById(R.id.PuValue);
                                PuValue.setText(team.getPowerUnit());

                                CSValue = findViewById(R.id.CSValue);
                                CSValue.setText(String.valueOf(team.getConstructorsChampion()));

                                PolePositionsNb = findViewById(R.id.PolePositionsNb);
                                PolePositionsNb.setText(String.valueOf(team.getPolePositon()));

                                FLapNb = findViewById(R.id.FLapNb);
                                FLapNb.setText(String.valueOf(team.getFastestLap()));

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    } else {
                        Log.e("FirestoreError", "Error getting documents: ", task.getException());
                    }
                });


    }
}