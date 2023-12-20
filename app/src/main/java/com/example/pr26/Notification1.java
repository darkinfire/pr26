package com.example.pr26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class Notification1 extends AppCompatActivity {
    private SoundPool mSoundPool;
    private Resources resourcesLoader;
    private int sound;
    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification1);

        resourcesLoader = getResources();
        createSoundPool();

        sound = loadSound(R.raw.school_zvon);

        findViewById(R.id.schoolBtn).setOnClickListener(view ->playSound(sound));
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> goBack());
    }

    private void createSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder().
                setUsage(AudioAttributes.USAGE_GAME).
                setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();

        mSoundPool = new SoundPool.Builder().
                setAudioAttributes(attributes).build();
    }

    private int playSound(int sound) {
        Log.d("play","play");
        return mSoundPool.play(sound,1,1,1,0, 1); //(float)(0.1 + new Random().nextDouble()*4)
    }

    private int loadSound(int r_row_id) {
        AssetFileDescriptor afd;
        try {
            afd = resourcesLoader.openRawResourceFd(r_row_id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }
    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

