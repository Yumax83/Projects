package com.example.adapter1;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);

        // Sample data with icons
        List<String> planets = new ArrayList<>();
        planets.add("Mercury");
        planets.add("Venus");
        planets.add("Earth");
        planets.add("Mars");

        List<Integer> icons = new ArrayList<>();
        icons.add(R.drawable.hand_0);
        icons.add(R.drawable.hand_1);
        icons.add(R.drawable.hand_2);
        icons.add(R.drawable.hand_3);

        // Set the custom adapter
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, planets, icons);
        spinner.setAdapter(adapter);

        // Apply the custom background
        spinner.setBackgroundResource(R.drawable.background_input_field);

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}