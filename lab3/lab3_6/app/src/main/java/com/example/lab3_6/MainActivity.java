package com.example.lab3_6;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button enter,prev,next;
    WebView webView;
    TextView url;
    List<String> urls;
    Integer page=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url= (TextView) findViewById(R.id.textView);
        enter=(Button)findViewById(R.id.button3);
        prev=(Button) findViewById(R.id.button);
        next=(Button) findViewById(R.id.button2);

        List<String> urls = new ArrayList<>();
        View.OnClickListener onEnterListener= new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                webView = (WebView) findViewById(R.id.webview);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setAllowContentAccess(true);
                webSettings.setAppCacheEnabled(true);
                webSettings.setDomStorageEnabled(true);
                webSettings.setUseWideViewPort(true);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(url.getText().toString());
                String str=url.getText().toString();
                System.out.println(str);
                urls.add(str);
                page=urls.size()-1;
                if(page>0) findViewById(R.id.button).setEnabled(true);
                findViewById(R.id.button2).setEnabled(false);

            }
        };
        enter.setOnClickListener(onEnterListener);
        View.OnClickListener onPrevListener= new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                url.setText(urls.get(page-1));
                page--;
                if(page<1) findViewById(R.id.button).setEnabled(false);
                findViewById(R.id.button2).setEnabled(true);
            }
        };
        prev.setOnClickListener(onPrevListener);
        View.OnClickListener onNextListener= new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                url.setText(urls.get(page+1));
                page++;
                if(page==urls.size()-1) findViewById(R.id.button2).setEnabled(false);
                findViewById(R.id.button).setEnabled(true);
            }
        };
        next.setOnClickListener(onNextListener);
    }
}
