package view;

import utils.Quote;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import event.QuoteEvent;
import event.QuoteEventListener;

public class QuoteView extends View implements QuoteEventListener{

	private int cellSize;
	private int cellsAcross;
	private Paint cellTextPaint, cellTextCorrectPaint;
	private String quote;
	float textPaddingLeft, textPaddingTop;
	Quote q;
	private int startingRow;
	private boolean showCorrect, lockCorrect;
	private int textColor, correctColor, bgColor;

	public QuoteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		showCorrect = true;
		lockCorrect = true;
		cellTextPaint = new Paint();
		cellTextPaint.setAntiAlias(true);
		cellTextPaint.setColor(Color.GREEN);
		cellTextCorrectPaint = new Paint();
		cellTextCorrectPaint.setAntiAlias(true);
		cellTextCorrectPaint.setColor(Color.YELLOW);
		startingRow = 0;
	}

	public void setQuote(Quote q){
		startingRow = 0;
		this.q=q;
		q.addQuoteEventListener(this);
		quote = q.getCryptoQuote();
		invalidate();
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		int smaller = Math.min(width, height);

		cellSize = smaller / 9;
		cellsAcross = width / cellSize;
		cellTextPaint.setTextSize(cellSize * .75f);
		cellTextCorrectPaint.setTextSize(cellSize * .75f);
		
		textPaddingLeft = (int) ((cellSize - cellTextPaint.measureText("9")) / 2);
        textPaddingTop = (int) ((cellSize - cellTextPaint.getTextSize()) / 2);

		setMeasuredDimension(width, height);
	}

	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		for(int i=0;i<=Math.ceil(quote.length()/cellsAcross);i++){
			for(int j=0; j<cellsAcross;j++){
				if(quote.length() > (int)((i+startingRow)*cellsAcross)+j){
					if(q.isCorrect((int)((i+startingRow)*cellsAcross)+j) && showCorrect)
						canvas.drawText(quote.charAt((int)((i+startingRow)*cellsAcross)+j)+"", j*cellSize+textPaddingLeft, (i+1)*cellSize, cellTextCorrectPaint);
					else
						canvas.drawText(quote.charAt((int)((i+startingRow)*cellsAcross)+j)+"", j*cellSize+textPaddingLeft, (i+1)*cellSize, cellTextPaint);
				}
			}
		}
	}
	
	public boolean onTouchEvent(MotionEvent m){
		float x = m.getX();
		float y = m.getY();
		switch(m.getAction()) {
			case(MotionEvent.ACTION_UP):
				int row = (int) (x/cellSize);
				int col = (int) (y/cellSize) + startingRow;
				int index = (int)(col*cellsAcross+row);
				Log.v("MyActivity", x+" "+y+" "+index+" "+cellSize+" "+cellsAcross);
				if(index < quote.length()){
					if(Character.isLetter(quote.charAt(index)))
						q.select(index);
				}
		}
		return true;
	}
	
	public void onQuoteEvent(QuoteEvent event){
		if(event.getId() == QuoteEvent.LETTER_CHANGED){
			quote = q.getCryptoQuote();
			invalidate();
		}
	}
	
	public void scrollUp(){
		if(startingRow > 0){
			startingRow--;
			invalidate();
		}
	}

	public void scrollDown(){
		if(startingRow < (Math.ceil(quote.length()/cellsAcross) - (this.getHeight()/cellSize) + 2)){
			startingRow++;
			invalidate();
		}
	}
	
	public void shrink(){
		if(cellsAcross < 25){
			cellsAcross++;
			cellSize = this.getWidth() / cellsAcross;
			cellTextPaint.setTextSize(cellSize * .75f);
			cellTextCorrectPaint.setTextSize(cellSize * .75f);
			invalidate();
		}
	}
	
	public void grow(){
		if(cellsAcross > 5){
			cellsAcross--;
			cellSize = this.getWidth() / cellsAcross;
			cellTextPaint.setTextSize(cellSize * .75f);
			cellTextCorrectPaint.setTextSize(cellSize * .75f);
			invalidate();
		}
	}
	
	public Quote getQuote(){
		return q;
	}
	
	public void changeSettings(boolean show, boolean lock, int textColorVal, int correctColorVal, int bgColorVal){
		showCorrect = show;
		lockCorrect = lock;
		cellTextPaint.setColor(setColor(textColor, textColorVal));
		cellTextCorrectPaint.setColor(setColor(correctColor, correctColorVal));
		this.setBackgroundColor(setColor(bgColor, bgColorVal));
		invalidate();
	}
	
	private int setColor(int color, int index){
		switch(index){
		case 0:
			color = Color.RED;
			break;
		case 1:
			color = Color.BLUE;
			break;
		case 2:
			color = Color.GREEN;
			break;
		case 3:
			color = Color.YELLOW;
			break;
		case 4:
			color = Color.rgb(150,0,220);
			break;
		case 5:
			color = Color.rgb(255, 75, 0);
			break;
		case 6:
			color = Color.BLACK;
			break;
		case 7:
			color = Color.WHITE;
			break;
		}
		return color;
	}
}
