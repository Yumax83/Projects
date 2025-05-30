package com.example.third;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CulcDiscount extends AppCompatActivity {
    EditText costGood;
    TextView discFinish, accCulc;
    Button calcBtn, newAccBtn;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mycost";
    private static final String KEY_COSTGOOD = "costGood";
    private static final String KEY_ACCCULC = "accCulc";
    private static final String KEY_DISCFINISH = "DiscFinish";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culc_discount);
        costGood = findViewById(R.id.cost_of_good);
        discFinish = findViewById(R.id.disc_finish);
        accCulc = findViewById(R.id.acc_culc);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String infCoastGood = sharedPreferences.getString(KEY_COSTGOOD, null);
        String infAccCulc = sharedPreferences.getString(KEY_ACCCULC, null);
        String infDiscFinish = sharedPreferences.getString(KEY_DISCFINISH, null);

        if (infCoastGood != null) {
            costGood.setText(infCoastGood);
            accCulc.setText(infAccCulc);
            discFinish.setText(infDiscFinish);
        }
        calcBtn = findViewById(R.id.culc_btn);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String costEditStr = costGood.getText().toString();
                if (!costEditStr.isEmpty()) {
                    int cost = Integer.parseInt(costEditStr);
                    double accCulcFinish = 0;
                    if (cost == 0 || cost < 500) {
                        String resAcc = String.valueOf(cost);
                        String textAcc = "Общая стоимость: " + resAcc;
                        accCulc.setText(textAcc);
                        String textDisc = "Ваша скидка составила: 0%";
                        discFinish.setText(textDisc);
                    } else if (cost >= 500 && cost <= 1000) {
                        accCulcFinish = cost * 0.97;
                        String resAcc = String.valueOf(accCulcFinish);
                        String textAcc = "Общая стоимость: " + resAcc;
                        accCulc.setText(textAcc);
                        String textDisc = "Ваша скидка составила: 3%";
                        discFinish.setText(textDisc);
                    } else if (cost > 1000) {
                        accCulcFinish = cost * 0.95;
                        String resAcc = String.valueOf(accCulcFinish);
                        String textAcc = "Общая стоимость: " + resAcc;
                        accCulc.setText(textAcc);
                        String textDisc = "Ваша скидка составила: 5%";
                        discFinish.setText(textDisc);
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_COSTGOOD, costGood.getText().toString());
                    editor.putString(KEY_ACCCULC, accCulc.getText().toString());
                    editor.putString(KEY_DISCFINISH, discFinish.getText().toString());
                    editor.apply();
                    Toast.makeText(CulcDiscount.this, "Расчет записан!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(CulcDiscount.this, "Введите стоимость товаров!", Toast.LENGTH_SHORT).show();
                }
            }
        });
            newAccBtn = findViewById(R.id.new_acc_btn);
        newAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                String costGoodText = null;
                String accCulcText = "Общая стоимость: ";
                String discFinishText = "Ваша скидка составила: ";
                accCulc.setText(accCulcText);
                discFinish.setText(discFinishText);
                costGood.setText(costGoodText);

                Toast.makeText(CulcDiscount.this, "Начните заново", Toast.LENGTH_SHORT).show();
            }
        });
      Button  exitBtn = findViewById(R.id.exit_Btn);
      exitBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });
    }

}