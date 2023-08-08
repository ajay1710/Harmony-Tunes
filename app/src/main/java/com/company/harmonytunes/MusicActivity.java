package com.company.harmonytunes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicActivity extends AppCompatActivity {

   private Button buttonPlayPause,buttonPrevious,buttonNext;
   private TextView textViewFileNameMusic,textViewProgress,textViewTotalTime;
   private SeekBar seekBarVolume,seekBarMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        buttonPlayPause=findViewById(R.id.buttonNext);
        buttonPrevious=findViewById(R.id.buttonPrevious);
        buttonNext=findViewById(R.id.buttonNext);
        textViewFileNameMusic=findViewById(R.id.textViewFileNameMusic);
        textViewProgress=findViewById(R.id.textViewProgress);
        textViewTotalTime=findViewById(R.id.textViewTotalTime);
        seekBarVolume=findViewById(R.id.volumeSeekBar);
        seekBarMusic=findViewById(R.id.musicSeekBar);



    }
}