package com.example.lab5_1;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView3 extends View {
    int x = 0;
    int y = 0;
    int widthRect = 300;
    int heightRect = 200;
    int strokeWidth = 20;
    int vx = 10;
    int vy = 10;
    public MyView3(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        x = x + vx;
        y = y + vy;
        if (x > canvas.getWidth() - widthRect) {
            vx = vx * -1;
            strokeWidth+=30;
        }
        if (x < 0) {
            vx = vx * -1;
            strokeWidth+=30;
        }
        if (y > canvas.getHeight() - heightRect) {
            vy = vy * -1;
            strokeWidth+=30;
        }
        if (y < 0) {
            vy = vy * -1;
            strokeWidth+=30;
        }
        canvas.drawRect(x,y,widthRect + x,heightRect+y,paint);
        invalidate();
    }
}
