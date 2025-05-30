package com.example.dzfragmentmenuapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class FirstPage extends Fragment {

    Spinner spinnerCars;
    Button buttonFindDescription;
    TextView textViewDescription;
   ImageView logoView;
    Array array = new Array();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_page, container, false);

        spinnerCars = view.findViewById(R.id.spinner_list);
        buttonFindDescription = view.findViewById(R.id.button_find_description);
        textViewDescription = view.findViewById(R.id.text_view_description);
        logoView=view.findViewById(R.id.logo);

        buttonFindDescription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = spinnerCars.getSelectedItemPosition();
                String description  = getDescriptionPosition(position);
                textViewDescription.setText(description);

                logoView.setImageResource(array.images1[position]);

            }
        });
        return view;
    }
    private String getDescriptionPosition(int position){
        String[] description = getResources().getStringArray(R.array.description_of_cars);
        return description[position];
    }


}