package edu.umd.cs.cmsc436.Fragments.DynamicLayout;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import edu.umd.cs.cmsc436.Fragments.DynamicLayout.TitlesFragment.ListSelectionListener;

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
}