package edu.umd.cs.cmsc436.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsFragment extends Fragment {

	TextView mQuoteView = null;
	int mCurrIdx = -1;
	int mQuoteArrLen = 0;

	public int getShownIndex() {
		return mCurrIdx;
	}

	public void showIndex(int newIndex) {
		if (newIndex < 0 || newIndex >= mQuoteArrLen)
			return;
		mCurrIdx = newIndex;
		mQuoteView.setText(DetailsActivity.QuoteArray[mCurrIdx]);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.detail_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mQuoteView = (TextView) getActivity().findViewById(R.id.quoteView);
		mQuoteArrLen = DetailsActivity.QuoteArray.length;
		setHasOptionsMenu(true);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCurrIdx = -1;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.detail_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.detail_menu_item:
			Toast.makeText(getActivity().getApplicationContext(),
					"This action provided by the Details Fragment", Toast.LENGTH_SHORT)
					.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
