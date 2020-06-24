package com.example.educationapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MyService extends Service {
    private MediaPlayer mMediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate()
    {
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        mMediaPlayer.start();
        mMediaPlayer.setLooping(false);
    }
}
