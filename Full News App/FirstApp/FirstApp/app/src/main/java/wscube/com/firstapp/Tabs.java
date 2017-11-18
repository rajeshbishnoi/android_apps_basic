package wscube.com.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Tabs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
    }
    public void clk_tab_views(View v)
    {
        Intent i=new Intent(getApplicationContext(),TabView.class);
        startActivity(i);
    }
}
