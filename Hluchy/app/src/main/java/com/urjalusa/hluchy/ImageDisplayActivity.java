package com.urjalusa.hluchy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class ImageDisplayActivity extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageRef;
    ImageView myImg;
    TextView textDisplay;
    File file;
    int[][] id = {{R.id.ASLDisplay00_imageDisplayActivity, R.id.ASLDisplay01_imageDisplayActivity, R.id.ASLDisplay02_imageDisplayActivity, R.id.ASLDisplay03_imageDisplayActivity, R.id.ASLDisplay04_imageDisplayActivity, R.id.ASLDisplay05_imageDisplayActivity, R.id.ASLDisplay06_imageDisplayActivity, R.id.ASLDisplay07_imageDisplayActivity},
            {R.id.ASLDisplay10_imageDisplayActivity, R.id.ASLDisplay11_imageDisplayActivity, R.id.ASLDisplay12_imageDisplayActivity, R.id.ASLDisplay13_imageDisplayActivity, R.id.ASLDisplay14_imageDisplayActivity, R.id.ASLDisplay15_imageDisplayActivity, R.id.ASLDisplay16_imageDisplayActivity, R.id.ASLDisplay17_imageDisplayActivity},
            {R.id.ASLDisplay20_imageDisplayActivity, R.id.ASLDisplay21_imageDisplayActivity, R.id.ASLDisplay22_imageDisplayActivity, R.id.ASLDisplay23_imageDisplayActivity, R.id.ASLDisplay24_imageDisplayActivity, R.id.ASLDisplay25_imageDisplayActivity, R.id.ASLDisplay26_imageDisplayActivity, R.id.ASLDisplay27_imageDisplayActivity},
            {R.id.ASLDisplay30_imageDisplayActivity, R.id.ASLDisplay31_imageDisplayActivity, R.id.ASLDisplay32_imageDisplayActivity, R.id.ASLDisplay33_imageDisplayActivity, R.id.ASLDisplay34_imageDisplayActivity, R.id.ASLDisplay35_imageDisplayActivity, R.id.ASLDisplay36_imageDisplayActivity, R.id.ASLDisplay37_imageDisplayActivity},
            {R.id.ASLDisplay40_imageDisplayActivity, R.id.ASLDisplay41_imageDisplayActivity, R.id.ASLDisplay42_imageDisplayActivity, R.id.ASLDisplay43_imageDisplayActivity, R.id.ASLDisplay44_imageDisplayActivity, R.id.ASLDisplay45_imageDisplayActivity, R.id.ASLDisplay46_imageDisplayActivity, R.id.ASLDisplay47_imageDisplayActivity},
            {R.id.ASLDisplay50_imageDisplayActivity, R.id.ASLDisplay51_imageDisplayActivity, R.id.ASLDisplay52_imageDisplayActivity, R.id.ASLDisplay53_imageDisplayActivity, R.id.ASLDisplay54_imageDisplayActivity, R.id.ASLDisplay55_imageDisplayActivity, R.id.ASLDisplay56_imageDisplayActivity, R.id.ASLDisplay57_imageDisplayActivity},
            {R.id.ASLDisplay60_imageDisplayActivity, R.id.ASLDisplay61_imageDisplayActivity, R.id.ASLDisplay62_imageDisplayActivity, R.id.ASLDisplay63_imageDisplayActivity, R.id.ASLDisplay64_imageDisplayActivity, R.id.ASLDisplay65_imageDisplayActivity, R.id.ASLDisplay66_imageDisplayActivity, R.id.ASLDisplay67_imageDisplayActivity},
            {R.id.ASLDisplay70_imageDisplayActivity, R.id.ASLDisplay71_imageDisplayActivity, R.id.ASLDisplay72_imageDisplayActivity, R.id.ASLDisplay73_imageDisplayActivity, R.id.ASLDisplay74_imageDisplayActivity, R.id.ASLDisplay75_imageDisplayActivity, R.id.ASLDisplay76_imageDisplayActivity, R.id.ASLDisplay77_imageDisplayActivity}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        storage = FirebaseStorage.getInstance();
        int x = 0, y = 0;
        textDisplay = findViewById(R.id.convertedTextDisplay_imageDisplayActivity);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1F00AB"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Bundle extras = getIntent().getExtras();
        String textToDisplay = extras.getString("Data").toLowerCase();
        textDisplay.setText(textToDisplay);

        for (int i = 0; i < textToDisplay.length(); i++) {
            char instance = textToDisplay.charAt(i);
            if (Character.isAlphabetic(instance)) {
                displayAlphaImage(instance,x,y);
            } else if (Character.isDigit(instance)) {
                displayDigitImage(instance,x,y);
            } else if (Character.isSpaceChar(instance)) {
                x++;
                y = -1;
            }
            if (y < 7) {
                y++;
            } else {
                x++;
                y = 0;
            }
            if (x == 8){
                Toast.makeText(getApplicationContext(), "Word limit exceeded", Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

    private void displayDigitImage(char digit, int x, int y) {
        String childPath = "digitImg/" + digit + ".gif";
        storageRef = storage.getReferenceFromUrl("gs://hluchy-a4293.appspot.com").child(childPath);
        try {
            file = File.createTempFile("image", "gif");
            File finalFile = file;
            storageRef.getFile(file).addOnSuccessListener(taskSnapshot -> {
                Bitmap myBitmap = BitmapFactory.decodeFile(finalFile.getAbsolutePath());
                myImg = findViewById(id[x][y]);
                myImg.setImageBitmap(myBitmap);
            }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Image Failed to Load", Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayAlphaImage(char letter, int x, int y) {
        String childPath = "alphabetImg/" + letter + ".gif";
        storageRef = storage.getReferenceFromUrl("gs://hluchy-a4293.appspot.com").child(childPath);

        try {
            file = File.createTempFile("image", "gif");
            File finalFile = file;
            storageRef.getFile(file).addOnSuccessListener(taskSnapshot -> {
                Bitmap myBitmap = BitmapFactory.decodeFile(finalFile.getAbsolutePath());
                myImg = findViewById(id[x][y]);
                myImg.setImageBitmap(myBitmap);
            }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Image Failed to Load", Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}