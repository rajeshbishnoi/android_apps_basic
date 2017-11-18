package wscube.com.firstapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.FileOutputStream;

public class News_Details extends AppCompatActivity {

    TextView txtTitle,txtDate,txtDes;
    ImageView imgNews;
    Intent intent;
    NewsModel newsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__details);
        txtTitle= (TextView) findViewById(R.id.txtTitle);
        txtDate= (TextView) findViewById(R.id.txtDate);
        txtDes= (TextView) findViewById(R.id.txtDetail);
        imgNews= (ImageView) findViewById(R.id.imgNews);
        intent=getIntent();
        newsModel= (NewsModel) intent.getExtras().getSerializable("newsModel");
        txtTitle.setText(newsModel.getTitle());
        txtDate.setText(newsModel.getDate());
        txtDes.setText(newsModel.getDescription());
        Picasso.with(getApplicationContext()).load(newsModel.getUrl()).into(imgNews);
       /* byte[] byteArray = intent.getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imgNews.setImageBitmap(bmp);
        txtTitle.setText(intent.getStringExtra("title"));
        txtDate.setText(intent.getStringExtra("date"));
        txtDes.setText(intent.getStringExtra("description"));*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news__details, menu);
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
