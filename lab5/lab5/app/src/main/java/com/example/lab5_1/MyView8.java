package com.example.lab5_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
public class MyView8 extends View {
    int p1x = 0;
    int p1y = 0;
    int p2x = 0;
    int p2y = 0;
    int p3x = 0;
    int p3y = 0;
    int red = 0;
    int green = 255;
    int blue = 0;
    int strokeWidth = 10;
    Paint paint;
    double i = 1;

    public MyView8(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setColor(Color.rgb(red, green, blue));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
            p3x = canvas.getWidth();
            int height = (int) (p3x * Math.sin(Math.toRadians(60)));
            p2y = (canvas.getHeight() - height)/2;
            p1y = canvas.getHeight() - p2y;
            p3y = p1y;
            p2x = canvas.getWidth()/2;
        canvas.drawLine(p1x,p1y,p2x,p2y,paint);
        canvas.drawLine(p2x,p2y,p3x,p3y,paint);
        canvas.drawLine(p1x,p1y,p3x,p3y,paint);
        drawTriangle(canvas, p1x, p2x, p3x, p1y, p2y, p3y);
        i+=0.1;
        invalidate();
    }

    private void drawTriangle(Canvas canvas, int p1x, int p2x, int p3x, int p1y, int p2y, int p3y) {

        int old_p1x = p1x;
        int old_p2x = p2x;
        int old_p3x = p3x;
        int old_p1y = p1y;
        int old_p2y = p2y;
        int old_p3y = p3y;

        p1x = (old_p1x + old_p2x) / 2;
        p1y = (old_p1y + old_p2y) / 2;
        p2x = (old_p1x + old_p3x) / 2;
        p2y = (old_p1y + old_p3y) / 2;
        p3x = (old_p3x + old_p2x) / 2;
        p3y = (old_p3y + old_p2y) / 2;
        canvas.drawLine(p1x,p1y,p2x,p2y,paint);
        canvas.drawLine(p2x,p2y,p3x,p3y,paint);
        canvas.drawLine(p1x,p1y,p3x,p3y,paint);
        if (p2x - p1x > canvas.getWidth()/(2*i)) {
            drawTriangle(canvas, p1x, old_p2x, p3x, p1y, old_p2y, p3y);
            drawTriangle(canvas, old_p1x, p1x, p2x, old_p1y, p1y, p2y);
            drawTriangle(canvas, p2x, p3x, old_p3x, p2y, p3y, old_p3y);
        }

    }

}