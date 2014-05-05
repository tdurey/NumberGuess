package edu.harding.numberguess;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String TAG;
	public static final String LARGEST_NUMBER_PREFERENCE_KEY = "largest_number";
	
	private String maxNum;
	private String number;
	private int random;
	private SharedPreferences prefs;
	private EditText mEditView;
	private TextView mTextView;
	private int max = 10;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mEditView = (EditText) findViewById(R.id.editText1);
		mTextView = (TextView) findViewById(R.id.textView1);
		
		// Restore the preference
		prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		maxNum = prefs.getString(LARGEST_NUMBER_PREFERENCE_KEY, maxNum);

		Log.d(TAG, "maxNum: " + maxNum);
		
		if(maxNum != null) {
			max = Integer.parseInt(maxNum);
			mTextView.setText("Guess a number between 1 and " + max);
		}
			
		// Random number generator
		random = (int)Math.ceil(Math.random()*max);
		Log.d(TAG, "Random: " + random);
		
		// Set random number in editview
//		mEditView.setText(String.valueOf(random));
		
		// Toast which says the random number
//		Toast.makeText(getApplicationContext(), String.valueOf(random) + " was chosen", 
//				   Toast.LENGTH_LONG).show();
	
		
		// Button id, and onClick code
		Button button= (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {

				// What is inside of editview
				number = mEditView.getText().toString();
				
		    	//check if field is blank
		    	if(number.equals("")) {
					mTextView.setText("Guess a number between 1 and " + max);
		    		return;
		    	}
		    	else 
		    		SubmitButton();
		    }

		    // Code when submit is pressed
			private void SubmitButton() {
				if(Integer.parseInt(mEditView.getText().toString()) < random) {
					mTextView.setText("Too low. Guess again.");					
				}
				else if(Integer.parseInt(mEditView.getText().toString()) > random) {
					mTextView.setText("Too high. Guess again.");				
					
				}
				else {
					mTextView.setText("Correct.  Guess a new number between 1 and 10.");
					
					// Random number generator
					final int random = (int)Math.ceil(Math.random()*10);
					Log.d(TAG, "New Random: " + random);
					
					// Toast which says the random number
//					Toast.makeText(getApplicationContext(), String.valueOf(random) + " was chosen", 
//							   Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	        	
	        	Intent myIntent = new Intent(getApplicationContext(), Settings.class);
	        	startActivity(myIntent);
	            
	        	return true;

	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
