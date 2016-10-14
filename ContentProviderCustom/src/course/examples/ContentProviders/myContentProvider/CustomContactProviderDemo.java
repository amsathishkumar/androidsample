package course.examples.ContentProviders.myContentProvider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class CustomContactProviderDemo extends ListActivity {
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ContentResolver cr = getContentResolver();
        
        ContentValues values = new ContentValues();
        
        values.put("data", "Record1");
        cr.insert(MyContentProvider.CONTENT_URI, values);
        
        values.clear();
        values.put("data", "Record2");
        cr.insert(MyContentProvider.CONTENT_URI, values);
        
        values.clear();
        values.put("data", "Record3");
        cr.insert(MyContentProvider.CONTENT_URI, values);
        
        cr.delete(Uri.parse(MyContentProvider.CONTENT_URI + "/1"), (String) null, (String[]) null );
        
        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);
        
		setListAdapter(new SimpleCursorAdapter(this, R.layout.list_layout, c,
				new String [] {"_id","data"}, new int[] {R.id.idString, R.id.data}));

    }
}