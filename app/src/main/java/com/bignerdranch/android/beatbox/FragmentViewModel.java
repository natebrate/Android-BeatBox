package com.bignerdranch.android.beatbox;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.icu.text.MessageFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.SeekBar;

public class FragmentViewModel extends BaseObservable {
    public ObservableField<String> mPlaybackSpeedRateLabel = new ObservableField<>();
    public ObservableInt mPlaybackSpeedRate = new ObservableInt();
    private BeatBox mBeatBox;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public FragmentViewModel(BeatBox beatBox) {
        if (beatBox != null) {
            mBeatBox = beatBox;
            mPlaybackSpeedRateLabel.set(MessageFormat.format(
                    mBeatBox.getPlaybackSpeedLabel(),
                    (int) (mBeatBox.getPlaybackSpeedRate() * 100)));
            mPlaybackSpeedRate.set((int) (mBeatBox.getPlaybackSpeedRate() * 100 - 20));
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onPlaybackSpeedRateChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mPlaybackSpeedRateLabel.set(MessageFormat.format(mBeatBox.getPlaybackSpeedLabel(), progress + 20));
        mPlaybackSpeedRate.set(progress);
        mBeatBox.setPlaybackSpeedRate((progress + 20.0f) / 100);
    }
}