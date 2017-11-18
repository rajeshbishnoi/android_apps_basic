package wscube.com.firstapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsInRecycler extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<NewsModel> newsModelSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_in_recycler);
        recyclerView= (RecyclerView) findViewById(R.id.newsRecyclerView);
        newsModelSet=new ArrayList<NewsModel>();
        layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        new FetchData().execute();
    }
    public class FetchData extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            JsonHandler json=new JsonHandler();
            String jsonStr=json.makeServiceCall(Url.IMAGEURL,JsonHandler.GET);
            if(jsonStr!=null)
            {
                try {
                    JSONObject job=new JSONObject(jsonStr);
                    JSONArray jar=job.getJSONArray("msg");
                    for(int i=0;i<jar.length();i++)
                    {
                        JSONObject j=jar.getJSONObject(i);
                        NewsModel newsModel=new NewsModel();
                        newsModel.setTitle(j.getString("news_desc_title"));
                        newsModel.setDate(j.getString("news_desc_date"));
                        newsModel.setDescription(j.getString("news_desc_desc"));
                        newsModel.setUrl(Url.PRE_IMG_URL+j.getString("news_desc_image"));
                        newsModelSet.add(newsModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            NewsRecyclerAdapter recyclerAdapter=new NewsRecyclerAdapter(newsModelSet,getApplicationContext());
            recyclerView.setAdapter(recyclerAdapter);
            super.onPostExecute(aVoid);
        }
    }
}
