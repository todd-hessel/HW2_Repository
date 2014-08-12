package oit.CST407.hw_persistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class GenieActivity extends Activity {
	
	private static final String INTERNAL_STORAGE_FILENAME = "genie_file";
	
	private ToggleButton tglWishOne = null;
	private ToggleButton tglWishTwo = null;
	private ToggleButton tglWishThree = null;

	// ON CREATE
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_genie);
		
		// Grab wish id's
		tglWishOne = (ToggleButton)findViewById(R.id.tglWish1);
		tglWishTwo = (ToggleButton)findViewById(R.id.tglWish2);
		tglWishThree = (ToggleButton)findViewById(R.id.tglWish3);
		
		// if wish one is clicked - disable it
		tglWishOne.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tglWishOne.setEnabled(false);
			}
		});
		
		// if wish two is clicked - disable it
		tglWishTwo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tglWishTwo.setEnabled(false);
			}
		});
		
		// if wish three is clicked - disable it
		tglWishThree.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tglWishThree.setEnabled(false);
			}
		});
		
		try {
			FileInputStream fis = openFileInput(INTERNAL_STORAGE_FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			List<Wish> wishes = (List<Wish>) ois.readObject();
			
			Wish wish1 = wishes.get(0);
			Wish wish2 = wishes.get(1);
			Wish wish3 = wishes.get(2);
			
			tglWishOne.setEnabled(wish1.getIsUsed());
			tglWishTwo.setEnabled(wish2.getIsUsed());
			tglWishThree.setEnabled(wish3.getIsUsed());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ON SAVE INSTANCE STATE
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		
		Wish  wish1 = new Wish(tglWishOne.isEnabled());
		Wish  wish2 = new Wish(tglWishTwo.isEnabled());
		Wish  wish3 = new Wish(tglWishThree.isEnabled());
		
		List<Wish> wishes = new ArrayList<Wish>();
		
		wishes.add(wish1);
		wishes.add(wish2);
		wishes.add(wish3);
		
		try {
			FileOutputStream fos = openFileOutput(INTERNAL_STORAGE_FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(wishes);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ON RESTORE INSTANCE STATE
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		
		try {
			FileInputStream fis = openFileInput(INTERNAL_STORAGE_FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			List<Wish> wishes = (List<Wish>) ois.readObject();
			
			Wish wish1 = wishes.get(0);
			Wish wish2 = wishes.get(1);
			Wish wish3 = wishes.get(2);
			
			tglWishOne.setEnabled(wish1.getIsUsed());
			tglWishTwo.setEnabled(wish2.getIsUsed());
			tglWishThree.setEnabled(wish3.getIsUsed());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	// ON CREATE OPTIONS MENU
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.genie, menu);
		return true;
	}
	
	// ON CREATE OPTIONS ITEM SELECTED
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
