package com.example.my3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

public class ContainerView extends View {

    private Paint containerPaint;
    private Paint borderPaint;
    private Paint beamPaint;
    private Paint doorPaint;
    private Paint highlightPaint;
    private Paint labelPaint;
    private int selectedPart = -1; // 0: top, 1: bottom, 2: left, 3: right, 4: left door, 5: right door

    public ContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setBackgroundResource(R.drawable.contenermodel);
        setAlpha(0.6f);
    }

    private void init() {
        containerPaint = new Paint();
        containerPaint.setColor(Color.GRAY);
        containerPaint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint();
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(4);

        beamPaint = new Paint();
        beamPaint.setColor(Color.DKGRAY);
        beamPaint.setStyle(Paint.Style.FILL);

        doorPaint = new Paint();
        doorPaint.setColor(Color.LTGRAY);
        doorPaint.setStyle(Paint.Style.FILL);

        highlightPaint = new Paint();
        highlightPaint.setColor(Color.YELLOW);
        highlightPaint.setStyle(Paint.Style.FILL);

        labelPaint = new Paint();
        labelPaint.setColor(Color.BLACK);
        labelPaint.setTextSize(30);
        labelPaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        float containerLeft = 70;
        float containerTop = 20;
        float containerRight = width - 70;
        float containerBottom = height - 20;

        // Main container rectangle
        canvas.drawRect(containerLeft, containerTop, containerRight, containerBottom, containerPaint);
        canvas.drawRect(containerLeft, containerTop, containerRight, containerBottom, borderPaint);

        // Top beam
        float topBeamTop = containerTop - 20;
        float topBeamBottom = containerTop;
        canvas.drawRect(containerLeft, topBeamTop, containerRight, topBeamBottom, (selectedPart == 0) ? highlightPaint : beamPaint);
        canvas.drawText("Top Beam", containerLeft + 10, topBeamTop - 10, labelPaint);

        // Bottom beam
        float bottomBeamTop = containerBottom;
        float bottomBeamBottom = containerBottom + 20;
        canvas.drawRect(containerLeft, bottomBeamTop, containerRight, bottomBeamBottom, (selectedPart == 1) ? highlightPaint : beamPaint);
        canvas.drawText("Bottom Beam", containerLeft + 10, bottomBeamBottom + 30, labelPaint);

        // Left side beam
        float leftBeamLeft = containerLeft - 20;
        float leftBeamRight = containerLeft;
        canvas.drawRect(leftBeamLeft, containerTop, leftBeamRight, containerBottom, (selectedPart == 2) ? highlightPaint : beamPaint);
        canvas.drawText("Left Beam", leftBeamLeft - 100, containerTop + 50, labelPaint);

        // Right side beam
        float rightBeamLeft = containerRight;
        float rightBeamRight = containerRight + 20;
        canvas.drawRect(rightBeamLeft, containerTop, rightBeamRight, containerBottom, (selectedPart == 3) ? highlightPaint : beamPaint);
        canvas.drawText("Right Beam", rightBeamRight + 10, containerTop + 50, labelPaint);

        // Left door
        float doorWidth = (containerRight - containerLeft) / 4;
        float leftDoorLeft = containerLeft + 10;
        float leftDoorRight = leftDoorLeft + doorWidth;
        canvas.drawRect(leftDoorLeft, containerTop + 50, leftDoorRight, containerBottom - 50, (selectedPart == 4) ? highlightPaint : doorPaint);
        canvas.drawText("Left Door", leftDoorLeft, containerTop + 40, labelPaint);

        // Right door
        float rightDoorLeft = containerRight - doorWidth - 10;
        float rightDoorRight = containerRight - 10;
        canvas.drawRect(rightDoorLeft, containerTop + 50, rightDoorRight, containerBottom - 50, (selectedPart == 5) ? highlightPaint : doorPaint);
        canvas.drawText("Right Door", rightDoorLeft, containerTop + 40, labelPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                selectedPart = -1;

                // Top beam
                if (x > 50 && x < getWidth() - 50 && y > 50 && y < 70) {
                    selectedPart = 0;
                }
                // Bottom beam
                else if (x > 50 && x < getWidth() - 50 && y > getHeight() - 30 && y < getHeight() - 10) {
                    selectedPart = 1;
                }
                // Left beam
                else if (x > 30 && x < 50 && y > 50 && y < getHeight() - 50) {
                    selectedPart = 2;
                }
                // Right beam
                else if (x > getWidth() - 30 && x < getWidth() - 10 && y > 50 && y < getHeight() - 50) {
                    selectedPart = 3;
                }
                // Left door
                else if (x > 60 && x < 140 && y > 100 && y < 250) {
                    selectedPart = 4;
                }
                // Right door
                else if (x > 560 && x < 640 && y > 100 && y < 250) {
                    selectedPart = 5;
                }

                if (selectedPart != -1) {
                    String[] parts = {"Top Beam", "Bottom Beam", "Left Side Beam", "Right Side Beam", "Left Door", "Right Door"};
                    new android.app.AlertDialog.Builder(getContext())
                            .setTitle("Selected Part")
                            .setMessage("You selected: " + parts[selectedPart])
                            .setPositiveButton("OK", null)
                            .show();

                    // Scale animation
                    Animation scale = new ScaleAnimation(
                            1f, 1.1f, 1f, 1.1f,
                            (getWidth() / 2), (getHeight() / 2)
                    );
                    scale.setDuration(200);
                    startAnimation(scale);
                }

                invalidate(); // Redraw the view
                return true;
        }
        return super.onTouchEvent(event);
    }

    public int getSelectedPart() {
        return selectedPart;
    }
}