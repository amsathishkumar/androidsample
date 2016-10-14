package course.examples.Menus.MenuExample;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HelloAndroid extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("Hello, Android");
		setContentView(tv);
		registerForContextMenu(tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.top_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.help:
			Toast.makeText(getApplicationContext(), "you've been helped",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.more_help:
			Toast.makeText(getApplicationContext(), "you've been helped more",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.even_more_help:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.help_guide:
			Toast.makeText(getApplicationContext(), "ContextMenu Shown",
					Toast.LENGTH_SHORT).show();
			return true;
		default:
			return false;
		}
	}
}