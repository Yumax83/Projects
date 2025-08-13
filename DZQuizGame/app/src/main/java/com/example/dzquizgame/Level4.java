package com.example.dzquizgame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level4 extends AppCompatActivity {
    Dialog dialog;
    Dialog dialogEnd;
    public int numOne;
    public int numTwo;
    Array array = new Array();
    Random random = new Random();

    public int count = 0; // счетчик правильных ответов
    public int i_progress = 0; // счетчик прогресса
    public int count_right = 0; // счетчик прав ответов
    public int count_loose = 0; //
    public static int bal_l4=0; //

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.universal);
        TextView textLevels = findViewById(R.id.zagolovokZadanie);
        textLevels.setText(R.string.level_4);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ImageView imgOne = findViewById(R.id.img_one);
        final ImageView imgTwo = findViewById(R.id.img_two);

        imgOne.setClipToOutline(true);
        imgTwo.setClipToOutline(true);

        //        //меняем фон для 1 уровня
//        ConstraintLayout background = findViewById(R.id.main);
//        background.setBackgroundResource(R.drawable.prev_lev_background_one);


//вызов диалогового окна в начале игры
        dialog = new Dialog(this); // создаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.preview_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        //        //устанавливаем картинку в диалоговое окно 1 уровня
       ImageView previewImg=dialog.findViewById(R.id.preview_img);
       previewImg.setImageResource(R.drawable.eda);

       TextView textDiscription = dialog.findViewById(R.id.text_description);
        textDiscription.setText(R.string.level_four);

        //устанавливаем фон диалогового окна 1 уровня (пока ненужно)
