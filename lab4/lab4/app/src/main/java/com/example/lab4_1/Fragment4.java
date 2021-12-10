package com.example.lab4_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

public class Fragment4 extends Fragment {

    View view;
    private double alpha;
    Button buttonDecrease;
    Button buttonIncrease;
    TextView tv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_4, container, false);
        alpha = 0.5;
        buttonDecrease = (Button) view.findViewById(R.id.button_dec);
        buttonIncrease = (Button) view.findViewById(R.id.button_inc);
        tv = (TextView) view.findViewById(R.id.textView);
        ImageView image3 = view.findViewById(R.id.image4_3);
        image3.setImageBitmap(mergeImages(BitmapFactory.decodeResource(getResources(), R.drawable.merge_img_1),
                BitmapFactory.decodeResource(getResources(), R.drawable.merge_img_2), alpha));
        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alpha != 0.1) {
                    alpha -= 0.05;
                    alpha *= 100;
                    alpha = Math.round(alpha);
                    alpha /= 100;
                    image3.setImageBitmap(mergeImages(BitmapFactory.decodeResource(getResources(), R.drawable.merge_img_1),
                            BitmapFactory.decodeResource(getResources(), R.drawable.merge_img_2), alpha));
                    tv.setText("alpha = " + String.valueOf(alpha));
                }
            }
        });
        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alpha != 0.9) {
                    alpha += 0.05;
                    alpha *= 100;
                    alpha = Math.round(alpha);
                    alpha /= 100;
                    image3.setImageBitmap(mergeImages(BitmapFactory.decodeResource(getResources(), R.drawable.merge_img_1),
                            BitmapFactory.decodeResource(getResources(), R.drawable.merge_img_2), alpha));
                    tv.setText("alpha = " + String.valueOf(alpha));
                }
            }
        });

        return view;
    }

    private Bitmap mergeImages(Bitmap src1, Bitmap src2, double alpha) {
        Bitmap bmOut = Bitmap.createBitmap(src1.getWidth(), src1.getHeight(), src1.getConfig());
        int A1, R1, G1, B1;
        int A2, R2, G2, B2;
        int A, R, G, B;
        int pixelColor1;
        int pixelColor2;
        int height = src1.getHeight();
        int width = src1.getWidth();

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                pixelColor1 = src1.getPixel(x, y);
                A1 = Color.alpha(pixelColor1);
                R1 = Color.red(pixelColor1);
                G1 = Color.green(pixelColor1);
                B1 = Color.blue(pixelColor1);

                pixelColor2 = src2.getPixel(x, y);
                A2 = Color.alpha(pixelColor2);
                R2 = Color.red(pixelColor2);
                G2 = Color.green(pixelColor2);
                B2 = Color.blue(pixelColor2);

                A = (int) (A1 * alpha + A2 * (1 - alpha));
                R = (int) (R1 * alpha + R2 * (1 - alpha));
                G = (int) (G1 * alpha + G2 * (1 - alpha));
                B = (int) (B1 * alpha + B2 * (1 - alpha));

                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        return bmOut;
    }
}