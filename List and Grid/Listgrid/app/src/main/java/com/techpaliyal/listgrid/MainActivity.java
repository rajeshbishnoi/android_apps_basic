package com.techpaliyal.listgrid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listt;
    GridView gridview;
    Spinner spinner;
    ArrayList<String> arrNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listt=(ListView)findViewById(R.id.list);
        gridview=(GridView)findViewById(R.id.gridview);
        spinner=(Spinner)findViewById(R.id.spinner);
        arrNames=new ArrayList<>();
        arrNames.add("Yogesh");
        arrNames.add("Sohan");
        arrNames.add("Umesh");
        arrNames.add("Rajesh");
        arrNames.add("Madan");

        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.row,arrNames);
        listt.setAdapter(arrayAdapter);
        gridview.setAdapter(arrayAdapter);
        spinner.setAdapter(arrayAdapter);
        listt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Clicked on"+arrNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Clicked on "+adapterView.getAdapter().getItem(i), Toast.LENGTH_SHORT).show();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Selected"+arrNames.get(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
