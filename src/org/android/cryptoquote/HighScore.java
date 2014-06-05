package org.android.cryptoquote;
import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.widget.Button;

public class HighScore extends Activity{
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.highscores);
		Button back = (Button) findViewById(R.id.mainmenu);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent goback = new Intent(v.getContext(),CryptoSplash.class);
				startActivityForResult(goback,0);
			}
		});
	}
}
