package com.example.my3;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import java.util.ArrayList;
import java.util.List;

public class ClickableImageView extends AppCompatImageView {

    private List<Rect> clickableRegions = new ArrayList<>();
    private int lastSelectedRegionIndex = -1;

    public ClickableImageView(Context context) {
        super(context);
    }

    public ClickableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // Method to add a clickable region with bounds (x1, y1, x2, y2)
    public void addRegion(Rect rect) {
        clickableRegions.add(rect);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            for (int i = 0; i < clickableRegions.size(); i++) {
                Rect rect = clickableRegions.get(i);
                if (rect.contains((int) x, (int) y)) {
                    lastSelectedRegionIndex = i;
                    Toast.makeText(getContext(), "Clicked on region " + i, Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    // Get the index of the selected region
    public int getLastSelectedRegionIndex() {
        return lastSelectedRegionIndex;
    }
}