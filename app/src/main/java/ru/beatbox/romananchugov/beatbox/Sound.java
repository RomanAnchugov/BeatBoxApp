package ru.beatbox.romananchugov.beatbox;

/**
 * Created by romananchugov on 15.12.2017.
 */

public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundId;



    public Sound(String assetPath){
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String fileName = components[components.length - 1];
        mName = fileName.replace(".wav", "");
    }

    public String getmAssetPath() {
        return mAssetPath;
    }

    public String getmName() {
        return mName;
    }

    public Integer getmSoundId() {
        return mSoundId;
    }

    public void setmSoundId(Integer mSoundId) {
        this.mSoundId = mSoundId;
    }
}
