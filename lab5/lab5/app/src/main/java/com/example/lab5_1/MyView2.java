package com.example.lab5_1;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView2 extends View {
    int x = 0;
    int widthRect = 300;
    int vx = 10;
    public MyView2(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        x = x + vx;
        if (x > canvas.getWidth() - widthRect) vx = vx * -1;
        if (x < 0) vx = vx * -1;
        canvas.drawRect(x,200,widthRect + x,500,paint);
        invalidate();
    }
}
