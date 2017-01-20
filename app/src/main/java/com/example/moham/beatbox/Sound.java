package com.example.moham.beatbox;

/**
 * Created by moham on 27/09/2016.
 */
public class Sound {

    private String mAssetPath;
    private String mName;
    private Integer mSoundId;

    public Sound(String Path){
        mAssetPath=Path;

            String[] names=mAssetPath.split("/");
            String fileName=names[names.length-1];
            mName=fileName.replace(".wav","");
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer mSoundId) {
        this.mSoundId = mSoundId;
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }
}