//        LinearLayout dialogFon = dialog.findViewById(R.id.dialog_fon);
//        dialogFon.setBackgroundResource(R.drawable.prev_lev_background_one);

        // кнопка назад
        TextView btnClose = dialog.findViewById(R.id.button_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level4.this, GameLevels.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        //кнопка продолжить
        Button btnContinue = dialog.findViewById(R.id.button_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show(); // показать диалоговое окно

        // Вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialogEnd.setContentView(R.layout.dialog_end); //путь к диалоговому окну
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //фон прозрачный
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT); //чтобы диалоговое окна расширялось на весь экран
        dialogEnd.setCancelable(false); // Нельзя закрыть за пределами окна

       //LinearLayout dialogFonEnd = dialogEnd.findViewById(R.id.dialog_fon);
      // dialogFonEnd.setBackgroundResource(R.drawable.prev_lev_background_one);

        TextView descriptionEnd = dialogEnd.findViewById(R.id.description_end);
        descriptionEnd.setText(R.string.level_four_end);

        //интересный факт
        TextView textDescriptionEnd = dialogEnd.findViewById(R.id.text_description_end);
        textDescriptionEnd.setText(R.string.level_four_text_end);

        TextView btnClose2 = dialogEnd.findViewById(R.id.button_close);
        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // вернемся назад к выбору уровня
                Intent intent = new Intent(Level4.this, GameLevels.class);
                startActivity(intent);
                dialogEnd.dismiss();
            }
        });

        Button btnContinue2 = dialogEnd.findViewById(R.id.button_continue);
        btnContinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level4.this, Level5.class);
                startActivity(intent);
                dialogEnd.dismiss();
            }
        });

        Button btnBack = findViewById(R.id.button_back_levels);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level4.this, GameLevels.class);
                startActivity(intent);
            }
        });

        //анимация подключаем
        final Animation animation = AnimationUtils.loadAnimation(Level4.this, R.anim.alpha);

        //значения для первой картинки генерация
        numOne = random.nextInt(20);
        imgOne.setImageResource(array.images44[numOne]);

        //значения для второй картинки генерация
        do {
            numTwo = random.nextInt(20);
        } while (array.choice[numOne] == array.choice[numTwo]);
        imgTwo.setImageResource(array.images44[numTwo]);

        //массив для прогресса игры
        final int[] progress = {R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7,
                R.id.point8, R.id.point9, R.id.point10, R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15};


        //обрабатываем нажатие на первую картнку
        imgOne.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {// условие если картинку нажали
                    imgTwo.setEnabled(false); //блокируем вторую картинку
                    if (array.choice[numOne] > array.choice[numTwo]) {
                        imgOne.setImageResource(R.drawable.right1111);
                    } else {
                        imgOne.setImageResource(R.drawable.loose1111);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) { // если отпустили палец
                    if (array.choice[numOne] > array.choice[numTwo]) {
                        if (count < 15) {
                            count++;
                            TextView tv = findViewById(progress[i_progress]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                            i_progress++;
                            count_right = count_right + 1;
                        }
                    } else {
                        if (count < 15) {
                            count++;
                            TextView tv = findViewById(progress[i_progress]);
                            tv.setBackgroundResource(R.drawable.style_points_red);
                            i_progress++;
                        }
                    }
                    if (count == 15) { //выход из уровня
                        SharedPreferences save = getSharedPreferences("save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 4);
                        if (level <= 4) {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 5);
                            editor.apply();
                        }
                        dialogEnd.show();
                        TextView textResult = dialogEnd.findViewById(R.id.reslevel);
                        count_loose=15-count_right;
                        bal_l4=count_right*1;
                        textResult.setText("В этом задании Вы набрали "+bal_l4+" баллов!\n Правильных ответов: "+ count_right + "\n Неправильных ответов: " + count_loose);
                        TextView markResult = dialogEnd.findViewById(R.id.markLevel);
                        if (count_right<6) {
                            markResult.setTextColor(Color.RED);
                            markResult.setText("(плохо)");
                        }
                        if (count_right>=6 && count_right<=10) {
                            markResult.setTextColor(Color.GRAY);
                            markResult.setText("(удовлетворительно)");
                        }
                        if (count_right>10 && count_right<=14) {
                            markResult.setTextColor(Color.YELLOW);
                            markResult.setText("(хорошо)");
                        }
                        if (count_right ==15) {
                            markResult.setTextColor(Color.GREEN);
                            markResult.setText("(Великолепно)");
                        }

                    } else {
                        numOne = random.nextInt(20);
                        imgOne.setImageResource(array.images44[numOne]); //Достаем из массива картнку
                        imgOne.startAnimation(animation); //запускаем анимацию для левой картинки

                        do {
                            numTwo = random.nextInt(20);
                        } while (array.choice[numOne] == array.choice[numTwo]);
                        imgTwo.setImageResource(array.images44[numTwo]); //Достаем из массива картнку
                        imgTwo.startAnimation(animation); //запускаем анимацию для правой картинки
                        imgTwo.setEnabled(true); //включаем вторую картику

                    }
                }
                return true;
            }
        });

        //обрабатываем нажатие на вторую картнку
        imgTwo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {// условие если картинку нажали
                    imgOne.setEnabled(false); //блокируем первую картинку
                    if (array.choice[numOne] < array.choice[numTwo]) {
                        imgTwo.setImageResource(R.drawable.right1111);
                    } else {
                        imgTwo.setImageResource(R.drawable.loose1111);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) { // если отпустили палец
                    if (array.choice[numOne] < array.choice[numTwo]) {
                        if (count < 15) {
                            count++;
                            TextView tv = findViewById(progress[i_progress]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                            i_progress++;
                            count_right = count_right + 1;
                        }
                    } else {
                        if (count < 15) {
                            count++;
                            TextView tv = findViewById(progress[i_progress]);
                            tv.setBackgroundResource(R.drawable.style_points_red);
                            i_progress++;
                        }
                    }
                    if (count == 15) { //выход из уровня
                        SharedPreferences save = getSharedPreferences("save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 4);
                        if (level <= 4) {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 5);
                            editor.apply();
                        }
                        dialogEnd.show();
                        TextView textResult = dialogEnd.findViewById(R.id.reslevel);
                        count_loose=15-count_right;
                        bal_l4=count_right*1;
                        textResult.setText("В этом задании Вы набрали "+bal_l4+" баллов!\n Правильных ответов: "+ count_right + "\n Неправильных ответов: " + count_loose);
                        TextView markResult = dialogEnd.findViewById(R.id.markLevel);
                        if (count_right<6) {
                            markResult.setTextColor(Color.RED);
                            markResult.setText("(плохо)");
                        }
                        if (count_right>=6 && count_right<=10) {
                            markResult.setTextColor(Color.GRAY);
                            markResult.setText("(удовлетворительно)");
                        }
                        if (count_right>10 && count_right<=14) {
                            markResult.setTextColor(Color.YELLOW);
                            markResult.setText("(хорошо)");
                        }
                        if (count_right ==15) {
                            markResult.setTextColor(Color.GREEN);
                            markResult.setText("(Великолепно)");
                        }

                    } else {
                        numOne = random.nextInt(20);
                        imgOne.setImageResource(array.images44[numOne]); //Достаем из массива картнку
                        imgOne.startAnimation(animation); //запускаем анимацию для левой картинки
                       // numTwo = random.nextInt(20);
                        do {
                            numTwo = random.nextInt(20);
                        } while (array.choice[numOne] == array.choice[numTwo]);
                        imgTwo.setImageResource(array.images44[numTwo]); //Достаем из массива картнку
                        imgTwo.startAnimation(animation); //запускаем анимацию для правой картинки
                        imgOne.setEnabled(true); //включаем первую картику

                    }
                }
                return true;
            }
        });
    }
}