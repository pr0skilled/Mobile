package com.example.lab4_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Fragment5 extends Fragment {

    View view;
    Button buttonDefault, buttonBlur, buttonClear, buttonReduceNoise, buttonErosion, buttonLighten, buttonSobel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_5, container, false);
        ImageView image = view.findViewById(R.id.image_filter);
        buttonDefault = (Button) view.findViewById(R.id.buttonDefault);
        buttonDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.intel));
            }
        });
        buttonBlur = (Button) view.findViewById(R.id.buttonBlur);
        buttonBlur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    image.setImageBitmap(blur(BitmapFactory.decodeResource(getResources(), R.drawable.intel)));
            }
        });
        buttonClear = (Button) view.findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageBitmap(clear(BitmapFactory.decodeResource(getResources(), R.drawable.intel)));
            }
        });
        buttonReduceNoise = (Button) view.findViewById(R.id.buttonReduceNoise);
        buttonReduceNoise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageBitmap(reduceNoise(BitmapFactory.decodeResource(getResources(), R.drawable.intel)));
            }
        });
        buttonErosion = (Button) view.findViewById(R.id.buttonErosion);
        buttonErosion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageBitmap(darkeiningAndLightening(BitmapFactory.decodeResource(getResources(), R.drawable.intel), new Minimizer()));
            }
        });
        buttonLighten = (Button) view.findViewById(R.id.buttonLighten);
        buttonLighten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageBitmap(darkeiningAndLightening(BitmapFactory.decodeResource(getResources(), R.drawable.intel), new Maximizer()));
            }
        });
        buttonSobel = (Button) view.findViewById(R.id.buttonSobel);
        buttonSobel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageBitmap(sobelOperator(BitmapFactory.decodeResource(getResources(), R.drawable.intel)));
            }
        });

        return view;
    }

    private Bitmap blur(Bitmap src) {
        double matrix[][] = { {0.000789,0.006581,0.013347, 0.006581, 0.000789},
                {0.006581,0.054901,0.111345,0.054901, 0.006581},
                {0.013347,0.111345,0.225821, 0.111345, 0.013347},
                {0.006581,0.054901,0.111345,0.054901,0.006581} ,
                {0.000789,0.006581,0.013347,0.006581, 0.000789}};
        return changeImage(src, matrix, 2, 5);
    }

    private Bitmap clear(Bitmap src) {
        double matrix[][] = { {-1, -1, -1},
                {-1, 9, -1},
                {-1, -1, -1}};

        return changeImage(src, matrix, 1, 3);
    }

    private Bitmap reduceNoise (Bitmap src) {
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        int A, R, G, B;
        int pixelColor;
        int height = src.getHeight();
        int width = src.getWidth();

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                pixelColor = src.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = 0;
                G = 0;
                B = 0;
                if (x > 3 && x < width - 4 && y > 3 && y < height - 4) {
                    ArrayList<Integer> Rarr = new ArrayList<>();
                    ArrayList<Integer> Garr = new ArrayList<>();
                    ArrayList<Integer> Barr = new ArrayList<>();
                    for (int i = 0; i < 7; i++) {
                        for (int j = 0; j < 7; j++) {
                            Rarr.add(Color.red(src.getPixel(x - 3 + i, y - 3 + j)));
                            Garr.add(Color.green(src.getPixel(x - 3 + i, y - 3 + j)));
                            Barr.add(Color.blue(src.getPixel(x - 3 + i, y - 3 + j)));
                        }
                    }
                    Collections.sort(Rarr);
                    Collections.sort(Garr);
                    Collections.sort(Barr);
                    R = Rarr.get(24);
                    G = Garr.get(24);
                    B = Barr.get(24);
                }

                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        return bmOut;
    }

    private Bitmap sobelOperator(Bitmap src) {
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        int A, R, G, B;
        int pixelColor;
        int height = src.getHeight();
        int width = src.getWidth();

        double matrix1[][] = { {-1, -2, -1},
                {0, 0, 0},
                {1, 2, 1}};

        double matrix2[][] = { {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}};

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                pixelColor = src.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);
                int avg = (int)(0.2126*R + 0.7152*G + 0.0722*B);
                bmOut.setPixel(x, y, Color.argb(A, avg, avg, avg));
            }
        }

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (x > 0 && x < width - 1 && y > 0 && y < height - 1) {
                    pixelColor = src.getPixel(x, y);
                    A = Color.alpha(pixelColor);
                    R = 0;
                    G = 0;
                    B = 0;
                    int R1 = 0;
                    int G1 = 0;
                    int B1 = 0;
                    int R2 = 0;
                    int G2 = 0;
                    int B2 = 0;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            R1 += matrix1[i][j] * Color.red(src.getPixel(x - 1 + i, y - 1 + j));
                            G1 += matrix1[i][j] * Color.green(src.getPixel(x - 1 + i, y - 1 + j));
                            B1 += matrix1[i][j] * Color.blue(src.getPixel(x - 1 + i, y - 1 + j));
                            R2 += matrix2[i][j] * Color.red(src.getPixel(x - 1 + i, y - 1 + j));
                            G2 += matrix2[i][j] * Color.green(src.getPixel(x - 1 + i, y - 1 + j));
                            B2 += matrix2[i][j] * Color.blue(src.getPixel(x - 1 + i, y - 1 + j));
                        }
                    }
                    R = (int) Math.sqrt(Math.pow(R1, 2) + Math.pow(R2, 2));
                    G = (int) Math.sqrt(Math.pow(G1, 2) + Math.pow(G2, 2));
                    B = (int) Math.sqrt(Math.pow(B1, 2) + Math.pow(B2, 2));
                    if (R > 255) {
                        R = 255;
                    }
                    if (G > 255) {
                        G = 255;
                    }
                    if (B > 255) {
                        B = 255;
                    }
                    if (R < 0) {
                        R = 0;
                    }
                    if (G < 0) {
                        G = 0;
                    }
                    if (B < 0) {
                        B = 0;
                    }
                    bmOut.setPixel(x, y, Color.argb(A, R, G, B));
                }

            }
        }

        return bmOut;
    }

    private Bitmap changeImage(Bitmap src, double matrix[][], int limit, int m_size) {
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        int A, R, G, B;
        int pixelColor;
        int height = src.getHeight();
        int width = src.getWidth();

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                pixelColor = src.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = 0;
                G = 0;
                B = 0;
                if (x > limit - 1 && x < width - limit && y > limit - 1 && y < height - limit) {
                    for (int i = 0; i < m_size; i++) {
                        for (int j = 0; j < m_size; j++) {
                            R += matrix[i][j] * Color.red(src.getPixel(x - limit + i, y - limit + j));
                            G += matrix[i][j] * Color.green(src.getPixel(x - limit + i, y - limit + j));
                            B += matrix[i][j] * Color.blue(src.getPixel(x - limit + i, y - limit + j));
                        }
                    }
                    if (R > 255) {
                        R = 255;
                    }
                    if (G > 255) {
                        G = 255;
                    }
                    if (B > 255) {
                        B = 255;
                    }
                    if (R < 0) {
                        R = 0;
                    }
                    if (G < 0) {
                        G = 0;
                    }
                    if (B < 0) {
                        B = 0;
                    }
                }

                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        return bmOut;
    }

    private Bitmap darkeiningAndLightening(Bitmap src, MinMax func) {
        int matrix[][] = { {0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0} ,
                {0, 0, 1, 0, 0}};

        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        int A, R, G, B;
        int pixelColor;
        int height = src.getHeight();
        int width = src.getWidth();

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                pixelColor = src.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);;
                G = Color.green(pixelColor);;
                B = Color.blue(pixelColor);;
                ArrayList<Integer> Rarr = new ArrayList<>();
                ArrayList<Integer> Garr = new ArrayList<>();
                ArrayList<Integer> Barr = new ArrayList<>();
                if (x > 2 - 1 && x < width - 2 && y > 2 - 1 && y < height - 2) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            R = matrix[i][j] * Color.red(src.getPixel(x - 2 + i, y - 2 + j));
                            G = matrix[i][j] * Color.green(src.getPixel(x - 2 + i, y - 2 + j));
                            B = matrix[i][j] * Color.blue(src.getPixel(x - 2 + i, y - 2 + j));
                            if (R != 0) {
                                Rarr.add(R);
                            }
                            if (G != 0) {
                                Garr.add(G);
                            }
                            if (B != 0) {
                                Barr.add(B);
                            }
                        }
                    }
                    if (Rarr.size() != 0) {
                        R = func.minmaximize(Rarr);
                    }
                    else {
                        R = 0;
                    }
                    if (Garr.size() != 0) {
                        G = func.minmaximize(Garr);
                    }
                    else {
                        G = 0;
                    }
                    if (Barr.size() != 0) {
                        B = func.minmaximize(Barr);
                    }
                    else {
                        B = 0;
                    }
                }
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        return bmOut;
    }
}

interface MinMax {
    public int minmaximize(ArrayList<Integer> list);
}

class Minimizer implements MinMax {
    public int minmaximize(ArrayList<Integer> list) {
        return Collections.min(list);
    }
}

class Maximizer implements MinMax {
    public int minmaximize(ArrayList<Integer> list) {
        return Collections.max(list);
    }
}