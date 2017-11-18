package com.thousandzero.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
String table;
    DBAdapter obj;
    String crtAns;
TextView question;
    Button o1,o2,o3,o4;
    ArrayList<Structure> arr= new ArrayList<Structure>();

    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        question=(TextView)findViewById(R.id.textView);
        o1=(Button)findViewById(R.id.button4);
        o2=(Button)findViewById(R.id.button5);
        o3=(Button)findViewById(R.id.button6);
        o4=(Button)findViewById(R.id.button7);

        table=getIntent().getExtras().getString("Blue");

        obj=DBAdapter.getDBAdapter(getApplicationContext());

        if(obj.checkDatabase()==false)obj.createDatabase(getApplicationContext());


        obj.openDatabase();

        arr=obj.getData(table);
    ShowRecord();
    }

    public void ShowRecord()
    {

        question.setText(arr.get(count).getQues());

        o1.setText(arr.get(count).getO1());
        o2.setText(arr.get(count).getO2());
        o3.setText(arr.get(count).getO3());
        o4.setText(arr.get(count).getO4());
        crtAns=arr.get(count).getAns();


    }
    public void Check(View a)
    {
        Button btn=(Button)a;
        if(btn.getText().toString().equals(crtAns))
        {
            Toast.makeText(getApplicationContext(),"Bingo!! You Nailed it",Toast.LENGTH_LONG).show();

            count++;
            ShowRecord();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Oops!! You Lost",Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
