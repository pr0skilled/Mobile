package com.example.lab27;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.res.Resources;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        double perY = size.y / 100.0;
        double perX = size.x / 100.0;

        Button bt = (Button) findViewById(R.id.button);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams((int)(perY*20*1.5), (int)(perY*20));
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        params.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        params.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        bt.setLayoutParams(params);
        //bt.setBackgroundResource(R.drawable.ugli);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.light_green));
                findViewById(R.id.button).setBackgroundColor(getResources().getColor(R.color.light_green));
          //      bt.setBackgroundResource(R.drawable.ugli);
            }
        });
    }
}