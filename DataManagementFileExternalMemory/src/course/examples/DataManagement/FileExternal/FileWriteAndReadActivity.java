package course.examples.DataManagement.FileExternal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import course.examples.Files.FileWriteAndRead.R;

public class FileWriteAndReadActivity extends Activity {
	private final String fileName = "myIcon.png";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File outFile = new File(
					getExternalFilesDir(Environment.DIRECTORY_PICTURES),
					fileName);
			try {
				BufferedOutputStream os = new BufferedOutputStream(
						new FileOutputStream(outFile));
				BufferedInputStream is = new BufferedInputStream(getResources()
						.openRawResource(R.drawable.icon));
				copy(is, os);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void copy(InputStream is, OutputStream os) {
		final byte[] buf = new byte[1024];
		int numBytes;
		try {
			while (-1 != (numBytes = is.read(buf))) {
				os.write(buf, 0, numBytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}