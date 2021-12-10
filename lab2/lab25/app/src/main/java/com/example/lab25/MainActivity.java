package com.example.lab25;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    double perX;
    double perY;
    Point size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        perX = size.x/100.0;
        perY = size.y/100.0;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins((int)perX*2, 0,(int)perX*2, 0);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(0, 0,(int)perX*1, 0);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins((int)perX*1, 0,0, 0);

        findViewById(R.id.button5).setLayoutParams(lp);
        findViewById(R.id.button2).setLayoutParams(lp);

        findViewById(R.id.button7).setLayoutParams(lp1);
        findViewById(R.id.button8).setLayoutParams(lp2);

        //setLengths(findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3), 1,1,2,3,3);
    }

    private void setLengths(Button b1, Button b2, Button b3, int x, int y, int z, int kraya, int meg){
        int but = kraya*2+meg*2;
        double t1 = 1.0*(100-but)/(x+y+z);
        double t2 = 1.0*but/(kraya+but);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams((int)(t1*x*perX), LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins((int)perX*kraya, 0,(int)perX*2, 0);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams((int)(t1*y*perX), LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins((int)(perX*t2*meg), 0, (int)(perX*t2*meg), 0);

        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams((int)(t1*z*perX), LinearLayout.LayoutParams.WRAP_CONTENT);
        lp3.setMargins(0, 0,(int)(perX*t2*meg), 0);

        b1.setLayoutParams(lp1);
        b2.setLayoutParams(lp2);
        b3.setLayoutParams(lp3);
    }
}