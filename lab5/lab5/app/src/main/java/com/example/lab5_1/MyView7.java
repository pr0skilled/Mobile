package com.example.lab5_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
public class MyView7 extends View {
    int x = 0;
    int y = 0;
    int side = 201;
    int strokeWidth = 20;
    int xEnd = 0;
    int yEnd = 0;
    int blue = 0;
    int green = 255;
    int red = 0;
    int degrees = 0;
    int step = 1;
    int circleCenterX = 0;
    int circleCenterY = 0;
    int radius = 0;

    public MyView7(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.rgb(red, green, blue));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        circleCenterX = canvas.getWidth()/2;
        circleCenterY = canvas.getHeight()/2;
        radius = (int) (canvas.getWidth() * 0.4);
        if (degrees == 360) {
            degrees = 0;
        }
        degrees += step;
        int positionX =  (int) (circleCenterX + radius * Math.cos(Math.toRadians(degrees)));
        int positionY =  (int) (circleCenterY + radius * Math.sin(Math.toRadians(degrees)));
        x = positionX - side/2;
        y = positionY - side/2;
        xEnd = positionX + side/2;
        yEnd = positionY + side/2;

        canvas.drawRect(x,y,xEnd,yEnd,paint);

        invalidate();
    }

    public void increaseStep() {
        if (step < 8) {
            step++;
        }
    }

    public void reduceStep() {
        if (step > 0) {
            step--;
        }
    }
}