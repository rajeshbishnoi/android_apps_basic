package com.example.rajesh.media;

import android.hardware.SensorEventListener;
import android.media.MediaPlayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
MediaPlayer mp;
    VideoView vc;
    RelativeLayout rel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rel=(RelativeLayout)findViewById(R.id.relativeLayout);
vc=(VideoView)findViewById(R.id.videoView);
        mp=MediaPlayer.create(getApplicationContext(),R.raw.abc);
        mp.prepareAsync();
        mp.setLooping(true);

String path="android.resource://"+getPackageName()+"/raw/android";

        Uri myuri=Uri.parse(path);
        vc.setVideoURI(myuri);

        MediaController med= new MediaController(this);
        med.setAnchorView(vc);
        vc.setMediaController(med);

    }

public void Hide(View a)
{
    rel.setVisibility(View.INVISIBLE);
}
    public void Play(View a)
    {
        rel.setVisibility(View.VISIBLE);
mp.start();
    }
    public void Pause(View a)
    {
mp.pause();
    }
    public void Stop(View a)
    {
        mp.seekTo(0);
        mp.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mp.pause();
    }
}

