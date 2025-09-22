package com.example.firebaseapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebaseapp.Singin.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView tvUid;
    ImageButton imgProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        imgProfile = findViewById(R.id.ib_profile);

        tvUid = findViewById(R.id.tv_uid);
        tvUid.setText(mAuth.getCurrentUser().getUid().toString());

        // выход из аккаунта
        SharedPreferences sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Button btnSignOut = findViewById(R.id.btn_sign_out);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.signOut();
                editor.remove("user_uid");
                editor.remove("user_email");
                editor.apply();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });


        imgProfile.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
            });


        }
    }