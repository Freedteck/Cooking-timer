package com.freed.app;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.View;
import android.os.CountDownTimer;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
	TextView timer;
	SeekBar seekBar;
	Button go;
	boolean buttonTapped = false;
	CountDownTimer countDownTimer;

	public void go(View view) {
		if (!buttonTapped) {
			buttonTapped = true;
			seekBar.setEnabled(false);
			go.setText("Stop");

			countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
				@Override
				public void onTick(long millisUntilFinished) {
					secondstime((int) millisUntilFinished / 1000);
				}

				@Override
				public void onFinish() {
					MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
					mPlayer.start();
					timerReset();
				}

			}.start();
		} else {
			timerReset();
		}

	}

	public void timerReset() {
		countDownTimer.cancel();
		seekBar.setEnabled(true);
		go.setText("go");
		timer.setText("00:30");
		seekBar.setProgress(30);
		buttonTapped = false;
	}

	public void secondstime(int progr) {

		int minutes = (int) progr / 60;
		int seconds = progr - minutes * 60;
		String second = Integer.toString(seconds);
		String minute = Integer.toString(minutes);
		if (seconds <= 9) {
			second = "0" + second;
		}
		if (minutes <= 9) {
			minute = "0" + minute;
		}

		timer.setText(minute + ":" + second);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekBar = findViewById(R.id.seekBar);
		timer = findViewById(R.id.timer);
		go = findViewById(R.id.go);
		seekBar.setMax(600);
		seekBar.setProgress(30);

		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				secondstime(arg1);
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

		});

	}

}
/*don't forget to subscribe my YouTube channel for more Tutorial and mod*/
/*
https://youtube.com/channel/UC_lCMHEhEOFYgJL6fg1ZzQA */
