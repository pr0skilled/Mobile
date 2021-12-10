package com.example.lab24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final Spinner[][] map = new Spinner[4][4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
        setListenersOnElements();
        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    private void setListenersOnElements(){
        for (Spinner[] line: map) {
            for (Spinner el : line) {
                el.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        checkWinner(adapterView.getSelectedItem().toString().charAt(0));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        }
    }
    private void checkWinner(char x){
        if (x!='X' && x!='O') return;

        boolean win=false;
        for (Spinner[] line: map)
            win = win || (countOfSame(line, x)>=3);

        for (int i = 0;!win && i < 4; i++) {
            Spinner[] arr = {map[0][i], map[1][i], map[2][i], map[3][i]};
            win = win || (countOfSame(arr, x)>=3);
        }

        win = win || (countOfSame(new Spinner[]{map[1][0], map[2][1], map[3][2]}, x)>=3)
                  || (countOfSame(new Spinner[]{map[0][0], map[1][1], map[2][2], map[3][3]}, x)>=3)
                  || (countOfSame(new Spinner[]{map[0][1], map[1][2], map[2][3]}, x)>=3)

                  || (countOfSame(new Spinner[]{map[0][2], map[1][1], map[2][0]}, x)>=3)
                  || (countOfSame(new Spinner[]{map[0][3], map[1][2], map[2][1], map[3][0]}, x)>=3)
                  || (countOfSame(new Spinner[]{map[1][3], map[2][2], map[3][1]}, x)>=3);

        if (win){
            makeWinner(x);
        }

    }
    private void makeWinner(char x){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Winner is "+x, Toast.LENGTH_SHORT);
        toast.show();
    }
    private int countOfSame(Spinner[] arr, char x){
        int r = 0;
        int max=0;
        for(Spinner el: arr){
            char t =el.getSelectedItem().toString().charAt(0);
            if (t==x) r++;
            else {
                max = Math.max(r, max);
                r=0;
            }
        }
        return max;
    }
    private void initElements(){
        map[0][0] = (Spinner) findViewById(R.id.el1);
        map[0][1] = (Spinner) findViewById(R.id.el2);
        map[0][2] = (Spinner) findViewById(R.id.el3);
        map[0][3] = (Spinner) findViewById(R.id.el4);

        map[1][0] = (Spinner) findViewById(R.id.el5);
        map[1][1] = (Spinner) findViewById(R.id.el6);
        map[1][2] = (Spinner) findViewById(R.id.el7);
        map[1][3] = (Spinner) findViewById(R.id.el8);

        map[2][0] = (Spinner) findViewById(R.id.el9);
        map[2][1] = (Spinner) findViewById(R.id.el10);
        map[2][2] = (Spinner) findViewById(R.id.el11);
        map[2][3] = (Spinner) findViewById(R.id.el12);

        map[3][0] = (Spinner) findViewById(R.id.el13);
        map[3][1] = (Spinner) findViewById(R.id.el14);
        map[3][2] = (Spinner) findViewById(R.id.el15);
        map[3][3] = (Spinner) findViewById(R.id.el16);
    }

    private void reset(){
        for (Spinner[] line: map) {
            for (Spinner el : line) {
                el.setSelection(0, true);
            }
        }
    }
}