package com.example.moham.beatbox;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BeatBoxActivity extends SingleFragmentActivity {


    @Override
    public Fragment CreateFragment() {
        return BeatBoxFragment.newInstance();
    }
}
