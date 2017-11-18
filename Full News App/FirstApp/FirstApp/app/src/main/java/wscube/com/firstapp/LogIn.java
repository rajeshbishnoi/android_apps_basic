package wscube.com.firstapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

public class LogIn extends AppCompatActivity {

    EditText username,pass;
    String strUsername,strPass,url="";
    int res,id;
    SharedPre sp;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        sp =new SharedPre();
        sp.createSP(getApplicationContext());
        pb= (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        username= (EditText) findViewById(R.id.username);
        pass= (EditText) findViewById(R.id.password);
        id=sp.readInt("user_id");
        if(id!=0)
        {
            Intent i=new Intent(getApplicationContext(),Dashboard.class);
            startActivity(i);
            finish();
        }
    }
public void log(View v)
{
    strUsername=username.getText().toString();
    if(strUsername==null || strUsername.trim().equals(""))
    {
        username.setError("Please enter username");
    }
    if(!strUsername.matches(Url.PATTERN))
    {
        username.setError("Invalid username");
    }
    strPass=pass.getText().toString();
    if(strPass==null || strPass.trim().equals(""))
    {
        pass.setError("please enter password");
    }
    url= Url.LOGIN+"user_email="+strUsername+"&user_password="+strPass;
    new SendData().execute();
}
    public class SendData extends AsyncTask<Void,Void,Void>
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
                try {
                    JSONObject job = new JSONObject(jsonStr);

                    res=job.getInt("result");
                   id= job.getInt("user_id");
                }catch(Exception e)
                {

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pb.setVisibility(View.INVISIBLE);
            if(res==1)
            {
                Toast.makeText(LogIn.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                sp.EDITint("user_id", id);
                Intent i=new Intent(getApplicationContext(),Dashboard.class);
                startActivity(i);
                finish();
            }
            else
            {
                Toast.makeText(LogIn.this, "Log In Details are Incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
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
