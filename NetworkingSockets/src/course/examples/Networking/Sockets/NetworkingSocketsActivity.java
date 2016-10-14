package course.examples.Networking.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class NetworkingSocketsActivity extends Activity {
	TextView mTextView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mTextView = (TextView) findViewById(R.id.textView1);
		// assuming webserver running on local machine with given IP address 
		//new HttpGet().execute("192.168.1.10");
		new HttpGet().execute("206.196.162.155");
		
	}

	private void onFinishGetRequest(String result) {
		mTextView.setText(result);
	}

	private class HttpGet extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			Socket socket = null;
			StringBuffer data = new StringBuffer();
			try {
				socket = new Socket(params[0], 80);
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
				pw.println("GET /json_example.txt");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String rawData;
				while ((rawData = br.readLine()) != null) {
					data.append(rawData);
				}
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (null != socket)
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			return data.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			onFinishGetRequest(result);
		}

	}
}