package course.examples.AudioVideo.VideoPlay;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import course.examples.AudioVideo.Playback.R;

public class AudioVideoVideoPlayActivity extends Activity {
	MediaPlayer mp = null;
	VideoView videoView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

			videoView = (VideoView) findViewById(R.id.videoView1);
		Button videoButton = (Button) findViewById(R.id.button2);
		videoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				videoView.setMediaController(new MediaController(
						AudioVideoVideoPlayActivity.this));
				videoView.setVideoURI(Uri
						.parse("android.resource://course.examples.AudioVideo.Playback/raw/video_test"));
				videoView.start();
			}
		});

	}

	@Override
	protected void onPause() {
		if (videoView != null && videoView.isPlaying()) {
			videoView.stopPlayback();
			videoView = null;
			
		}
		super.onPause();
	}
}