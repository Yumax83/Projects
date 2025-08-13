package com.example.dzquizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameLevels extends AppCompatActivity {

    public int bal=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences save = getSharedPreferences("save", MODE_PRIVATE);
        final int level = save.getInt("Level", 1);


        setContentView(R.layout.activity_game_levels);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameLevels.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bal=Level1.bal_l1+Level2.bal_l2+Level3.bal_l3+Level4.bal_l4+Level5.bal_l5;

        TextView point = findViewById(R.id.textPoint);
        point.setText("Общее количество баллов: "+bal);

// переход на 1 уровень
        TextView textView1 = findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level >= 1) {
                    Intent intent = new Intent(GameLevels.this, Level1.class);
                    startActivity(intent);
                }
            }
        });
        // переход на 2 уровень
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level >= 2) {
                    Intent intent = new Intent(GameLevels.this, Level2.class);
                    startActivity(intent);
                }
            }
        });
        // переход на 3 уровень
        TextView textView3 = findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level >= 3) {
                    Intent intent = new Intent(GameLevels.this, Level3.class);
                    startActivity(intent);
                }
            }
        });

        // переход на 4 уровень
        TextView textView4 = findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level >= 4) {
                    Intent intent = new Intent(GameLevels.this, Level4.class);
                    startActivity(intent);
                }
            }
        });
        // переход на 5 уровень
        TextView textView5 = findViewById(R.id.textView5);
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level >= 5) {
                    Intent intent = new Intent(GameLevels.this, Level5.class);
                    startActivity(intent);
                }
            }
        });

        final  int[] x = {
                R.id.textView1,
                R.id.textView2,
                R.id.textView3,
                R.id.textView4,
                R.id.textView5,
        };

        for (int i = 0; i < level; i++) {
            TextView tv= findViewById(x[i]);
            tv.setText("Задание " + (i+1));

        }
        Button reset = findViewById(R.id.button_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = save.edit();
                editor.putInt("Level", 1);
                editor.apply();
                Intent intent = new Intent(GameLevels.this, MainActivity.class);
                startActivity(intent);
                bal=0;
                point.setText("Общее количество баллов: "+bal);
            }
        });

    }
}