package com.example.dz_set_profil;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button status = findViewById(R.id.buttonStatus);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    status.setBackgroundColor(Color.parseColor("#4CAF50"));
                    status.setText("ONLINE");
                } else {
                    status.setBackgroundColor(Color.parseColor("#E93F11"));
                    status.setText("OFFLINE");
                }
            }
        });

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        EditText textName= findViewById(R.id.textName);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton) {
                    textName.setTextColor(Color.parseColor("#092CF0"));
                } else if (checkedId == R.id.radioButton2) {
                    textName.setTextColor(Color.parseColor("#28BD32"));
                }
            }
        });

        Switch mySwitch = findViewById(R.id.switch1);
        TextView textView = findViewById(R.id.textView);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   textView.setBackgroundResource(R.drawable.main_box_yellow);
                } else {
                    textView.setBackgroundResource(R.drawable.main_box);
                }
            }
        });

        SwitchMaterial switchMaterial = findViewById(R.id.switchMaterial);
        ImageView photo = findViewById(R.id.imageView);

        switchMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchMaterial.isChecked()) {
                   photo.setVisibility(View.VISIBLE);
                } else {
                    photo.setVisibility(View.INVISIBLE);

                }
            }
        });

        CheckBox boldCheckBox = findViewById(R.id.boldCheckBox);
        CheckBox italicCheckBox = findViewById(R.id.italicCheckBox);

        boldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (italicCheckBox.isChecked()) {
                        textName.setTypeface(null, Typeface.BOLD_ITALIC);
                    } else {
                        textName.setTypeface(null, Typeface.BOLD);
                    }
                } else {
                    if (italicCheckBox.isChecked()) {
                        textName.setTypeface(null, Typeface.ITALIC);
                    } else {
                        textName.setTypeface(null, Typeface.NORMAL);
                    }
                }
            }
        });

        italicCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (boldCheckBox.isChecked()) {
                        textName.setTypeface(null, Typeface.BOLD_ITALIC);
                    } else {
                        textName.setTypeface(null, Typeface.ITALIC);
                    }
                } else {
                    if (boldCheckBox.isChecked()) {
                        textName.setTypeface(null, Typeface.BOLD);
                    } else {
                        textName.setTypeface(null, Typeface.NORMAL);
                    }
                }
            }
        });
    }
}