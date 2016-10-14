package edu.umd.cs.cmsc436.Fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import edu.umd.cs.cmsc436.Fragments.TitlesFragment.ListSelectionListener;

public class DetailsActivity extends Activity implements ListSelectionListener {

	public static String[] TitleArray;
	public static String[] QuoteArray;
	private final TitlesFragment mTitlesFragment = new TitlesFragment();
	private final DetailsFragment mDetailsFragment = new DetailsFragment();

	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TitleArray = getResources().getStringArray(R.array.Titles);
		QuoteArray = getResources().getStringArray(R.array.Quotes);
		setContentView(R.layout.main);

		mFragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = mFragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.activityFrame, mTitlesFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onListSelection(int index) {
		if (!mDetailsFragment.isAdded()) {
			FragmentTransaction fragmentTransaction = mFragmentManager
					.beginTransaction();
			fragmentTransaction.add(R.id.activityFrame, mDetailsFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
			mFragmentManager.executePendingTransactions();
		}
		if (mDetailsFragment.getShownIndex() != index) {
			mDetailsFragment.showIndex(index);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.activity_menu_item:
			Toast.makeText(getApplicationContext(),
					"This action provided by the Activity", Toast.LENGTH_SHORT)
					.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}