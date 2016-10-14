package course.examples.Networking.AndroidHttpClientXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.app.ListActivity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class NetworkingAndroidHttpClientXMLActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new HttpGetTask()
				.execute("http://api.geonames.org/earthquakes?north=44.1&south=-9.9&east=-22.4&west=55.2&username=demo");
	}

	private void onFinishGetRequest(List<String> result) {
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				result));
	}

	private class HttpGetTask extends AsyncTask<String, Void, List<String>> {

		@Override
		protected List<String> doInBackground(String... params) {
			AndroidHttpClient client = AndroidHttpClient.newInstance("");
			HttpGet request = new HttpGet(params[0]);
			XMLResponseHandler responseHandler = new XMLResponseHandler();
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

	private class XMLResponseHandler implements ResponseHandler<List<String>> {
		@Override
		public List<String> handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			try {
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader xr = sp.getXMLReader();
				XMLContentHandler handler = new XMLContentHandler();
				xr.setContentHandler(handler);
				xr.parse(new InputSource(response.getEntity().getContent()));
				return handler.getData();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	private class XMLContentHandler extends DefaultHandler {
		String lat = null, lng = null, mag = null;
		boolean parsingLat = false, parsingLng = false, parsingMag = false;
		List<String> results = new ArrayList<String>();

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			if (localName.equals("lat")) {
				parsingLat = true;
			} else if (localName.equals("lng")) {
				parsingLng = true;
			} else if (localName.equals("magnitude")) {
				parsingMag = true;
			}
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if (parsingLat) {
				lat = new String(ch, start, length).trim();
			} else if (parsingLng) {
				lng = new String(ch, start, length).trim();
			} else if (parsingMag) {
				mag = new String(ch, start, length).trim();
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) {
			if (localName.equals("lat")) {
				parsingLat = false;
			} else if (localName.equals("lng")) {
				parsingLng = false;
			} else if (localName.equals("magnitude")) {
				parsingMag = false;
			} else if (localName.equals("earthquake")) {
				results.add("lat:" + lat + " lng: " + lng + " mag:" + mag);
				lat = null;
				lng = null;
				mag = null;
			}
		}

		public List<String> getData() {
			return results;
		}
	}

}