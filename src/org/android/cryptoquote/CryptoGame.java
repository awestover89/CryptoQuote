package org.android.cryptoquote;

import java.io.InputStream;
import java.util.Random;

import utils.Quote;
import utils.QuoteReader;
import view.QuoteView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import event.QuoteEvent;
import event.QuoteEventListener;

public class CryptoGame extends Activity implements QuoteEventListener{

	private String quote;
	private InputStream in;
	private SharedPreferences pref;
	Quote q;
	private QuoteView qv;
	private int tries = 0;

	final int NUM_FILES = 4;
	final String TAG = "MyActivity";

	public void onCreate(Bundle bundle){
		pref = getSharedPreferences("Crypt", 0);
		in = null;
		Log.v(TAG,"IN CREATE");
		super.onCreate(bundle);
		setContentView(R.layout.grid);
		qv = (QuoteView) findViewById(R.id.quoteview);
		if(bundle!=null)
			q = bundle.getParcelable("Quote");
		else{
			quote = getQuoteFromFile();
			q = new Quote(quote);
			q.addQuoteEventListener(this);
			qv.setQuote(q);
			Log.v("MyActivity",quote+ " " +q.getCryptoQuote()+" "+q.getTries());
			String cryptQuote = pref.getString("Quote", q.getCryptoQuote());
			SharedPreferences.Editor editor = pref.edit();
			editor.putString("Quote", cryptQuote);
			editor.commit();
		}
		//q.addQuoteEventListener(this);
		//qv.setQuote(q);
	}

	private void popupKeyboard(){
		tries++;
		showDialog(1);
	}

	protected Dialog onCreateDialog(int id){
		Dialog dialog;
		switch(id){
		case 1:
			Button[] alpha = new Button[31];
			dialog = new Dialog(this);
			ViewGroup vg = new TableLayout(this);
			for(int i=0;i<4;i++){
				TableRow tr = new TableRow(this);
				tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				for(int j=0;j<7;j++){
					final int index = (i*7)+j;
					final char letter = (char)(65+index);
					if(Character.isLetter(letter)){
						alpha[index] = new Button(this);
						alpha[index].setText(letter+"");
						alpha[index].setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								q.swap(letter);
								dismissDialog(1);
							}
						});
						tr.addView(alpha[index],LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
					}
				}
				vg.addView(tr);
			}
			dialog.setContentView(vg);
			break;
		case 2:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(q.getCryptoQuote() + "\n Tries: " + q.getTries())
			.setCancelable(false)
			.setPositiveButton("New Puzzle", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					quote = getQuoteFromFile();
					q.restart(quote);
					qv.setQuote(q);
					Log.v("MyActivity", quote);
					Log.v("MyActivity",q.getCryptoQuote());
					Log.v("MyActivity",q.getTries()+" ");
					Log.v("MyActivity",qv.getQuote().getCryptoQuote());
					Log.v("MyActivity",qv.getQuote().getTries()+" ");
					dismissDialog(2);
				}
			});
			dialog = builder.create();
			break;
		default:
			dialog = new Dialog(this);
			break;
		}
		return dialog;			
	}

	public String getQuoteFromFile(){
		tries = 0;
		in = null;
		String temp;
		Random r = new Random();
		int fid  = r.nextInt(NUM_FILES);
		int id;
		switch(fid){
		case 0:
			id = R.raw.quotes;
			break;
		case 1:
			id = R.raw.quotes2;
			break;
		case 2: 
			id = R.raw.quotes3;
			break;
		case 3:
			id = R.raw.quotes4;
			break;
		default:
			id = R.raw.quotes5;
		}
		try{
			int line = r.nextInt(100)+1;
			in = getResources().openRawResource(id);
			QuoteReader qr = new QuoteReader(in);
			for(int i=1;i<line;i++)
				qr.readLine();
			temp = qr.readLine().toUpperCase();
			Log.v("MyActivity", fid + " " + line);
		}
		catch(Exception ex){temp="THERE WAS AN ERROR";}
		return temp;
	}

	public void onQuoteEvent(QuoteEvent event){
		if(event.getId() == QuoteEvent.LETTER_SELECTED)
			popupKeyboard();
		else if(event.getId() == QuoteEvent.FINISHED){
			removeDialog(2);
			showDialog(2);
		}
	}

	public void onSaveInstanceState(Bundle outState){
		q.removeQuoteEventListener(this);
		q.removeQuoteEventListener(qv);
		outState.putParcelable("Quote", q);
	}

	public void scrollUp(View v){
		qv.scrollUp();
	}

	public void scrollDown(View v){
		qv.scrollDown();
	}

	public void shrink(View v){
		qv.shrink();
	}

	public void grow(View v){
		qv.grow();
	}

	public void useHint(View v){
		q.useHint();
	}

	public boolean onCreateOptionsMenu(Menu menu){
		menu.add(0, 1, 0, "Restart");
		menu.add(0, 2, 0, "New Quote");
		menu.add(0, 3, 0, "Settings");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			q.restart(quote);
			qv.setQuote(q);
			return true;
		case 2:
			quote = getQuoteFromFile();
			q.restart(quote);
			qv.setQuote(q);
			return true;
		case 3:
			Intent i = new Intent(this,SettingsActivity.class);
			this.startActivityForResult(i, 100);
			return true;
		}
		return false;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 100){
			if(resultCode == 100){
				SharedPreferences pref = getSharedPreferences("Crypt", 0);
				boolean show = pref.getBoolean("show", true);
				boolean lock = pref.getBoolean("lock", true);
				int textColorVal = pref.getInt("textColor", 2);
				int correctColorVal = pref.getInt("correctColor", 3);
				int bgColorVal = pref.getInt("bgColor", 6);
				qv.changeSettings(show, lock, textColorVal, correctColorVal, bgColorVal);
			}
		}
	}
}
