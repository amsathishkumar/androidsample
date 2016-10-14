package course.examples.ContentProviders.ContactsListWithAdapter;

import course.examples.ContentProviders.ContactsList.R;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;

public class ContactsListExample extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String columns[] = new String[] {
				ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.STARRED };
		
		String colsToDisplay [] = new String[] {
				ContactsContract.Contacts.DISPLAY_NAME};
		int[] colResIds = new int[] { R.id.name };

		ContentResolver cr = getContentResolver();
		Cursor c = cr.query(ContactsContract.Contacts.CONTENT_URI, 
				columns, ContactsContract.Contacts.STARRED + "=0", null, null);
		setListAdapter(new SimpleCursorAdapter(this, R.layout.list_layout, c,
				colsToDisplay, colResIds));
	}
}