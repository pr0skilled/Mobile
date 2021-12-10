package com.example.lab5_1;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView5 extends View {
    int x = 0;
    int y = 0;
    int widthRect = 300;
    int heightRect = 200;
    int vx = 10;
    int vy = 10;
    int x2 = 800;
    int y2 = 500;
    int widthRect2 = 300;
    int heightRect2 = 200;
    int vx2 = -10;
    int vy2 = -10;
    int strokeWidth = 20;
    int blue = 0;
    int green = 0;
    int red = 0;
    public MyView5(Context context) {
        super(context);
    }
    void modifyRect() {
        strokeWidth = (int)(Math.random()*100);
        blue = (int)(Math.random()*255);
        green = (int)(Math.random()*255);
        red = (int)(Math.random()*255);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.rgb(red, green, blue));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        x = x + vx;
        y = y + vy;
        if (x > canvas.getWidth() - widthRect) {
            vx = vx * -1;
            modifyRect();
        }
        if (x < 0) {
            vx = vx * -1;
            modifyRect();
        }
        if (y > canvas.getHeight() - heightRect) {vy = vy * -1;
            modifyRect();
        }
        if (y < 0) {
            vy = vy * -1;
            modifyRect();
        }
        canvas.drawRect(x,y,widthRect + x,heightRect+y,paint);
        x2 = x2 + vx2;
        y2 = y2 + vy2;
        if (x2 > canvas.getWidth() - widthRect) {
            vx2 = vx2 * -1;
            modifyRect();
        }
        if (x2 < 0) {
            vx2 = vx2 * -1;
            modifyRect();
        }
        if (y2 > canvas.getHeight() - heightRect) {
            vy2 = vy2 * -1;
            modifyRect();
        }
        if (y2 < 0) {
            vy2 = vy2 * -1;
            modifyRect();
        }
        canvas.drawRect(x2,y2,widthRect2 +
                x2,heightRect2+y2,paint);
        invalidate();
    }
}