package edu.umd.cs.cmsc436.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
		setHasOptionsMenu(true);
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
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.title_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		/*
		 * case android.R.id.home: // app icon in Action Bar clicked; go home
		 * Intent intent = new Intent(this, HomeActivity.class);
		 * intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 * startActivity(intent); return true;
		 */
		case R.id.title_menu_item:
			Toast.makeText(getActivity().getApplicationContext(),
					"This action provided by the TitlesFragment", Toast.LENGTH_SHORT)
					.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}