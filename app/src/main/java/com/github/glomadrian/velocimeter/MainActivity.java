package com.github.glomadrian.velocimeter;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.github.glomadrian.velocimeterlibrary.VelocimeterView;

public class MainActivity extends AppCompatActivity {

  private SeekBar seek;
  private VelocimeterView velocimeter;
  private VelocimeterView velocimeter2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    seek = (SeekBar) findViewById(R.id.seek);
    seek.setOnSeekBarChangeListener(new SeekListener());
    velocimeter = (VelocimeterView) findViewById(R.id.velocimeter);
    velocimeter2 = (VelocimeterView) findViewById(R.id.velocimeter2);
  }

  private class SeekListener implements SeekBar.OnSeekBarChangeListener {

    @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
      velocimeter.setValue(progress, false);
      velocimeter2.setValue(progress, false);
    }

    @Override public void onStartTrackingTouch(SeekBar seekBar) {
      //Empty
    }

    @Override public void onStopTrackingTouch(SeekBar seekBar) {
      //Empty
    }
  }
}
