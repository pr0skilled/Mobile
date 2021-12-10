package com.example.lab4_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

public class Fragment6 extends Fragment {

    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_6, container, false);
        ImageView imageOrig = view.findViewById(R.id.imageOrig);
        ImageView image1bit = view.findViewById(R.id.image1bit);
        ImageView image8bit = view.findViewById(R.id.image8bit);
        ImageView imageNoWM = view.findViewById(R.id.imageNoWM);
        Bitmap WM_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.watermark);
        Bitmap orig_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.container);
        int A, R, G, B;
        int pixelColor;
        int height = orig_bitmap.getHeight();
        int width = orig_bitmap.getWidth();
        int heightWM = WM_bitmap.getHeight();
        int widthWM = WM_bitmap.getWidth();
        Bitmap greyWM = Bitmap.createBitmap(WM_bitmap.getWidth(), WM_bitmap.getHeight(), WM_bitmap.getConfig());
        for (int y = 0; y < heightWM; y++) {
            for (int x = 0; x < widthWM; x++) {
                pixelColor = WM_bitmap.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);
                int avg = (int) (0.2126 * R + 0.7152 * G + 0.0722 * B);
                greyWM.setPixel(x, y, Color.argb(A, avg, avg, avg));
            }
        }

        Bitmap binaryWM = Bitmap.createBitmap(greyWM.getWidth(), greyWM.getHeight(), greyWM.getConfig());
        for (int y = 0; y < heightWM; y++) {
            for (int x = 0; x < widthWM; x++) {
                pixelColor = greyWM.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);
                if (R > 127) {
                    R = 0;
                    G = 0;
                    B = 0;
                } else {
                    R = 255;
                    G = 255;
                    B = 255;
                }
                binaryWM.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        Bitmap origWithWM8 = Bitmap.createBitmap(orig_bitmap.getWidth(), orig_bitmap.getHeight(), orig_bitmap.getConfig());
        Bitmap origWithWM1 = Bitmap.createBitmap(orig_bitmap.getWidth(), orig_bitmap.getHeight(), orig_bitmap.getConfig());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = orig_bitmap.getPixel(x, y);
                int WMpixelColor = binaryWM.getPixel(x % widthWM, y % heightWM);
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);
                int B8 = Color.blue(pixelColor);
                if (Color.blue(WMpixelColor) == 255) {
                    StringBuilder orig_bi1 = new StringBuilder(String.format("%8s", Integer.toBinaryString(B)).replace(' ', '0'));
                    StringBuilder orig_bi8 = new StringBuilder(String.format("%8s", Integer.toBinaryString(B)).replace(' ', '0'));
                    if (orig_bi1.charAt(7) == '0') {
                        orig_bi1.setCharAt(7, '1');
                    } else {
                        orig_bi1.setCharAt(7, '0');
                    }
                    if (orig_bi8.charAt(0) == '0') {
                        orig_bi8.setCharAt(0, '1');
                    } else {
                        orig_bi8.setCharAt(0, '0');
                    }
                    B = Integer.parseInt(orig_bi1.toString(), 2);
                    B8 = Integer.parseInt(orig_bi8.toString(), 2);
                }
                origWithWM1.setPixel(x, y, Color.argb(A, R, G, B));
                origWithWM8.setPixel(x, y, Color.argb(A, R, G, B8));
            }
        }

        image8bit.setImageBitmap(origWithWM8);
        image1bit.setImageBitmap(origWithWM1);

        Bitmap afterWMremoval = Bitmap.createBitmap(origWithWM8.getWidth(), origWithWM8.getHeight(), origWithWM8.getConfig());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = origWithWM8.getPixel(x, y);
                int WMpixelColor = binaryWM.getPixel(x % widthWM, y % heightWM);
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);
                if (Color.blue(WMpixelColor) == 255) {
                    StringBuilder bi = new StringBuilder(String.format("%8s", Integer.toBinaryString(B)).replace(' ', '0'));
                    if (bi.charAt(0) == '0') {
                        bi.setCharAt(0, '1');
                    } else {
                        bi.setCharAt(0, '0');
                    }
                    B = Integer.parseInt(bi.toString(), 2);
                }
                afterWMremoval.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        imageNoWM.setImageBitmap(afterWMremoval);

        return view;
    }

}