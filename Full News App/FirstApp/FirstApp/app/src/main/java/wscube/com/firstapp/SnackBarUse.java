package wscube.com.firstapp;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SnackBarUse extends AppCompatActivity {

    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_use);
        fab= (FloatingActionButton) findViewById(R.id.floatingActionButton);

        toolbar= (Toolbar) findViewById(R.id.toolbar_act_snack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coordinatorLayout= (CoordinatorLayout) findViewById(R.id.activity_snack_bar_use);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Snackbar snackbar=  Snackbar.make(view,"Clicked on snackbar", Snackbar.LENGTH_LONG).
                        setAction("click me", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar.make(view,"Clicked on SnackBar Button",Snackbar.LENGTH_SHORT).show();
                            }
                        });
                snackbar.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
               return super.onOptionsItemSelected(item);
        }

    }
}
