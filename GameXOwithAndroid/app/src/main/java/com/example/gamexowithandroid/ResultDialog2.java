package com.example.gamexowithandroid;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultDialog2 extends Dialog {
    private final String message;
    private PlayingFieldDifficult playingFieldDifficult;

    public ResultDialog2(@NonNull Context context, String message, PlayingFieldDifficult playingFieldDifficult) {
        super(context);
        this.message = message;
        this.playingFieldDifficult = playingFieldDifficult;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result_dialog2);
        TextView messageText = findViewById(R.id.messageText);
        Button startAgainButton = findViewById(R.id.startAgainButton);

        messageText.setText(message);
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playingFieldDifficult.restartMatch();
                dismiss();
            }
        });

    }
}