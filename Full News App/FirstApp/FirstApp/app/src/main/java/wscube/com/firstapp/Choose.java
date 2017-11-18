package wscube.com.firstapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {

    Button btnNewsRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        btnNewsRecycler= (Button) findViewById(R.id.button6);
        btnNewsRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),NewsInRecycler.class);
                startActivity(i);
            }
        });
    }
    public void clk_showNotification(View v)
    {
        NotificationCompat.Builder builder= (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_template_icon_bg)
                .setContentTitle("First Notification")
                .setContentText("This is the first notification,created on 5 jan 2017.");
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
    @SuppressLint("NewApi")
    protected void displaynotification()
    {
        int numMessage=0;
        NotificationCompat.Builder builder= (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.common_google_signin_btn_text_dark)
                .setContentTitle("Second Notification")
                .setTicker("New Message Alert")
                .setContentText("This is second notification also created on 5 jan 2017");
        builder.setNumber(++numMessage);
        NotificationCompat.InboxStyle inboxStyle= new NotificationCompat.InboxStyle();
        String events[]=new String[6];
        events[0]="This is first line";
        events[1]="This is second line";
        events[2]="This is third line";
        events[3]="This is fourth line";
        events[4]="This is fifth line";
        events[5]="This is sixth line";

        for(int i=0;i<events.length;i++)
        {
            inboxStyle.addLine(events[i]);
        }
        builder.setStyle(inboxStyle);
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);

        @SuppressLint({"NewApi", "LocalSuppress"})
        TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
    }
    public void clk_drop_down_spinner(View v)
    {
        Intent intent=new Intent(getApplicationContext(),DropDownSpinner.class);
        startActivity(intent);
    }
    public void clk_tabs(View v)
    {
        Intent i=new Intent(getApplicationContext(),ChooseTabs.class);
        startActivity(i);
    }
    public void  clk_snackbar(View v)
    {
        Intent i=new Intent(getApplicationContext(),SnackBarUse.class);
        startActivity(i);
    }
    public void clk_okhttp(View v)
    {
        Intent i=new Intent(getApplicationContext(),NewsUsingOkhttp.class);
        startActivity(i);
    }
public void login(View v)
{
Intent i=new Intent(getApplicationContext(),LogIn.class);
    startActivity(i);
}
    public void news(View v)
    {
        Intent i=new Intent(getApplicationContext(),News.class);
        startActivity(i);
    }
    public void register(View v)
    {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
    public void showCustom(View v)
    {
        final Dialog dialog=new Dialog(Choose.this);
        dialog.setContentView(R.layout.coustom_dialog);
        dialog.setTitle("Welcome");
        dialog.show();
        Button ok= (Button) dialog.findViewById(R.id.btnOk);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose, menu);
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
