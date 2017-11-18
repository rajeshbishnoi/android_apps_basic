package com.example.rajesh.techpaliyal;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
WebView web;
    ProgressBar pg;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web=(WebView)findViewById(R.id.web1);
        pg=(ProgressBar)findViewById(R.id.progressBar);

        web.loadUrl(getIntent().getExtras().getString("blue"));

//      web.loadUrl("http://techpaliyal.blogspot.in/");
        web.getSettings().setJavaScriptEnabled(true);

        web.setWebViewClient(new WebViewClient()
                             {
                                 @Override
                                 public void onPageStarted(WebView view, String url, Bitmap favicon)
                                 {
                                     super.onPageStarted(view, url, favicon);

                                     pg.setVisibility(View.VISIBLE);
                                 }

                                 @Override
                                 public void onPageFinished(WebView view, String url) {
                                     super.onPageFinished(view, url);
                                     pg.setVisibility(View.INVISIBLE);
                                 }
                             }
        );
    }
    public void onBackPressed(){
      super.onBackPressed();
      if (web.canGoBack())web.goBack();
      else finish();
  }
}
