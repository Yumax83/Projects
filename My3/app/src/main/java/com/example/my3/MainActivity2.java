package com.example.my3;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import android.util.Log;

public class MainActivity2 extends AppCompatActivity {

    private TextView[] labels = new TextView[6];
    private HashMap<String, String[]> labelDataMap = new HashMap<>();
    private HashMap<String, List<String>> parentChildMap = new HashMap<>();

    private ValueAnimator[] animators = new ValueAnimator[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        labels[0] = findViewById(R.id.label_top_beam);
        labels[1] = findViewById(R.id.label_bottom_beam);
        labels[2] = findViewById(R.id.label_left_pillar);
        labels[3] = findViewById(R.id.label_right_pillar);
        labels[4] = findViewById(R.id.label_left_door);
        labels[5] = findViewById(R.id.label_right_door);

// Set tags for each label
        labels[0].setTag("Top Beam");
        labels[1].setTag("Bottom Beam");
        labels[2].setTag("Left Pillar");
        labels[3].setTag("Right Pillar");
        labels[4].setTag("Left Door");
        labels[5].setTag("Right Door");


        // Declare listener before using it
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                textView.setText("X");
                textView.setTextSize(24); // Large size
                textView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                textView.setPadding(8, 5, 8, 5); // Slightly more padding
                textView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                String labelName = textView.getTag().toString();
                Toast.makeText(MainActivity2.this, "You clicked on: " + labelName, Toast.LENGTH_SHORT).show();


                // Get the array of 5 elements for this label
                String[] items = labelDataMap.get(labelName);
                if (items != null && items.length > 0) {
                    showSpinnerDialog(labelName, items);
                } else {
                    Toast.makeText(MainActivity2.this, "No data available for " + labelName, Toast.LENGTH_SHORT).show();
                }
            }
        };

// Set click listener for each label
        for (TextView label : labels) {
            label.setOnClickListener(listener);
        }

        labelDataMap.put("Top Beam", new String[]{
                "Please change...", "Element 1", "Element 2", "Element 3", "Element 4", "Element 5"
        });
        labelDataMap.put("Bottom Beam", new String[]{
                "Please change...", "Item A", "Item B", "Item C", "Item D", "Item E"
        });
        labelDataMap.put("Left Pillar", new String[]{
                "Please change...", "Option 1", "Option 2", "Option 3", "Option 4", "Option 5"
        });
        labelDataMap.put("Right Pillar", new String[]{
                "Please change...", "Choice A", "Choice B", "Choice C", "Choice D", "Choice E"
        });
        labelDataMap.put("Left Door", new String[]{
                "Please change...", "Door 1", "Door 2", "Door 3", "Door 4", "Door 5"
        });
        labelDataMap.put("Right Door", new String[]{
                "Please change...", "Panel 1", "Panel 2", "Panel 3", "Panel 4", "Panel 5"
        });
        parentChildMap.put("Element 1", Arrays.asList("Child 1A", "Child 1B", "Child 1C"));
        parentChildMap.put("Element 2", Arrays.asList("Child 2A", "Child 2B"));
        parentChildMap.put("Element 3", Arrays.asList("Child 3A", "Child 3B", "Child 3C", "Child 3D"));

       parentChildMap.put("Item A", Arrays.asList("Choice 1A", "Choice 1B", "Choice 1C"));
       parentChildMap.put("Item B", Arrays.asList("Choice 1A", "Choice 1B", "Choice 1C"));
       parentChildMap.put("Item C", Arrays.asList("Choice 1A", "Choice 1B", "Choice 1C"));

       parentChildMap.put("Door 1", Arrays.asList("Panel 1A", "Panel 1B", "Panel 1C"));
       parentChildMap.put("Door 2", Arrays.asList("Panel 1A", "Panel 1B", "Panel 1C"));
       parentChildMap.put("Door 3", Arrays.asList("Panel 1A", "Panel 1B", "Panel 1C"));
    }

    private void showSpinnerDialog(String title, String[] items) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_spinner_two_level, null);

        TextView textViewTitle = dialogView.findViewById(R.id.title);
        Spinner spinnerParent = dialogView.findViewById(R.id.spinner_parent);
        Spinner spinnerChild = dialogView.findViewById(R.id.spinner_child);

        textViewTitle.setText(title);

        // Set up parent spinner
        ArrayAdapter<String> parentAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item,
                R.id.textView,
                items);

        parentAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerParent.setAdapter(parentAdapter);

        // Set up child spinner (empty at first)
        ArrayAdapter<String> childAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item,
                R.id.textView,
                new ArrayList<>());
        childAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerChild.setAdapter(childAdapter);

        // Listen for parent selection change
        spinnerParent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { // Skip the hint
                    String selectedItem = items[position];
                    List<String> children = parentChildMap.get(selectedItem);
                    if (children != null && !children.isEmpty()) {
                        childAdapter.clear();
                        childAdapter.addAll(children);
                        childAdapter.notifyDataSetChanged();
                    } else {
                        childAdapter.clear();
                    }
                } else {
                    childAdapter.clear();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> {
                    int selectedIndex = spinnerParent.getSelectedItemPosition();
                    if (selectedIndex > 0) {
                        String selectedParent = (String) spinnerParent.getSelectedItem();
                        String selectedChild = (String) spinnerChild.getSelectedItem();

                        Toast.makeText(MainActivity2.this,
                                "Selected: " + selectedParent + " → " + selectedChild,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity2.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
// Create and configure the dialog
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false); // Prevent dismissing by back press
        dialog.setCanceledOnTouchOutside(false); // Prevent dismissing by tapping outside

        // Optional: Add dimming effect
        dialog.getWindow().setDimAmount(0.7f); // 0.0 = no dim, 1.0 = fully dim

       // builder.show();
        dialog.show();
    }

}