package course.examples.ContentProviders.ContactsListWithInsDel;

import java.util.ArrayList;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ListActivity;
import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.widget.SimpleCursorAdapter;

public class ContactsListDisplayActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String columns[] = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.STARRED };

		if (getIntent().getAction().equals(
				ContactsListModificationActivity.INSERT)) {
			insertContact("Adam Porter");
			insertContact("Steve Ballmer");
			insertContact("Steve Jobs");
			insertContact("Larry Page");
		} else if (getIntent().getAction().equals(
				ContactsListModificationActivity.DELETE)) {
			// deleteAllContacts();
			deleteContact("Steve Ballmer");
		}
		
		String colsToDisplay[] = new String[] { ContactsContract.Contacts.DISPLAY_NAME };
		int[] colResIds = new int[] { R.id.name };

		Cursor c = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, columns, null, null,
				null);
		
		setListAdapter(new SimpleCursorAdapter(this, R.layout.list_layout,
				c, colsToDisplay, colResIds));

	}

	private void insertContact(String name) {
		AccountManager am = AccountManager.get(this);
		Account[] accList = am.getAccountsByType("com.google");
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

		int rawContactInsertIndex = ops.size();
		ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
				.withValue(RawContacts.ACCOUNT_TYPE, accList[0].type)
				.withValue(RawContacts.ACCOUNT_NAME, accList[0].name).build());

		ops.add(ContentProviderOperation
				.newInsert(Data.CONTENT_URI)
				.withValueBackReference(Data.RAW_CONTACT_ID,
						rawContactInsertIndex)
				.withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
				.withValue(StructuredName.DISPLAY_NAME, name).build());
		try {
			getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}
	}

	private void deleteContact(String name) {
		getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI,
				ContactsContract.Contacts.DISPLAY_NAME + "=?", new String[] {name});
	}
	
	private void deleteAllContacts() {
		getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI,
				null, null);
	}
}
