package course.examples.AudioVideo.AudioManager;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AudioVideoAudioManagerActivity extends Activity {
	private float volume = 0;
	private SoundPool soundPool;
	private int soundId;
	private AudioManager audioManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		final TextView tv = (TextView) findViewById(R.id.textView1);
		volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		tv.setText(String.valueOf(volume));

		final Button upButton = (Button) findViewById(R.id.button2);
		upButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				volume += 1;
				audioManager.playSoundEffect(AudioManager.FX_KEY_CLICK);
				tv.setText(String.valueOf(volume));
			}
		});

		final Button downButton = (Button) findViewById(R.id.button1);
		downButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				volume -= 1;
				audioManager.playSoundEffect(AudioManager.FX_KEY_CLICK);
				tv.setText(String.valueOf(volume));
			}
		});

		final Button playButton = (Button) findViewById(R.id.button3);
		playButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
				soundPool.play(soundId, volume, volume, 1, 0, 1.0f);
			}
		});

		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				playButton.setEnabled(true);
			}
		});
		soundId = soundPool.load(this, R.raw.slow_whoop_bubble_pop1, 1);
	}

	@Override
	protected void onResume() {
		super.onResume();
		audioManager.setSpeakerphoneOn(true);
		audioManager.loadSoundEffects();
	}

	@Override
	protected void onPause() {
		if (null != soundPool) {
			soundPool.unload(soundId);
			soundPool.release();
			soundPool = null;
		}
		audioManager.setSpeakerphoneOn(false);
		audioManager.unloadSoundEffects();
		super.onPause();
	}

}