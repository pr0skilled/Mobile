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

public class MyView9 extends View {
    Paint paint;
    int size = 20;
    int n = 51;
    int[][] values;
    int[][] times;
    public static final int INFECTED = 0;
    public static final int IMMUNE = 1;
    public static final int HEALTHY = 2;
    int time = 0;

    public MyView9(Context context) {
        super(context);
        values = new int[n][n];
        times = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                values[i][j] = HEALTHY;
                times[i][j] = 1;
            }
        }
        values[n / 2][n / 2] = INFECTED;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (values[i][j] == HEALTHY) {
                    paint.setColor(Color.rgb(0, 255, 0));
                } else if (values[i][j] == INFECTED) {
                    paint.setColor(Color.rgb(255, 0, 0));
                } else if (values[i][j] == IMMUNE) {
                    paint.setColor(Color.rgb(255, 255, 0));
                }
                canvas.drawRect(size * j + 30, size * i + 494, size + size * j + 30, size + size * i + 494, paint);
            }
        }
        time++;
        if (time == 10) {
            time = 0;
            int[][] old_values = new int[n][n];
            for (int i = 0; i < n; i++) {
                old_values[i] = Arrays.copyOf(values[i], values[i].length);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (old_values[i][j] == INFECTED) {
                        if (times[i][j] == 6) {
                            values[i][j] = IMMUNE;
                            times[i][j] = 1;
                        } else {
                            times[i][j]++;
                        }
                    } else if (old_values[i][j] == IMMUNE) {
                        if (times[i][j] == 20) {
                            values[i][j] = HEALTHY;
                            times[i][j] = 1;
                        } else {
                            times[i][j]++;
                        }
                    } else {
                        boolean c1 = i > 0 && j > 0 && old_values[i - 1][j - 1] == INFECTED;
                        boolean c2 = i > 0 && old_values[i - 1][j] == INFECTED;
                        boolean c3 = j > 0 && old_values[i][j - 1] == INFECTED;
                        boolean c4 = i < n - 1 && j > 0 && old_values[i + 1][j - 1] == INFECTED;
                        boolean c5 = i < n - 1 && old_values[i + 1][j] == INFECTED;
                        boolean c6 = i > 0 && j < n - 1 && old_values[i - 1][j + 1] == INFECTED;
                        boolean c7 = j < n - 1 && old_values[i][j + 1] == INFECTED;
                        boolean c8 = i < n - 1 && j < n - 1 && old_values[i + 1][j + 1] == INFECTED;
                        if (c1 || c2 || c3 || c4 || c5 || c6 || c7 || c8) {
                            Random random = new Random();
                            int num = random.nextInt(9) + 1;
                            if (num > 5) {
                                values[i][j] = INFECTED;
                                times[i][j] = 1;
                            }
                        }
                    }
                }
            }
        }

        invalidate();
    }
}