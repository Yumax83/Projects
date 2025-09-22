package com.example.cardviewtraining;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    CardView cardView;
    Button btn;
    TextView textView;

    ConstraintLayout l;

    Integer i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        cardView = findViewById(R.id.cardView);
textView= findViewById(R.id.textView2);

textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (i==0) {
            cardView.setVisibility(View.GONE);
            i=1;
        }
        else if (i==1) {
            cardView.setVisibility(View.VISIBLE);
            i=0;
        }
    }
});


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i==0) {
                    cardView.setVisibility(View.GONE);
                i=1;
                }
                else if (i==1) {
                    cardView.setVisibility(View.VISIBLE);
                    i=0;
                }
            }
        });

    }
}
