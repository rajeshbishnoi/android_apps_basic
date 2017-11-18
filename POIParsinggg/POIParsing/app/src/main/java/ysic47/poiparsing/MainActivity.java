package ysic47.poiparsing;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
EditText lat,longs,radius,type,name;
    ProgressBar pg;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lat=(EditText)findViewById(R.id.editText);
        longs=(EditText)findViewById(R.id.editText2);
        radius=(EditText)findViewById(R.id.editText3);
        type=(EditText)findViewById(R.id.editText4);
        name=(EditText)findViewById(R.id.editText5);

        pg=(ProgressBar)findViewById(R.id.progressBar);
        lst=(ListView)findViewById(R.id.listview1);

    }

    public void Search(View a)
    {
        new BckFetcher().execute("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location="+lat.getText().toString()+","+longs.getText().toString()+"&radius="+radius.getText().toString()+"&types="+type.getText().toString()+"&name="+name.getText().toString()+"&sensor=false&key=AIzaSyBPXwJ6XQDhCfQGX1QGJBsoy4z6a1rc0lw");
    }


    public class BckFetcher extends AsyncTask<String,Void,Gagan>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pg.setVisibility(View.VISIBLE);
        }

        @Override
        protected Gagan doInBackground(String... params) {

try {
    URL url = new URL(params[0]);

    SAXParserFactory spf = SAXParserFactory.newInstance();

    XMLReader xr = spf.newSAXParser().getXMLReader();

    xr.setContentHandler(new XMLHandler());

    xr.parse(new InputSource(url.openStream()));

    Gagan result = XMLHandler.getGetterSetter();

    return result;
}
catch (Exception e)
{

}
            return null;
        }

        @Override
        protected void onPostExecute(Gagan gagen) {
            super.onPostExecute(gagen);

            pg.setVisibility(View.INVISIBLE);

            lst.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_1,gagen.getNameArr()));

        }
    }
}
