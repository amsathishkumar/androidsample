package course.examples.AudioVideo.AudioRecording;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AudioRecordingActivity extends Activity {
	private static final String LOG_TAG = "AudioRecordTest";
	private static final String mFileName = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ "/audiorecordtest.3gp";
	private MediaRecorder mRecorder = null;
	private MediaPlayer mPlayer = null;

	private void onRecord(boolean isRecording) {
		if (!isRecording) {
			startRecording();
		} else {
			stopRecording();
		}
	}

	private void onPlay(boolean isPlaying) {
		if (!isPlaying) {
			startPlaying();
		} else {
			stopPlaying();
		}
	}

	private void startPlaying() {
		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource(mFileName);
			mPlayer.prepare();
			mPlayer.start();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}
	}

	private void stopPlaying() {
		mPlayer.release();
		mPlayer = null;
	}

	private void startRecording() {
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}
		mRecorder.start();
	}

	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

	public static class RecordButton extends Button {
		boolean mRecording = false;

		public RecordButton(Context ctx, AttributeSet attrs) {
			super(ctx, attrs);
		}

		public void setRecording(boolean setting) {
			mRecording = setting;
			if (setting) {
				setText("Stop recording");
			} else {
				setText("Start recording");
			}
		}

		public boolean isRecording() {
			return mRecording;
		}
	}

	public static class PlayButton extends Button {
		boolean mPlaying = false;

		public PlayButton(Context ctx, AttributeSet attrs) {
			super(ctx, attrs);
			System.out.println("PlayButton constructor called");
		}

		public void setPlaying(boolean setting) {
			mPlaying = setting;
			if (setting) {
				setText("Stop playing");
			} else {
				setText("Start playing");
			}
		}

		public boolean isPlaying() {
			return mPlaying;
		}
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		final RecordButton mRecordButton = (RecordButton) findViewById(R.id.record_button);
		mRecordButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean currentState = mRecordButton.isRecording();
				onRecord(currentState);
				mRecordButton.setRecording(!currentState);
			}
		});

		final PlayButton mPlayButton = (PlayButton) findViewById(R.id.play_button);
		mPlayButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean currentState = mPlayButton.isPlaying();
				onPlay(currentState);
				mPlayButton.setPlaying(!currentState);
			}
		});
	}

	@Override
	public void onPause() {
		super.onPause();
		if (mRecorder != null) {
			mRecorder.release();
			mRecorder = null;
		}

		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
}