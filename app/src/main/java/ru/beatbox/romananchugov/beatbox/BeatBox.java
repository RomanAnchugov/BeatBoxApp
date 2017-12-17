package ru.beatbox.romananchugov.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romananchugov on 15.12.2017.
 */

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static String SOUND_FOLDER = "sample_sounds";
    private List<Sound> mSounds;

    public static final int MAX_SOUNDS = 5;
    private SoundPool mSoundPool;

    private AssetManager mAssets;

    public BeatBox(Context context){
        mSounds = new ArrayList<>();
        mAssets = context.getAssets();

        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);


        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try{
            soundNames = mAssets.list(SOUND_FOLDER);
            Log.i(TAG, "Found" + soundNames.length + "sounds");
        } catch (IOException e) {
            Log.e(TAG, "Could not list assets", e);
            return;
        }

        for(String filename: soundNames){
            try {
                String assetPath = SOUND_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException e) {
                Log.e(TAG, "Couldnot load sound" + filename, e);
            }
        }
    }
//
    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getmAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setmSoundId(soundId);
    }
    public void play(Sound sound){
        Integer soundId = sound.getmSoundId();
        if(soundId == null){
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1,0,1.0f);
    }
    public void release(){
        mSoundPool.release();
    }

    public List<Sound> getSounds(){

        return mSounds;
    }

}
