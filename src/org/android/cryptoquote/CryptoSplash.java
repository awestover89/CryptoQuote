package org.android.cryptoquote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CryptoSplash extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.v("MyActivity","Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button newgame = (Button) findViewById(R.id.newgame);
        Button hiscore = (Button) findViewById(R.id.hiscore);
        newgame.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		Intent startGame = new Intent(v.getContext(),CryptoGame.class);
				startActivityForResult(startGame,0);
        	}
        });
        hiscore.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent showHighScores = new Intent(v.getContext(),HighScore.class);
				startActivityForResult(showHighScores,0);
			}
		});
    }
}