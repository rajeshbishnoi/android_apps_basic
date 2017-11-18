package wscube.com.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseTabs extends AppCompatActivity implements View.OnClickListener {

    Button btnSimple,btnScrollable,btnIconText,btnIcon,btnCustomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_tabs);
        btnSimple= (Button) findViewById(R.id.btn_simple_tab);
        btnScrollable= (Button) findViewById(R.id.btn_scrollable_tab);
        btnIconText= (Button) findViewById(R.id.btn_icon_and_text_tabs);
        btnIcon= (Button) findViewById(R.id.btn_only_icon_tabs);
        btnCustomView= (Button) findViewById(R.id.btn_custom_view);
        btnSimple.setOnClickListener(this);
        btnScrollable.setOnClickListener(this);
        btnIconText.setOnClickListener(this);
        btnIcon.setOnClickListener(this);
        btnCustomView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i=new Intent(getApplicationContext(),TabView.class);
        switch(view.getId())
        {

            case R.id.btn_simple_tab:
                i.putExtra("id",R.id.btn_simple_tab);
                break;
            case R.id.btn_scrollable_tab:
                i.putExtra("id",R.id.btn_scrollable_tab);
                break;
            case R.id.btn_icon_and_text_tabs:
                i.putExtra("id",R.id.btn_icon_and_text_tabs);
                break;
            case R.id.btn_only_icon_tabs:
                i.putExtra("id",R.id.btn_only_icon_tabs);
                break;
            case R.id.btn_custom_view:
                i.putExtra("id",R.id.btn_custom_view);
                break;
        }
        startActivity(i);
    }
}
