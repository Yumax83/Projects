package com.example.gamexowithandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayingFieldEasy extends AppCompatActivity {

    private final List<int[]> combinationlist = new ArrayList<>();

    private int activePlayer = 1;
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int totalSelectBoxes = 1;

    //  private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;

    private TextView playerName;
    private TextView androidName;

    private int currentScoreOne = 0;
    private int currentScoreAndroid = 0;
    ImageView[] image = new ImageView[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playing_field_easy);

        combinationlist.add(new int[]{0, 1, 2});
        combinationlist.add(new int[]{3, 4, 5});
        combinationlist.add(new int[]{6, 7, 8});
        combinationlist.add(new int[]{0, 3, 6});
        combinationlist.add(new int[]{1, 4, 7});
        combinationlist.add(new int[]{2, 5, 8});
        combinationlist.add(new int[]{0, 4, 8});
        combinationlist.add(new int[]{2, 4, 6});


        playerName = findViewById(R.id.playerName);
        androidName = findViewById(R.id.androidName);

        String getPlayerName = getIntent().getStringExtra("Player");
        String getAndroidName = getIntent().getStringExtra("Android");

        playerName.setText(getPlayerName);
        androidName.setText(getAndroidName);

        image[0] = findViewById(R.id.image1);
        image[1] = findViewById(R.id.image2);
        image[2] = findViewById(R.id.image3);
        image[3] = findViewById(R.id.image4);
        image[4] = findViewById(R.id.image5);
        image[5] = findViewById(R.id.image6);
        image[6] = findViewById(R.id.image7);
        image[7] = findViewById(R.id.image8);
        image[8] = findViewById(R.id.image9);

        changePlayerTurn(activePlayer);

        image[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(0)) {
                    performAction((ImageView) v, 0);

                if ((!checkResults()) && (totalSelectBoxes != 9)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(1)) {
                    performAction((ImageView) v, 1);

                if ((!checkResults()) && (totalSelectBoxes != 9)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(2)) {
                    performAction((ImageView) v, 2);

                if ((!checkResults()) && (totalSelectBoxes != 9)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(3)) {
                    performAction((ImageView) v, 3);

                if ((!checkResults()) && (totalSelectBoxes != 9)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(4)) {
                    performAction((ImageView) v, 4);

                if ((!checkResults()) && (totalSelectBoxes != 9)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(5)) {
                    performAction((ImageView) v, 5);

                if ((!checkResults()) && (totalSelectBoxes != 9)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(6)) {
                    performAction((ImageView) v, 6);

                if ((!checkResults()) && (totalSelectBoxes != 9)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(7)) {
                    performAction((ImageView) v, 7);

                if ((!checkResults()) && (totalSelectBoxes != 9)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(8)) {
                    performAction((ImageView) v, 8);

                    if ((!checkResults()) && (totalSelectBoxes != 9)) {
                        performActionAndroid((ImageView) v, randomPos());
                    }
                }
            }
        });
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        activePlayer = currentPlayerTurn;

        LinearLayout playerLayoutOuter = findViewById(R.id.playerLayoutOuter);
        LinearLayout androidLayoutOuter = findViewById(R.id.androidLayoutOuter);

        if (activePlayer == 1) {
            playerLayoutOuter.setBackgroundResource(R.drawable.black_border);
            androidLayoutOuter.setBackgroundResource(R.drawable.white_box);
        } else {
            androidLayoutOuter.setBackgroundResource(R.drawable.black_border);
            playerLayoutOuter.setBackgroundResource(R.drawable.white_box);
        }
    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {


            boxPositions[selectedBoxPosition] = activePlayer;
            playerName = findViewById(R.id.playerName);
            androidName = findViewById(R.id.androidName);

            TextView scorePlayer = findViewById(R.id.scoreOne);

            image[selectedBoxPosition].setImageResource(R.drawable.xim);
            image[selectedBoxPosition].setBackgroundResource(R.drawable.white_box);
            image[selectedBoxPosition].setScaleType(ImageView.ScaleType.CENTER);

            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(PlayingFieldEasy.this, playerName.getText().toString() + " is winner", PlayingFieldEasy.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
                currentScoreOne++;
                scorePlayer.setText(String.valueOf(currentScoreOne));
            } else if (totalSelectBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(PlayingFieldEasy.this, " Math Draw", PlayingFieldEasy.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(2);
                totalSelectBoxes++;

        }
    }

    private void performActionAndroid(ImageView imageView, int pos) {
        TextView scoreAndroid = findViewById(R.id.scoreAndroid);

        if (isBoxSelectTable(pos)) {
            boxPositions[pos] = activePlayer;
            playerName = findViewById(R.id.playerName);
            androidName = findViewById(R.id.androidName);

            image[pos].setImageResource(R.drawable.oim);
            image[pos].setBackgroundResource(R.drawable.white_box);
            image[pos].setScaleType(ImageView.ScaleType.CENTER);

            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(PlayingFieldEasy.this, androidName.getText().toString() + " is winner", PlayingFieldEasy.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
                currentScoreAndroid++;
                scoreAndroid.setText(String.valueOf(currentScoreAndroid));
            } else if (totalSelectBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(PlayingFieldEasy.this, " Math Draw", PlayingFieldEasy.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(1);
                totalSelectBoxes++;
            }
        } else {
            performActionAndroid(imageView, randomPos());
        }
    }

    private boolean checkResults() {
        boolean response = false;
        for (int i = 0; i < combinationlist.size(); i++) {
            final int[] combination = combinationlist.get(i);

            if (boxPositions[combination[0]] == activePlayer && boxPositions[combination[1]] == activePlayer && boxPositions[combination[2]] == activePlayer) {
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxSelectTable(int boxPosition) {
        boolean response = false;
        if (boxPositions[boxPosition] == 0) {
            response = true;
        }
        return response;
    }

    public void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        activePlayer = 1;
        totalSelectBoxes = 1;
        image[0] = findViewById(R.id.image1);
        image[1] = findViewById(R.id.image2);
        image[2] = findViewById(R.id.image3);
        image[3] = findViewById(R.id.image4);
        image[4] = findViewById(R.id.image5);
        image[5] = findViewById(R.id.image6);
        image[6] = findViewById(R.id.image7);
        image[7] = findViewById(R.id.image8);
        image[8] = findViewById(R.id.image9);

        image[0].setImageResource(R.drawable.white_box);
        image[1].setImageResource(R.drawable.white_box);
        image[2].setImageResource(R.drawable.white_box);
        image[3].setImageResource(R.drawable.white_box);
        image[4].setImageResource(R.drawable.white_box);
        image[5].setImageResource(R.drawable.white_box);
        image[6].setImageResource(R.drawable.white_box);
        image[7].setImageResource(R.drawable.white_box);
        image[8].setImageResource(R.drawable.white_box);
    }

    public int randomPos() {
        Random random = new Random();
        int randPos = random.nextInt(8);
        return randPos;
    }

}