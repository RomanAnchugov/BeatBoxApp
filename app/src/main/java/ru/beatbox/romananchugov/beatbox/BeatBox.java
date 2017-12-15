package ru.beatbox.romananchugov.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
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

    private AssetManager mAssets;

    public BeatBox(Context context){
        mSounds = new ArrayList<>();
        mAssets = context.getAssets();
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
            String assetPath = SOUND_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            mSounds.add(sound);
        }
    }

    public List<Sound> getSounds(){
        Log.i("Works", mSounds.size() + "");
        return mSounds;
    }

}
