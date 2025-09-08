package com.example.productsapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detali);

        ImageView imageView = findViewById(R.id.mImage);
        TextView titleTV = findViewById(R.id.mTitle);
        TextView overviewTV = findViewById(R.id.mProduct);

    Bundle bundle = getIntent().getExtras();

    assert bundle != null;
    String mTitle = bundle.getString("title");
    String mOverview = bundle.getString("overview");
    String mPoster = bundle.getString("poster");


    Glide.with(this).load(mPoster).into(imageView);
    titleTV.setText(mTitle);
    overviewTV.setText(mOverview);


    }
}