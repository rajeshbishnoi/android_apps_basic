package ysic47.currencyconvertor;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

res=(TextView)findViewById(R.id.textView2);


        new BckFetcher().execute("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
    }


    public class BckFetcher extends AsyncTask<String,Void,Gagan>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

           // pg.setVisibility(View.VISIBLE);
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

          //  pg.setVisibility(View.INVISIBLE);

double from= Double.parseDouble(gagen.getRateArr().get(0));
            double to= Double.parseDouble(gagen.getRateArr().get(22));

            res.setText("12 USD = "+(to/from*12)+" INR");


        }
    }
}
