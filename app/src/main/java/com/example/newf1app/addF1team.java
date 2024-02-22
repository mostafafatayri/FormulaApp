package com.example.newf1app;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.HashMap;
import java.util.Map;

public class addF1team extends AppCompatActivity {
    LinearLayout openForm;
    RadioButton isChampion;
    EditText highestPosition;
    Button addButton;
    EditText editTextHighestFinish;
    EditText editTextHighestFinishTimes;
    EditText editTextPolePositions;
    EditText editTextFastestLaps;
    ///
    EditText editTextChampion;
    EditText BaseValue;
    EditText TeamChiefValue;
    EditText PU;
    EditText TechnicalChiefValue;
    EditText TeamName;
    FirebaseFirestore fstore;
    // added
    private static final int PICK_TEAM_LOGO_REQUEST = 1;
    private static final int PICK_DRIVER_IMAGE_REQUEST = 2;

    private static final int PICK_DRIVER_IMAGE_SECOND_REQUEST = 3; // 3
    // Views
    private ImageView imageViewTeamLogo;
    // Firebase Storage
    private StorageReference storageReference;
    // Constants
    private Uri imageUri;

    // driver 1 images : ///
    private ImageView imageViewDriverOne;
    private StorageReference storageRefForDriverOne;
    private  Uri driverOneUri;
    //second driver ==::>
    private ImageView imageViewDriverSecond;
    private StorageReference storageRefForDriverSecond;
    private  Uri driverSecondUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_f1team);
        // added new
        fstore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        storageRefForDriverOne= FirebaseStorage.getInstance().getReference();
        storageRefForDriverSecond = FirebaseStorage.getInstance().getReference();
        // Initialize views
        imageViewTeamLogo = findViewById(R.id.imageViewTeamLogo);
        // Set click listener for Select Image button
        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        buttonSelectImage.setOnClickListener(v -> openFileChooserForTeamLogo()); ///
        // Set click listener for Add button
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> uploadData());
        // activate the constructors form
        openForm = findViewById(R.id.linearLayoutChampions);
        isChampion = findViewById(R.id.radioButtonChampion);
        boolean isTrue = false;
        isChampion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = openForm.getVisibility();
                if (visibility == View.VISIBLE) {
                    openForm.setVisibility(View.GONE);
                } else {
                    openForm.setVisibility(View.VISIBLE);
                }
            }
        });
        // add driver one :
        imageViewDriverOne=findViewById(R.id.imageViewDriverOne);
        Button buttonSelectDriverOne = findViewById(R.id.buttonSelectDriverOne);
        buttonSelectDriverOne.setOnClickListener(v ->openFileChooserForDriverImage());

        // add driver 2 :
        imageViewDriverSecond=findViewById(R.id.imageViewDriverSecond);
        Button buttonSelectDriverSecond= findViewById(R.id.buttonSelectDriverSecond);
        buttonSelectDriverSecond.setOnClickListener(v->openFileChooserForDriverTwoImage());

    }

    private void openFileChooserForDriverTwoImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_DRIVER_IMAGE_SECOND_REQUEST);
    }
    private void openFileChooserForDriverImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_DRIVER_IMAGE_REQUEST);
    }
    // image upload

    private void openFileChooserForTeamLogo() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_TEAM_LOGO_REQUEST);
    }
    // Method to handle the result of file chooser
    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_TEAM_LOGO_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            // Set the selected image to the ImageView for preview
            imageViewTeamLogo.setImageURI(imageUri);
        } else if (requestCode == PICK_DRIVER_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            driverOneUri = data.getData();
            // Set the selected driver image to the ImageView for preview
            imageViewDriverOne.setImageURI(driverOneUri);
        }
        else if (requestCode==PICK_DRIVER_IMAGE_SECOND_REQUEST && resultCode==RESULT_OK && data !=null &&data.getData() !=null){
            driverSecondUri = data.getData();
            imageViewDriverSecond.setImageURI(driverSecondUri);
        }
    }

    // upload function
    private void uploadData() {
        editTextHighestFinish = findViewById(R.id.editTextHighestFinish);
        editTextHighestFinishTimes = findViewById(R.id.editTextHighestFinishTimes);
        editTextPolePositions = findViewById(R.id.editTextPolePositions);
        editTextFastestLaps = findViewById(R.id.editTextFastestLaps);
        editTextChampion = findViewById(R.id.editTextChampion);
        BaseValue = findViewById(R.id.BaseValue);
        TeamChiefValue = findViewById(R.id.TeamChiefValue);
        PU = findViewById(R.id.PU);
        TechnicalChiefValue = findViewById(R.id.TechnicalChiefValue);
        TeamName = findViewById(R.id.TeamName);
        String highestFinish = editTextHighestFinish.getText().toString();
        String highestFinishTimes = editTextHighestFinishTimes.getText().toString();
        String polePositions = editTextPolePositions.getText().toString();
        String fastestLaps = editTextFastestLaps.getText().toString();
        String Champions = editTextChampion.getText().toString();
        String Base = BaseValue.getText().toString();
        String ChiefValue = TeamChiefValue.getText().toString();
        String PowerUnit = PU.getText().toString();
        String TechChief = TechnicalChiefValue.getText().toString();
        String Name = TeamName.getText().toString();
        Log.d("ched", highestFinish);

        Map<String, Object> f1TeamData = new HashMap<>();
        f1TeamData.put("HighestFinish", highestFinish);
        f1TeamData.put("FastestLap", fastestLaps);
        f1TeamData.put("Pole_Position", polePositions);
        f1TeamData.put("HighestFinishValue", highestFinishTimes);
        f1TeamData.put("PU", PowerUnit);
        f1TeamData.put("Base", Base);
        f1TeamData.put("TechnicalChief", TechChief);
        f1TeamData.put("TeamChief", ChiefValue);
        f1TeamData.put("ChampionShips", Champions);
        f1TeamData.put("F1_Team", Name);

        if (imageUri != null && driverOneUri != null) {
            // Upload team logo image to Firebase Storage
            StorageReference teamLogoRef = storageReference.child("/teamPic" + System.currentTimeMillis() + ".jpg");
            teamLogoRef.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        teamLogoRef.getDownloadUrl().addOnSuccessListener(teamLogoUri -> {
                            // Store team logo image URL in Firestore data
                            f1TeamData.put("TeamLogoURL", teamLogoUri.toString());
                            // Upload driver image to Firebase Storage
                            StorageReference driverImageRef = storageRefForDriverOne.child("/driverPic" + System.currentTimeMillis() + ".jpg");
                            driverImageRef.putFile(driverOneUri)
                                    .addOnSuccessListener(driverTaskSnapshot -> {
                                        driverImageRef.getDownloadUrl().addOnSuccessListener(driverUri -> {
                                            // Store driver image URL in Firestore data
                                            f1TeamData.put("DriverOneURL", driverUri.toString());/////

                                            // Upload the second driver's image
                                            StorageReference drivetwo = storageRefForDriverSecond.child("/driverTwoPic" + System.currentTimeMillis() + ".jpg");
                                            drivetwo.putFile(driverSecondUri).addOnSuccessListener(driverTwoTaskSnapshot -> {
                                                drivetwo.getDownloadUrl().addOnSuccessListener(driverTwoUri -> {
                                                    // Store second driver image URL in Firestore data
                                                    f1TeamData.put("DriverTwoURL", driverTwoUri.toString());

                                                    // Add data to Firestore
                                                    fstore.collection("F1Teams")
                                                            .add(f1TeamData)
                                                            .addOnSuccessListener(documentReference -> {
                                                                String documentId = documentReference.getId();
                                                                Log.d("UploadData", "Data uploaded successfully");
                                                                Toast.makeText(addF1team.this, "Team Added Successfull.",
                                                                        Toast.LENGTH_LONG).show();

                                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                                startActivity(intent);
                                                                finish();
                                                            }).addOnFailureListener(e -> {
                                                                Log.e("UploadData", "Failed to upload data", e);
                                                            });
                                                });
                                            }).addOnFailureListener(e -> {
                                                Log.e("UploadData", "Failed to upload second driver image: " + e.getMessage());
                                            });
                                        });
                                    }).addOnFailureListener(e -> {
                                        Log.e("UploadData", "Failed to upload first driver image: " + e.getMessage());
                                    });

                        });
                    }).addOnFailureListener(e -> {
                        Log.e("UploadData", "Failed to upload team logo image: " + e.getMessage());
                    });
        } else {
            // Add data to Firestore without the image URLs
            fstore.collection("F1Teams")
                    .add(f1TeamData)
                    .addOnSuccessListener(documentReference -> {
                        String documentId = documentReference.getId();
                        Log.d("UploadData", "Data uploaded successfully (without images)");
                    }).addOnFailureListener(e -> {
                        Log.e("UploadData", "Failed to upload data (without images): " + e.getMessage());
                    });
        }


    }

}
