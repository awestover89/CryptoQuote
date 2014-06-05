package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.os.Parcel;
import android.os.Parcelable;
import event.QuoteEvent;
import event.QuoteEventListener;

public class Quote implements Parcelable {
	
	private String correct, encrypted;
	private List<QuoteEventListener> _listeners = new ArrayList<QuoteEventListener>();
	private char selected;
	private int numTries, numHints;
	
	public Quote(String correct){
		this.correct = correct;
		encrypted = encrypt();
		numTries = 0;
		numHints = 103;
	}
	
	public Quote(Parcel in){
		correct = in.readString();
		encrypted = in.readString();
		selected = in.readString().charAt(0);
		numTries = in.readInt();
	}
	
	public synchronized void addQuoteEventListener(QuoteEventListener listener)  {
		_listeners.add(listener);
	}
	  
	public synchronized void removeQuoteEventListener(QuoteEventListener listener)   {
		_listeners.remove(listener);
	}
	
	public String getCryptoQuote(){
		return encrypted;
	}
	
	private char[] shuffleAlpha(){
		char[] alphabet = new char[26];
		for(int i=0;i<26;i++)
			alphabet[i] = (char)(65+i);
		char temp = ' ';
		Random rng = new Random();
		for(int i=0;i<1000;i++){
			int r1 = rng.nextInt(26);
			int r2 = rng.nextInt(26);
			temp = alphabet[r1];
			alphabet[r1] = alphabet[r2];
			alphabet[r2] = temp;
		}
		return alphabet;
	}
	
	private String encrypt(){
		String s2 = "";
		char[] shuffledAlpha = shuffleAlpha();
		for(int i=0;i<correct.length();i++){
			if(Character.isLetter(correct.charAt(i)))
				s2 = s2+shuffledAlpha[(int)correct.charAt(i)-65];
			else
				s2 = s2+correct.charAt(i);
			}
		return s2;
	}
	
	private void fireEvent(int id){
		QuoteEvent event = new QuoteEvent(this);
		event.setId(id);
		Iterator<QuoteEventListener> i = _listeners.iterator();
		while(i.hasNext())  {
			i.next().onQuoteEvent(event);
		}
	}
	
	public boolean isCorrect(int i){
		return correct.charAt(i) == encrypted.charAt(i);
	}
	
	public void select(int i){
		selected = encrypted.charAt(i);
		fireEvent(QuoteEvent.LETTER_SELECTED);
	}
	
	public void swap(char c){
		numTries++;
		for(int i=0;i<encrypted.length();i++){
			if(encrypted.charAt(i) == selected)
				encrypted = encrypted.substring(0, i) + c + encrypted.substring(i+1);
			else if(encrypted.charAt(i) == c)
				encrypted = encrypted.substring(0, i) + selected + encrypted.substring(i+1);
		}
		if(correct.equals(encrypted))
			fireEvent(QuoteEvent.FINISHED);
		else
			fireEvent(QuoteEvent.LETTER_CHANGED);
	}
	
	public int getTries(){
		return numTries;
	}
	
	public void useHint(){
		if(numHints > 0){
			numHints--;
			Random rng = new Random();
			int r = rng.nextInt(encrypted.length());
			while(isCorrect(r))
				r = rng.nextInt(encrypted.length());
			selected = encrypted.charAt(r);
			swap(correct.charAt(r));
		}
	}
	
	public void restart(String newQuote){
		numTries = 0;
		numHints = 3;
		correct = newQuote;
		encrypted = encrypt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(correct);
		dest.writeString(encrypted);
		dest.writeString(selected+"");
		dest.writeInt(numTries);
	}

}
