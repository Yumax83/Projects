package com.example.addtry;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Spinner mySpinner, mySpinner2;

    String[] countries = {"Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       mySpinner2 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Определяем разметку для использования при выборе элемента
       mySpinner2.setAdapter(adapter);  // Применяем адаптер к элементу spinner
        mySpinner2.setSelection(0);
        initViews();



//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.YEAR, -1); // Минимальная дата - год назад
//        calendar.add(Calendar.YEAR, 2);  // Максимальная дата - через 2 года
//
//        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), calendar.getTime(), null, Calendar.DAY_OF_MONTH);
//        mySpinner3 dateSpinner = new mySpinner3(dateModel);
//        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), calendar.getTime(), null, Calendar.DAY_OF_MONTH);
//        JSpinner dateSpinner = new JSpinner(dateModel);


    }

    private void initViews() {
        mySpinner = findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.animals, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
// Обработчик выбора элементов
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
// Действия при выборе элемента
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
// Действия, если ничего не выбрано
            }
        });
    }
}