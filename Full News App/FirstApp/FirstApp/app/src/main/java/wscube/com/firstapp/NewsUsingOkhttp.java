package wscube.com.firstapp;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.protocol.ResponseServer;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class NewsUsingOkhttp extends AppCompatActivity {

    String name;
    String userName;
    EditText et_id;
    String et_id_str;
    String responseStr;
    public static final MediaType JSON =  MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_using_okhttp);
        et_id= (EditText) findViewById(R.id.et_id);

      new authentication().execute();
//        authenticationAndPostParameters();

     //   new okhttpHandlerPOST().execute();
        //new OkhttpHandlerGet().execute();

    }
    public void clk_okhttp_post(View v)
    {
        et_id_str=et_id.getText().toString();
        new OkhttpHandlerPost().execute();
    }

    public class okhttpHandlerPOST extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
           // String url="";
            String url = "http://webchoice-test.uk/techsquare/api/get_forum_topics.php?";
            HttpUrl.Builder builder=HttpUrl.parse(url).newBuilder();
            builder.addQueryParameter("category_id","1");
            builder.addQueryParameter("page_no","1");

/*
            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://wscubetech.org/eatonn_website/admineatonn/api/view_all_product.php").newBuilder();
            urlBuilder.addQueryParameter("user_id", "110");
*/

            url=builder.build().toString();

            Request request=new Request.Builder().url(url).build();

            OkHttpClient okhttpclient=new OkHttpClient.Builder()
                    .addInterceptor(new BasicAuthInterceptor("admin","Techadmin")).build();
            okhttpclient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("Failure:", String.valueOf(e));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful())
                    {
                        responseStr= Html.fromHtml(response.body().string()).toString();
                        Log.d("Success:", responseStr);

                    }
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



        }
    }

    public void displayMessage()
    {
        Toast.makeText(NewsUsingOkhttp.this, "Response Successful:"+responseStr, Toast.LENGTH_SHORT).show();
    }
    public class OkhttpHandlerGet extends AsyncTask<Void,Void,Void>
    {

        OkHttpClient okHttpClient=new OkHttpClient();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Request request=new Request.Builder().url(Url.OKHTTP_GET_URL).build();
            try {
                Response response=okHttpClient.newCall(request).execute();
                String strJson=response.body().string();
                Log.d("respose",strJson);
                JSONObject job=new JSONObject(strJson);
                name=job.getString("name");
            } catch (IOException e) {
                Log.d("error", String.valueOf(e));
                e.printStackTrace();
            } catch (JSONException e) {
                Log.d("error", String.valueOf(e));
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),"Name="+name,Toast.LENGTH_SHORT).show();
        }
    }
    public class OkhttpHandlerPost extends AsyncTask<Void,Void,Void>
    {

        OkHttpClient okHttpClient=new OkHttpClient();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("order_id",et_id_str).build();

           // String str="";
            //RequestBody requestBody=RequestBody.create(JSON,str);
            Request request=new Request.Builder().url(Url.OKHTTP_POST_URL).post(requestBody).build();
            try {
                Response response=okHttpClient.newCall(request).execute();
                String jsonStr=response.body().string();
                JSONObject job=new JSONObject(jsonStr);
                JSONObject msgJsonObject = job.getJSONObject("msg");
                userName=msgJsonObject.getString("user_name");
            } catch (IOException e) {
                Log.d("error:", String.valueOf(e));
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("error:", String.valueOf(e));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(NewsUsingOkhttp.this, "name="+userName, Toast.LENGTH_SHORT).show();
        }
    }
    public void handleResponse(final String response) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   // progressBar.setVisibility(View.GONE);
                    Toast.makeText(NewsUsingOkhttp.this, response, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {

        }
    }

    public class authentication extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String url = "http://webchoice-test.uk/techsquare/api/get_forum_topics.php?";
            //category_id=1&page_no=1
            HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
            urlBuilder.addQueryParameter("category_id", "1");
            urlBuilder.addQueryParameter("page_no", "1");

            url = urlBuilder.build().toString();


            Request request = new Request.Builder()
                    .url(url)
                    .build();

            //Username: admin
            //Password: TechAdmin
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new BasicAuthInterceptor("admin", "TechAdmin"))
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("Failure", "" + e);
                    // progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String res = Html.fromHtml(response.body().string()).toString();
                        Log.v("ResponsePostSuccess", res);
                       // handleResponse(res);
                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    public void authenticationAndPostParameters() {
        String url = "http://webchoice-test.uk/techsquare/api/get_forum_topics.php?";
        //category_id=1&page_no=1
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("category_id", "1");
        urlBuilder.addQueryParameter("page_no", "1");

        url = urlBuilder.build().toString();


        Request request = new Request.Builder()
                .url(url)
                .build();

        //Username: admin
        //Password: TechAdmin
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor("admin", "TechAdmin"))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("Failure", "" + e);
               // progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String res = Html.fromHtml(response.body().string()).toString();
                    Log.v("ResponsePostSuccess", res);
                    handleResponse(res);
                }
            }
        });
    }
}
