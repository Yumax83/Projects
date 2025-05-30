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

public class PlayingFieldDifficult extends AppCompatActivity {
    private final List<int[]> combinationlist = new ArrayList<>();
    private int activePlayer = 1;
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int totalSelectBoxes = 1;
    private TextView playerName1;
    private TextView androidName1;
    private int currentScoreOne = 0;
    private int currentScoreAndroid = 0;
    ImageView[] image = new ImageView[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playing_field_difficult);

        //changePlayerTurn(activePlayer);


        combinationlist.add(new int[]{0, 1, 2, 3, 4});
        combinationlist.add(new int[]{5, 6, 7, 8, 9});
        combinationlist.add(new int[]{10, 11, 12, 13, 14});
        combinationlist.add(new int[]{15, 16, 17, 18, 19});
        combinationlist.add(new int[]{20, 21, 22, 23, 24});
        combinationlist.add(new int[]{0, 5, 10, 15, 20});
        combinationlist.add(new int[]{1, 6, 11, 16, 21});
        combinationlist.add(new int[]{2, 7, 12, 17, 22});
        combinationlist.add(new int[]{3, 8, 13, 18, 23});
        combinationlist.add(new int[]{4, 9, 14, 19, 24});
        combinationlist.add(new int[]{0, 6, 12, 18, 24});
        combinationlist.add(new int[]{4, 8, 12, 16, 20});


        playerName1 = findViewById(R.id.playerName1);
        androidName1 = findViewById(R.id.androidName1);

        String getPlayerName = getIntent().getStringExtra("Player");
        //String getAndroidName = getIntent().getStringExtra("Android");

        playerName1.setText(getPlayerName);
        //  androidName1.setText(getAndroidName);

        image[0] = findViewById(R.id.image1);
        image[1] = findViewById(R.id.image2);
        image[2] = findViewById(R.id.image3);
        image[3] = findViewById(R.id.image4);
        image[4] = findViewById(R.id.image5);
        image[5] = findViewById(R.id.image6);
        image[6] = findViewById(R.id.image7);
        image[7] = findViewById(R.id.image8);
        image[8] = findViewById(R.id.image9);
        image[9] = findViewById(R.id.image10);
        image[10] = findViewById(R.id.image11);
        image[11] = findViewById(R.id.image12);
        image[12] = findViewById(R.id.image13);
        image[13] = findViewById(R.id.image14);
        image[14] = findViewById(R.id.image15);
        image[15] = findViewById(R.id.image16);
        image[16] = findViewById(R.id.image17);
        image[17] = findViewById(R.id.image18);
        image[18] = findViewById(R.id.image19);
        image[19] = findViewById(R.id.image20);
        image[20] = findViewById(R.id.image21);
        image[21] = findViewById(R.id.image22);
        image[22] = findViewById(R.id.image23);
        image[23] = findViewById(R.id.image24);
        image[24] = findViewById(R.id.image25);


