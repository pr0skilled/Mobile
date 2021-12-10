package com.example.lab1v2;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {
    TextView screen;
    String prev="", temp="";
    Character oper=' ';
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("---------------start-----------------");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.screen);
        screen.setText(prev + ' ' + oper + "\n ");

        setListenersOnNumbers();
        setListenersOnOperators();
        setListenerOnClear();
        setListenerOnEquals();

    }

    private void setListenerOnEquals() {
        findViewById(R.id.buttonEq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualsClick(v);
            }
        });
    }

    private void setListenersOnOperators(){
        findViewById(R.id.buttonPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick(v);
            }
        });
        findViewById(R.id.buttonMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick(v);
            }
        });
        findViewById(R.id.buttonMultiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick(v);
            }
        });
        findViewById(R.id.buttonDiv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick(v);
            }
        });
    }
    private void setListenersOnNumbers(){
        findViewById(R.id.button0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
    }

    private float countResult() throws Exception {
        switch (oper){
            case '+':
                return Float.parseFloat(prev) + Float.parseFloat(temp);
            case '-':
                return Float.parseFloat(prev) - Float.parseFloat(temp);
            case '*':
                return Float.parseFloat(prev) * Float.parseFloat(temp);
            case '/':
                float t = Float.parseFloat(temp);
                if (t == 0) throw new Exception("Dividing by zero");
                else return Float.parseFloat(prev) / t;
        }
        return Float.parseFloat(temp);
    }
    private void setListenerOnClear() {

        findViewById(R.id.buttonC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClick(v);
            }
        });
    }

    public void onEqualsClick(View view){
        if (oper==' ' && temp=="") return;
        if (oper == ' ') {
            prev = "0";
            oper = '+';
        }
        try {
            if (temp!="") prev = Float.toString(countResult());
        }
        catch (Exception err) {
        }
        temp = prev;
        oper = ' ';
        prev = "";
        screen.setText(prev +' ' +oper+ '\n' + temp);
    }
    public void onClearClick(View view){
        if (temp.length()<1) return;

        temp = new StringBuffer(temp).delete(temp.length()-1, temp.length()).toString();
        screen.setText(prev +' ' +oper+ '\n' + temp);
    }
    public void onOperatorClick(View view) {
        if (oper==' ' && temp=="") return;

        Button but = (Button) findViewById(view.getId());
        if (oper == ' ') {
            prev = "0";
            oper = '+';
        }
        try {
            if (temp!="") prev = Float.toString(countResult());
        }
        catch (Exception err) {

        }
        oper = but.getText().charAt(0);
        temp = "";
        screen.setText(prev +' ' +oper+ '\n' + temp);
    }
    public void onNumberClick(View view) {
        if (temp.length()<8) {
            Button but = (Button) findViewById(view.getId());
            //if (temp.length()>1)
            if (temp=="" || temp == "0") temp = but.getText().toString();
                else temp += but.getText().toString();
            //else temp = but.getText().toString();
            screen.setText(prev +' ' +oper+ '\n' + temp);
        }
    }
}