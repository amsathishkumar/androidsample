package edu.umd.cs.cmsc436.Fragments.StaticLayout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mQuoteView = (TextView) getActivity().findViewById(R.id.quoteView);
		mQuoteArrLen = DetailsActivity.QuoteArray.length;
	}
}
