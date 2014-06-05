package org.android.cryptoquote;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class SettingsActivity extends Activity{
	
	Spinner s1,s2,s3;
	CheckBox show, lock;
	
	public void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.settings);
		
		s1 = (Spinner) findViewById(R.id.textColor);
	    s2 = (Spinner) findViewById(R.id.correctTextColor);
	    s3 = (Spinner) findViewById(R.id.bgColor);
	    ArrayAdapter adapter = ArrayAdapter.createFromResource(
	            this, R.array.colors, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    s1.setAdapter(adapter);
	    s2.setAdapter(adapter);
	    s3.setAdapter(adapter);
	    setDefaults();
	}
	
	public void setDefaults(){
		SharedPreferences pref = getSharedPreferences("Crypt", 0);
		boolean state = pref.getBoolean("show", true);
		show = (CheckBox) findViewById(R.id.showCorrectCheck);
		show.setChecked(state);
		state = pref.getBoolean("lock", true);
		lock = (CheckBox) findViewById(R.id.lockCorrectCheck);
		lock.setChecked(state);
		int position = pref.getInt("textColor", 2);
		s1.setSelection(position);
		position = pref.getInt("correctColor", 3);
		s2.setSelection(position);
		position = pref.getInt("bgColor", 6);
		s3.setSelection(position);
	}
	
	public void submit(View v){
		SharedPreferences pref = getSharedPreferences("Crypt", 0);
		SharedPreferences.Editor edit = pref.edit();
		edit.putBoolean("show", show.isChecked());
		edit.putBoolean("lock", lock.isChecked());
		edit.putInt("textColor", s1.getSelectedItemPosition());
		edit.putInt("correctColor", s2.getSelectedItemPosition());
		edit.putInt("bgColor", s3.getSelectedItemPosition());
		edit.commit();
		setResult(100);
		finish();
	}

}
