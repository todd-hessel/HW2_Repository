package oit.CST407.hw2_state;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

// MAIN ACTIVITY DEFINIION
public class OpinionActivity extends Activity {
	
	private static final String KEY_CHANGE_TEXT = "CHANGE_TEXT";
	private TextView txtRandomNumber = null;
	
	// ON CREATE
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opinion);
		
		// get textview's id
		txtRandomNumber = (TextView)findViewById(R.id.txtRandom);
		
		// set textview's value to a random number
		txtRandomNumber.setText("Random Number: " + Math.random());
		
		// check if bundle is null ( is null on first load )
		if( savedInstanceState != null ){
			String changeText = savedInstanceState.getString(KEY_CHANGE_TEXT);
			txtRandomNumber.setText(changeText);
		}
	}
	
	// ON SAVE INSTANCE STATE
	@Override 
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		
		String randomNumber = txtRandomNumber.getText().toString();
		outState.putString(KEY_CHANGE_TEXT, randomNumber);
	}
	
	// ON RESTORE INSTANCE STATE
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		
		String randomNumber = savedInstanceState.getString(KEY_CHANGE_TEXT);
		txtRandomNumber.setText(randomNumber);
	}
	
	// ON CREATE OPTIONS MENU
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opinion, menu);
		return true;
	}
	
	// ON OPTIONS ITEM SELECTED
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
