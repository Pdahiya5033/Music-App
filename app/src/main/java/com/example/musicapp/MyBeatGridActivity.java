package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MyBeatGridActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return BeatGridFragment.newInstance();
    }
}