package wscube.com.firstapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;

public class Dashboard extends AppCompatActivity {

    SharedPre sp;
    ProgressBar pb;
    int id;
    String name,url;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        pb= (ProgressBar) findViewById(R.id.progressBar3);
        pb.setVisibility(View.INVISIBLE);
        sp=new SharedPre();
        sp.createSP(getApplicationContext());
       id= sp.readInt("user_id");
        Toast.makeText(Dashboard.this, "id="+id, Toast.LENGTH_SHORT).show();
        tv= (TextView) findViewById(R.id.textView4);
        url= Url.VIEW_PROFILE+"user_id="+id;
        new Loaddata().execute();

    }
    public void logout(View v)
    {

            sp.EDITint("user_id", 0);
        Intent i=new Intent(getApplicationContext(),Choose.class);
        startActivity(i);
        finish();

    }
public class Loaddata extends AsyncTask<Void,Void,Void>
{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        JsonHandler json = new JsonHandler();
        String jsonStr = json.makeServiceCall(url, JsonHandler.GET);
        if(jsonStr!=null)
        {
            try{
                JSONObject job=new JSONObject(jsonStr);
                JSONObject data=job.getJSONObject("details");
                name=data.getString("user_name");

            }
            catch(Exception e)
            {

            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pb.setVisibility(View.INVISIBLE);
        if(name==null)
        {
            Toast.makeText(Dashboard.this, "No name", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Dashboard.this, "name="+name, Toast.LENGTH_SHORT).show();
            tv.setText(name);
        }
    }
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
