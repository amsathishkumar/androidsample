package edu.umd.cs.cmsc436.Fragments.StaticLayout;

import android.app.Activity;
import android.os.Bundle;
import edu.umd.cs.cmsc436.Fragments.StaticLayout.TitlesFragment.ListSelectionListener;

public class DetailsActivity extends Activity implements ListSelectionListener {
	
	public static String [] TitleArray; 
	public static String [] QuoteArray; 
	private DetailsFragment mDetailsFragment;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TitleArray = getResources().getStringArray(R.array.Titles);
		QuoteArray = getResources().getStringArray(R.array.Quotes);
		setContentView(R.layout.main);
		mDetailsFragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details);
	}

	@Override
	public void onListSelection(int index) {
		if (null != mDetailsFragment && mDetailsFragment.getShownIndex() != index) {
			mDetailsFragment.showIndex(index);
		}
	}
}