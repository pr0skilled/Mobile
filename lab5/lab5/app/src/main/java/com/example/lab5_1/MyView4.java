package com.example.lab5_1;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView4 extends View {
    int x = 0;
    int y = 0;
    int widthRect = 300;
    int heightRect = 200;
    int strokeWidth = 20;
    int vx = 10;
    int vy = 10;
    int blue = 0;
    int green = 0;
    int red = 0;
    public MyView4(Context context) {
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
        if (y > canvas.getHeight() - heightRect) {
            vy = vy * -1;
            modifyRect();
        }
        if (y < 0) {
            vy = vy * -1;
            modifyRect();
        }
        canvas.drawRect(x,y,widthRect + x,heightRect+y,paint);
        invalidate();
    }
}
