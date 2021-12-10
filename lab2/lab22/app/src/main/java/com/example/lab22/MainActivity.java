package com.example.lab22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
//import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText number1;
    EditText number2;
    Spinner oper;
    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = (EditText) findViewById(R.id.number1);
        number2 = (EditText) findViewById(R.id.number2);
        oper = (Spinner) findViewById(R.id.spinner2);
        res = (TextView) findViewById(R.id.result);
        number1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        number1.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_ATOP);
                    }
                }
        );
        number2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        number2.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_ATOP);
                    }
                }
        );

        findViewById(R.id.buttonStart).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count();
                    }
                }
        );
    }

    private void count(){
        String num1 = number1.getText().toString();
        String num2 = number2.getText().toString();
        boolean f=false;
        if (num1.length()<1) {
            number1.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            f = true;
        }
        if (num2.length()<1) {
            number2.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            f = f || true;
        }
        if (f) return;

        float a = Float.parseFloat(num1);
        float b = Float.parseFloat(num2);
        float r;
        try {
            switch (oper.getSelectedItem().toString().charAt(0)) {
                case '+': {
                    r = a + b;
                    break;
                }
                case '-': {
                    r = a - b;
                    break;
                }
                case '*': {
                    r = a * b;
                    break;
                }
                case '/': {
                    if (b == 0) throw new Exception("Dividing by zero");
                    r = a / b;
                    break;
                }
                default: throw new Exception("Unknown operator");
            }
            res.setText(String.valueOf(r));
        } catch (Exception err) {
            System.out.println(err.toString());
            number2.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
        }
    }
}