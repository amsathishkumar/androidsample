package course.examples.HelloWorld.HelloWorldWithLogin;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginscreen);
		
		final EditText uname = (EditText) findViewById(R.id.editText1);
		final EditText passwd = (EditText) findViewById(R.id.editText2);
		final Button loginButton = (Button) findViewById(R.id.button1);
		
		loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (checkPassword(uname.getText(), passwd.getText())){
				Intent helloAndroidIntent = new Intent(LoginScreen.this, HelloAndroid.class);
				startActivity(helloAndroidIntent);
				} else {
					uname.setText("");
					passwd.setText("");
				}
			}
		});
}
	
	private boolean checkPassword (Editable uname, Editable passwd) {
		// pretend to extract text and check password
		return new Random().nextBoolean();
	}
}
