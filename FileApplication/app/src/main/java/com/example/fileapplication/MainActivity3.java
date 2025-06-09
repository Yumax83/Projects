package com.example.fileapplication;

import android.Manifest;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    Button takePermission;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        takePermission = findViewById(R.id.btnPermission);

        // Поле для вывода информации оразрешении
        textView2 = findViewById(R.id.textView2);

// В OnCreate при создании проверяем настройки разрешений

        // Для версии до 29 API
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                textView2.setText("Разрешение не предоставлено!");
            }
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                textView2.setText("Разрешение уже предоставлено!");
            }
        }
        // Для версии 30 API и выше
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                textView2.setText("Разрешение не предоставлено!");
            }
            if (Environment.isExternalStorageManager()) {
                textView2.setText("Разрешение уже предоставлено!");
            }
        }
            // во всех трех эмуляторах  отработано все правильно




        takePermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Разрешение до Android 10 (API 29)
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //Toast.makeText(MainActivity3.this, "Нажали кнопку", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(MainActivity3.this, new String[]
                                {Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                        textView2.setText(" ");
                    }
                }
                // Разрешение для Android 11 (API 30) и выше
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                    if (!Environment.isExternalStorageManager()) {
                        Toast.makeText(MainActivity3.this, "Нажали кнопку", Toast.LENGTH_SHORT).show();
                        try {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                            intent.addCategory("android.intent.category.DEFAULT");
                            intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                            startActivityIfNeeded(intent, 101);
                        } catch (Exception e) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                            startActivityIfNeeded(intent, 101);
                        }
                    }
                }
            }
        });
    }
}