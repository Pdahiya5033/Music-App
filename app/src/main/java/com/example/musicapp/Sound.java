package com.example.musicapp;

public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundId;
    public Sound(String assetPath){
        mAssetPath=assetPath;
        String[] components=assetPath.split("/");
        String filename=components[components.length-1];
        mName=filename.replace(".wav","");
    }
    public String getmAssetPath(){
        return mAssetPath;
    }
    public String getmName(){
        return mName;
    }
    public Integer getSoundId(){
        return mSoundId;
    }
    public void setSoundId(Integer s){
        mSoundId=s;
    }

}
