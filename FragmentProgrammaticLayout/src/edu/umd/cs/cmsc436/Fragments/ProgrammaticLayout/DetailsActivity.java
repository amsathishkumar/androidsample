package edu.umd.cs.cmsc436.Fragments.ProgrammaticLayout;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import edu.umd.cs.cmsc436.Fragments.ProgrammaticLayout.TitlesFragment.ListSelectionListener;

public class DetailsActivity extends Activity implements ListSelectionListener {

	public static String[] TitleArray;
	public static String[] QuoteArray;
	private final DetailsFragment mDetailsFragment = new DetailsFragment();
	private final TitlesFragment mTitlesFragment = new TitlesFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TitleArray = getResources().getStringArray(R.array.Titles);
		QuoteArray = getResources().getStringArray(R.array.Quotes);
		setContentView(R.layout.main);

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.titleFrame, mTitlesFragment);
		fragmentTransaction.add(R.id.detailFrame, mDetailsFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onListSelection(int index) {
		if (null != mDetailsFragment
				&& mDetailsFragment.getShownIndex() != index) {
			mDetailsFragment.showIndex(index);
		}
	}
}