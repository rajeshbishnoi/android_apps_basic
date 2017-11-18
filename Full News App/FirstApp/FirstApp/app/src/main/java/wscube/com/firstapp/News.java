package wscube.com.firstapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class News extends AppCompatActivity {

    ListView lstNews;
    ProgressBar pb;
    ArrayList<Bag> bg;
    String result = "";
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        // b=new Bag();
        bg = new ArrayList<Bag>();
        lstNews = (ListView) findViewById(R.id.listView);
        pb = (ProgressBar) findViewById(R.id.progressBar4);
        pb.setVisibility(View.INVISIBLE);
        new getData().execute();
       lstNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               Intent intent=new Intent(getApplicationContext(),News_Details.class);
               intent.putExtra("title",bg.get(i).getTitle());
               intent.putExtra("description",bg.get(i).getDes());
               intent.putExtra("date",bg.get(i).getDate());
               bmp=bg.get(i).getBmpImgNews();

               ByteArrayOutputStream stream = new ByteArrayOutputStream();
               bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
               byte[] byteArray = stream.toByteArray();
               intent.putExtra("image",byteArray);
               startActivity(intent);
           }
       });
    }
   /* private InputStream OpenHttpConnection(String urlString)
            throws IOException

    {

        InputStream in = null;

        int response = -1;

        Url url = new Url(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }
        catch (Exception ex)
        {
            throw new IOException("Error connecting");
        }
        return in;
    }
    private Bitmap DownloadImage(String Url)
    {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(Url);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return bitmap;
    }*/
    public class getData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            JsonHandler json = new JsonHandler();
            String jsonStr = json.makeServiceCall(Url.IMAGEURL, JsonHandler.GET);
            if (jsonStr != null) {
                Log.d("response", jsonStr);

                try {
                    JSONObject job = new JSONObject(jsonStr);
                    JSONArray jar = job.getJSONArray("msg");
                    result = job.getString("result");
                    for (int i = 0; i < jar.length(); i++) {
                        JSONObject dt = jar.getJSONObject(i);
                        Bag b = new Bag();
                        b.setTitle(dt.getString("news_desc_title"));
                        b.setDate(dt.getString("news_desc_date"));
                        b.setDes(dt.getString("news_desc_desc"));
                        String actualImgUrl=Url.PRE_IMG_URL+dt.getString("news_desc_image");
                        InputStream in = new java.net.URL(actualImgUrl).openStream();
                        b.setBmpImgNews(BitmapFactory.decodeStream(in));
                        bg.add(b);
                        Log.d("actual image url", actualImgUrl);
                    }

                } catch (JSONException e) {
                    Log.d("jsonstr", "Error");
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    Log.d("URL","wrong url");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pb.setVisibility(View.INVISIBLE);
            Toast.makeText(News.this, "result=" + result, Toast.LENGTH_SHORT).show();
            NewsListAdapter ml = new NewsListAdapter(getApplicationContext(), bg);
            lstNews.setAdapter(ml);
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fetch_image, menu);
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
