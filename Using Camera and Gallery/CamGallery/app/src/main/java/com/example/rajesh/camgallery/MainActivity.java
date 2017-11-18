package com.example.rajesh.camgallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
ImageView img;
    RelativeLayout rel;
    File fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
rel=(RelativeLayout)findViewById(R.id.rel);
        img=(ImageView)findViewById(R.id.imageView);
    }

    public void Camera(View a)
    {
        Intent abc= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(abc,1234);
    }
    public void Gallery(View a)
    {
        Intent abc= new Intent(Intent.ACTION_PICK);

        abc.setType("image/*");

        startActivityForResult(abc,432);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent pqr) {
        super.onActivityResult(requestCode, resultCode, pqr);

        if(resultCode==RESULT_OK)
        {
            if(requestCode==1234)
            {
                Bitmap bmp= (Bitmap)pqr.getExtras().get("data");
                img.setImageBitmap(bmp);
            }
            else
            {
                try {
                    Uri path=pqr.getData();
                    InputStream strm=getContentResolver().openInputStream(path);
                    Bitmap bmp= BitmapFactory.decodeStream(strm);
                    img.setImageBitmap(bmp);
                        }
                catch (Exception e)
                {

                    }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.test,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.ref)
        {

        }
        else if(item.getItemId()==R.id.save)
        {
            //////////////////////////////////////
            rel.setDrawingCacheEnabled(true);
            Bitmap bmp=rel.getDrawingCache();
            /////////////////////////////////////

            //////////////////////////saving///////////////////////////////
        String name= Environment.getExternalStorageDirectory()+ File.separator+"Pictures/IDCARD"+System.currentTimeMillis()+".jpg";
            FileOutputStream fos;
              fl= new File(name);

            try{
                fos= new FileOutputStream(fl);
                bmp.compress(Bitmap.CompressFormat.JPEG,70,fos);
                fos.flush();
            }
            catch (Exception e)
            {

            }
        }
        else if(item.getItemId()==R.id.share)
        {
            if(fl==null){
                //toast
                return false;
            }
            Intent abc= new Intent(Intent.ACTION_SEND);
            abc.setType("image/*");
            Uri uri=Uri.fromFile(fl);

            abc.putExtra("android.intent.extra.STREAM",uri);
            startActivity(Intent.createChooser(abc,"Select App to Share Image"));

        }
        return super.onOptionsItemSelected(item);
    }
}
