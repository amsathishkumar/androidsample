package course.examples.FormWidgets.Spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerExample extends Activity {
 	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    Spinner spinner = (Spinner) findViewById(R.id.spinner);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.colors, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    
	    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	    	public void onItemSelected(AdapterView<?> parent,
	    	        View view, int pos, long id) {
	    	      Toast.makeText(parent.getContext(), "The color is " +
	    	          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
	    	    }

	    	    public void onNothingSelected(AdapterView<?> parent) {
	    	    }
		});
	}
}