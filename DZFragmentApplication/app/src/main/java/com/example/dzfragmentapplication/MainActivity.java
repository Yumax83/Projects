package com.example.dzfragmentapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    Button btnS1, btnS2,btnS3;

    FragmentStorona1 S1Fragment = new FragmentStorona1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        btnS1 = findViewById(R.id.btnStorona1);
        btnS2 = findViewById(R.id.btnStorona2);
        btnS3 = findViewById(R.id.btnStorona3);
        replaceFragments(S1Fragment);

        btnS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragments(S1Fragment);
            }
        });

        btnS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentStorona2 S2Fragment = new FragmentStorona2();
                replaceFragments(S2Fragment);
            }
        });

        btnS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentStorona3 S3Fragment = new FragmentStorona3();
                replaceFragments(S3Fragment);
            }
        });



    }
    private void replaceFragments(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame1, fragment);
        fragmentTransaction.commit();
    }
}