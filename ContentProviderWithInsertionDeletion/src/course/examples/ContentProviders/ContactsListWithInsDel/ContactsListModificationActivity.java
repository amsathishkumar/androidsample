package course.examples.ContentProviders.ContactsListWithInsDel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ContactsListModificationActivity extends Activity {
	final static String INSERT = "ContactsListWithInsDel.action.INSERT";
	final static String DELETE = "ContactsListWithInsDel.action.DELETE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button insertButton = (Button) findViewById(R.id.button1);
		insertButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(INSERT);
				intent.setFlags(Intent.FLAG_DEBUG_LOG_RESOLUTION);
				startActivity(intent);
			}
		});

		Button deleteButton = (Button) findViewById(R.id.button2);
		deleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(DELETE));
			}
		});

	}

}
