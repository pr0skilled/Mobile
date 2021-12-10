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
import android.widget.ImageView;

public class Fragment2 extends Fragment {

    View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_2, container, false);
        ImageView image1 = view.findViewById(R.id.image2_1);
        ImageView image2 = view.findViewById(R.id.image2_2);
        ImageView image3 = view.findViewById(R.id.image2_3);
        image1.setImageBitmap(changeImage(BitmapFactory.decodeResource(getResources(), R.drawable.vivo), 1));
        image2.setImageBitmap(changeImage(BitmapFactory.decodeResource(getResources(), R.drawable.vivo), 2));
        image3.setImageBitmap(changeImage(BitmapFactory.decodeResource(getResources(), R.drawable.vivo), 3));
        return view;
    }

    private Bitmap changeImage(Bitmap src, int pos) {
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
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);
                if (pos == 1 && !(R > 155)) {
                    R += 100;
                }
                else if (pos == 2 && !(G > 155)) {
                    G += 100;
                }
                else if (pos == 3 && !(B > 155)){
                    B += 100;
                }
                else if (pos == 1) {
                    R = 255;
                }
                else if (pos == 2) {
                    G = 255;
                }
                else if (pos == 3) {
                    B = 255;
                }
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        return bmOut;
    }
}