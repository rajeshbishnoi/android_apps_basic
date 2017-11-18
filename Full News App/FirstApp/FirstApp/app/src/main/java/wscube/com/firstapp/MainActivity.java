package wscube.com.firstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText name, email, pass;
    Button submit;
    String strName, strEmail, strPass;
    SharedPre sp;
    Toolbar toolbar;
    ProgressBar pb;
    String url = "";
    int id;
    int res = 0;
    FloatingActionButton fab;
    CoordinatorLayout coordinatorLayout;
    TextInputLayout inputLayout_name, inputLayout_email, inputLayout_password;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        pb = (ProgressBar) findViewById(R.id.progressBar2);
        pb.setVisibility(View.INVISIBLE);
        sp = new SharedPre();
        sp.createSP(getApplicationContext());
        inputLayout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayout_password = (TextInputLayout) findViewById(R.id.input_layout_password);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        submit = (Button) findViewById(R.id.submit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Floating Action Button Just Clicked", Toast.LENGTH_SHORT).show();
                //Snackbar snackbar = new Snackbar.make(coordinatorLayout, "Floating Action Button", Snackbar.LENGTH_LONG);
            }
        });
        id = sp.readInt("user_id");
        if (id != 0) {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
            finish();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void signUp(View v) {
        strName = name.getText().toString();
        if (strName == null || strName.trim().equals("")) {
            inputLayout_name.setError("Please enter your name!");
            name.requestFocus();
            return;
        }
        strEmail = email.getText().toString();
        if (strEmail.trim().equals("") || strEmail==null) {
            inputLayout_email.setError("Please enter your email!");
            email.requestFocus();
            return;
        }
        if (!strEmail.matches(Url.PATTERN)) {
            inputLayout_email.setError("Invalid email address");
            email.requestFocus();
            return;
        }
        strPass = pass.getText().toString();
        if (strPass == null || strPass.trim().equals("")) {
            inputLayout_password.setError("Please enter your password!");
            pass.requestFocus();
            return;
        }
        if (!(strPass.length() >= 8)) {
            inputLayout_password.setError("Password is too short!");
            pass.requestFocus();
            return;
        }
        url = Url.REGISTRATION + "user_name=" + strName + "&user_email=" + strEmail + "&user_password=" + strPass;
        new parseData().execute();

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    class parseData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            JsonHandler json = new JsonHandler();
            String jsonStr = json.makeServiceCall(url, JsonHandler.GET);
            if (jsonStr != null) {
                try {
                    JSONObject job = new JSONObject(jsonStr);
                    res = job.getInt("result");
                    id = job.getInt("user_id");
                } catch (Exception e) {
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pb.setVisibility(View.INVISIBLE);
            if (res == 1) {

                Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                sp.EDITint("user_id", id);
                Intent i = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(i);
                finish();

            } else {

                Toast.makeText(MainActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
            }


        }


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
