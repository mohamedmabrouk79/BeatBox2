package com.example.moham.beatbox;

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
 * Created by moham on 27/09/2016.
 */
public class BeatBox {
    public static final String TAG="beatbox";
    public static final  String SOUND_FOLDER="sample_sounds";
    private AssetManager asset;
    private List<Sound> mSounds=new ArrayList<>();
    public static final int MAX_SOUND=5;
    private SoundPool mSoundPool;
    public BeatBox(Context context){
        asset=context.getAssets();
        mSoundPool=new SoundPool(MAX_SOUND, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }


/*********** for get all  sounds Name for asset folder and put it as a path and add data it  to sound **********/
    public void loadSounds(){
        String[] soundsName;
        try {
           soundsName=asset.list(SOUND_FOLDER);
            Log.v(TAG,"sounds number is  :"+soundsName.length);


        for (String filename:soundsName){
          String assetPath=SOUND_FOLDER+"/"+filename;
            Sound sound=new Sound(assetPath);
            load(sound);
            mSounds.add(sound);
            Log.v(TAG, "path is  :" + assetPath);
        }
        }catch (Exception e){
   Log.v(TAG,"Error is  :"+e);
        }
    }

    /**************** for play music **********/
    public void play(Sound sound){
        Integer id=sound.getSoundId();
        if (id==null){
            return;
        }
        mSoundPool.play(id,1.0f,1.0f,1,0,1.0f);
    }

    public void load(Sound sound) throws IOException{
        AssetFileDescriptor afd=asset.openFd(sound.getAssetPath());
        int SoundId=mSoundPool.load(afd,1);
        sound.setSoundId(SoundId);
    }

    /*************** to load sound  ***********/


    public List<Sound> getSounds(){
        return mSounds;
    }

    /*********** release  sound pool ****************/
    public void release(){
        mSoundPool.release();
    }

}
