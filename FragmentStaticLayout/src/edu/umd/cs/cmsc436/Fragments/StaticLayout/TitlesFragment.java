package edu.umd.cs.cmsc436.Fragments.StaticLayout;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitlesFragment extends ListFragment {
	ListSelectionListener mListener = null;
	int mCurrIdx = 0;

	public interface ListSelectionListener {
		public void onListSelection(int index);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (ListSelectionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnArticleSelectedListener");
		}
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

		setListAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.list_item, DetailsActivity.TitleArray));

		if (savedState != null) {
			mCurrIdx = savedState.getInt("currIdx", 0);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("currIdx", mCurrIdx);
	}

	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
		mCurrIdx = pos;
		getListView().setItemChecked(pos, true);
		mListener.onListSelection(pos);
	}
}