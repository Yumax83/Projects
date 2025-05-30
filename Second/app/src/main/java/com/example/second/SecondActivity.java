package com.example.second;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private MediaPlayer sound1, sound2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        ImageButton img1 = findViewById(R.id.buttonImg1);
        ImageButton img2 = findViewById(R.id.buttonImg2);

        sound1 = MediaPlayer.create(this, R.raw.sound_1);
        sound2 = MediaPlayer.create(this, R.raw.sound_2);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayButton(sound1, sound2);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayButton(sound2, sound1);
            }
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });

        Button btnSt = findViewById(R.id.buttonStop);
        btnSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound1.stop();
                sound2.stop();
            }
        });

    }
//    public void goBack(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }


    public void soundPlayButton(MediaPlayer snd1, MediaPlayer snd2) {

        if (snd1.isPlaying()) {
            snd1.pause();
            snd2.seekTo(0);
            //snd1.stop();

            snd1.setLooping(false);

        }
//        else  {
//            snd1.start();
//            snd1.setLooping(true);
//        }

        if (snd2.isPlaying()) {
            snd2.pause();
            snd1.seekTo(0);
            snd2.setLooping(false);
        }
//        else {
//            snd2.start();
//            snd2.setLooping(true);
//      //  }
        snd1.start();
        snd1.setLooping(true);

    }

}