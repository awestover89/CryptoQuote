package event;

import java.util.EventObject;

public class QuoteEvent extends EventObject{

	private static final long serialVersionUID = -7121640851933272229L;
	public final static int LETTER_SELECTED = 0;
	public final static int LETTER_CHANGED = 1;
	public final static int FINISHED = 2;
	
	int id;

	public QuoteEvent(Object source) {
		super(source);
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

}
