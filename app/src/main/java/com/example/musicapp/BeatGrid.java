package com.example.musicapp;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatGrid {
    private static final String TAG="BeatGrid";
    private static final String SOUNDS_FOLDER="sample_sound";
    private static final int MAX_SOUNDS=5;
    private SoundPool mSoundPool;
    private AssetManager mAssets;
    private List<Sound> mSounds=new ArrayList<>();
    public BeatGrid(Context context){
        mAssets=context.getAssets();
        mSoundPool=new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }
    public void play(Sound sound){
        Integer soundId=sound.getSoundId();
        if(soundId==null){
            return;
        }
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }
    private void loadSounds(){
        String[] soundName;
        try{
            soundName=mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG,"found "+soundName.length+" sounds");
        }catch(IOException ioe){
            Log.e(TAG,"could not list"+ioe);
            return;
        }
        for(String filename:soundName){
            try{
                String assetPath=SOUNDS_FOLDER+"/"+filename;
                Sound sound=new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            }catch(IOException ioe){
                Log.e(TAG,"could not load sound"+filename,ioe);
            }

        }
    }
    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd=mAssets.openFd(sound.getmAssetPath());
        int soundId=mSoundPool.load(afd,1);
        sound.setSoundId(soundId);
    }
    public List<Sound> getmSounds(){
        return mSounds;
    }
    public void release(){
        mSoundPool.release();
    }
}
