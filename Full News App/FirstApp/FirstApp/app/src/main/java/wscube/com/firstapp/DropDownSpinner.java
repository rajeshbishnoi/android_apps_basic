package wscube.com.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DropDownSpinner extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner dropDownSpinner;
    ArrayList<String> arrCategory=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_down_spinner);
        dropDownSpinner= (Spinner) findViewById(R.id.spnCategory);
        arrCategory.add("Automobile");
        arrCategory.add("Business Service");
        arrCategory.add("Computers");
        arrCategory.add("Education");
        arrCategory.add("Personal");
        arrCategory.add("Travel");
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrCategory);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      String item=  arrCategory.get(i);
        Toast.makeText(this, "select item:"+item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
