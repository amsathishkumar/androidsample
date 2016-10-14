package course.examples.Networking.AndroidHttpClientJSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ListActivity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class NetworkingAndroidHttpClientJSONActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new HttpGetTask()
				.execute("http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=demo");
	}

	private void onFinishGetRequest(List<String> result) {
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, result));
	}

	private class HttpGetTask extends AsyncTask<String, Void, List<String>> {

		@Override
		protected List<String> doInBackground(String... params) {
			AndroidHttpClient client = AndroidHttpClient.newInstance("");
			HttpGet request = new HttpGet(params[0]);
			JSONResponseHandler responseHandler = new JSONResponseHandler();
			try {
				return client.execute(request, responseHandler);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			onFinishGetRequest(result);
		}
	}

	private class JSONResponseHandler implements ResponseHandler<List<String>> {
		@Override
		public List<String> handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			List<String> result = new ArrayList<String>();
			String JSONResponse = new BasicResponseHandler()
					.handleResponse(response);
			try {
				JSONObject object = (JSONObject) new JSONTokener(JSONResponse)
						.nextValue();
				JSONArray earthquakes = object.getJSONArray("earthquakes");
				for (int i = 0; i < earthquakes.length(); i++) {
					JSONObject tmp = (JSONObject) earthquakes.get(i);
					result.add("mag:" + tmp.get("magnitude") + " lat:"
							+ tmp.getString("lat") + " lng:" + tmp.get("lng"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
}