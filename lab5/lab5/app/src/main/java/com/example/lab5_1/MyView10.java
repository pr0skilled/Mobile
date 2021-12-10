package com.example.lab5_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyView10 extends View {
    Paint paint;
    int side = 201;
    int strokeWidth = 20;
    int blue = 0;
    int green = 255;
    int red = 0;
    double degrees = 0;
    double step = 3;
    int centerX = 0;
    int centerY = 0;
    int a = 300;
    int l = 250;

    public MyView10(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setColor(Color.rgb(red, green, blue));
        centerX = canvas.getWidth()/2;
        centerY = canvas.getHeight()/2;

        for (double i = 0; i < degrees; i++) {
            int positionX =  (int) (a*Math.sin(i/50) - l*Math.sin(2*i/50) + centerX);
            int positionY =  (int) (- a*Math.cos(i/50) + l*Math.cos(2*i/50) + centerY);
            canvas.drawCircle(positionX, positionY, 16, paint);
        }

        degrees += step;
        if (degrees == 360) {
            degrees = 0;
        }

        invalidate();
    }

}