        image[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(0)) {
                    performAction((ImageView) v, 0);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
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

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
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

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
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

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
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

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
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

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
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

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
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

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
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

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(9)) {
                    performAction((ImageView) v, 9);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                } }
            }
        });
        image[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(10)) {
                    performAction((ImageView) v, 10);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(11)) {
                    performAction((ImageView) v, 11);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(12)) {
                    performAction((ImageView) v, 12);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(13)) {
                    performAction((ImageView) v, 13);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(14)) {
                    performAction((ImageView) v, 14);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(15)) {
                    performAction((ImageView) v, 15);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(16)) {
                    performAction((ImageView) v, 16);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(17)) {
                    performAction((ImageView) v, 17);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(18)) {
                    performAction((ImageView) v, 18);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(19)) {
                    performAction((ImageView) v, 19);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(20)) {
                    performAction((ImageView) v, 20);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(21)) {
                    performAction((ImageView) v, 21);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(22)) {
                    performAction((ImageView) v, 22);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(23)) {
                    performAction((ImageView) v, 23);

                if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }}
            }
        });
        image[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer = 1;
                if (isBoxSelectTable(24)) {
                    performAction((ImageView) v, 24);

               if ((!checkResults1()) && (totalSelectBoxes != 25)) {
                    performActionAndroid((ImageView) v, randomPos());
                }
                }
            }
        });
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        activePlayer = currentPlayerTurn;

        LinearLayout playerLayoutOuter = findViewById(R.id.playerLayoutOuter1);
        LinearLayout androidLayoutOuter = findViewById(R.id.androidLayoutOuter1);

        if (activePlayer == 1) {

            androidLayoutOuter.setBackgroundResource(R.drawable.white_box);
        } else {

            playerLayoutOuter.setBackgroundResource(R.drawable.white_box);
        }
    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = activePlayer;

        playerName1 = findViewById(R.id.playerName1);


        TextView scorePlayer = findViewById(R.id.scoreOne);

        image[selectedBoxPosition].setImageResource(R.drawable.xim);
        image[selectedBoxPosition].setBackgroundResource(R.drawable.white_box1);
        image[selectedBoxPosition].setScaleType(ImageView.ScaleType.FIT_CENTER);


        if (checkResults1()) {
            ResultDialog2 resultDialog1 = new ResultDialog2(PlayingFieldDifficult.this, playerName1.getText().toString() + " is winner", PlayingFieldDifficult.this);
            resultDialog1.setCancelable(false);
            resultDialog1.show();
            currentScoreOne++;
            scorePlayer.setText(String.valueOf(currentScoreOne));
        } else if (totalSelectBoxes == 25) {
            ResultDialog2 resultDialog1 = new ResultDialog2(PlayingFieldDifficult.this, " Math Draw", PlayingFieldDifficult.this);
            resultDialog1.setCancelable(false);
            resultDialog1.show();
        } else {
            changePlayerTurn(2);
            totalSelectBoxes++;

        }
    }

    private void performActionAndroid(ImageView imageView, int pos) {
        TextView scoreAndroid = findViewById(R.id.scoreAndroid);
        androidName1 = findViewById(R.id.androidName1);

        if (isBoxSelectTable(pos)) {
            boxPositions[pos] = activePlayer;


            image[pos].setImageResource(R.drawable.oim);
            image[pos].setBackgroundResource(R.drawable.white_box1);
            image[pos].setScaleType(ImageView.ScaleType.FIT_CENTER);

            if (checkResults1()) {
                ResultDialog2 resultDialog1 = new ResultDialog2(PlayingFieldDifficult.this, androidName1.getText().toString() + " is winner", PlayingFieldDifficult.this);
                resultDialog1.setCancelable(false);
                resultDialog1.show();
                currentScoreAndroid++;
                scoreAndroid.setText(String.valueOf(currentScoreAndroid));
            } else if (totalSelectBoxes == 25) {
                ResultDialog2 resultDialog1 = new ResultDialog2(PlayingFieldDifficult.this, " Math Draw", PlayingFieldDifficult.this);
                resultDialog1.setCancelable(false);
                resultDialog1.show();
            } else {
            changePlayerTurn(1);
            totalSelectBoxes++;

        }
      }
        else {
            performActionAndroid(imageView, randomPos());
        }
    }

    private boolean checkResults1() {
        boolean response = false;
        for (int i = 0; i < combinationlist.size(); i++) {
            final int[] combination = combinationlist.get(i);

            if (boxPositions[combination[0]] == activePlayer && boxPositions[combination[1]] == activePlayer && boxPositions[combination[2]] == activePlayer && boxPositions[combination[3]] == activePlayer && boxPositions[combination[4]] == activePlayer) {
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
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
        image[9] = findViewById(R.id.image10);
        image[10] = findViewById(R.id.image11);
        image[11] = findViewById(R.id.image12);
        image[12] = findViewById(R.id.image13);
        image[13] = findViewById(R.id.image14);
        image[14] = findViewById(R.id.image15);
        image[15] = findViewById(R.id.image16);
        image[16] = findViewById(R.id.image17);
        image[17] = findViewById(R.id.image18);
        image[18] = findViewById(R.id.image19);
        image[19] = findViewById(R.id.image20);
        image[20] = findViewById(R.id.image21);
        image[21] = findViewById(R.id.image22);
        image[22] = findViewById(R.id.image23);
        image[23] = findViewById(R.id.image24);
        image[24] = findViewById(R.id.image25);


        image[0].setImageResource(R.drawable.white_box1);
        image[1].setImageResource(R.drawable.white_box1);
        image[2].setImageResource(R.drawable.white_box1);
        image[3].setImageResource(R.drawable.white_box1);
        image[4].setImageResource(R.drawable.white_box1);
        image[5].setImageResource(R.drawable.white_box1);
        image[6].setImageResource(R.drawable.white_box1);
        image[7].setImageResource(R.drawable.white_box1);
        image[8].setImageResource(R.drawable.white_box1);
        image[9].setImageResource(R.drawable.white_box1);
        image[10].setImageResource(R.drawable.white_box1);
        image[11].setImageResource(R.drawable.white_box1);
        image[12].setImageResource(R.drawable.white_box1);
        image[13].setImageResource(R.drawable.white_box1);
        image[14].setImageResource(R.drawable.white_box1);
        image[15].setImageResource(R.drawable.white_box1);
        image[16].setImageResource(R.drawable.white_box1);
        image[17].setImageResource(R.drawable.white_box1);
        image[18].setImageResource(R.drawable.white_box1);
        image[19].setImageResource(R.drawable.white_box1);
        image[20].setImageResource(R.drawable.white_box1);
        image[21].setImageResource(R.drawable.white_box1);
        image[22].setImageResource(R.drawable.white_box1);
        image[23].setImageResource(R.drawable.white_box1);
        image[24].setImageResource(R.drawable.white_box1);


    }

    public int randomPos() {
        Random random = new Random();
        int randPos = random.nextInt(24);
        return randPos;
    }

}