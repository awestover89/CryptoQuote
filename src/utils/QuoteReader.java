package utils;

import java.io.IOException;
import java.io.InputStream;

public class QuoteReader {
	
	InputStream in;
	private static final int _CR = 13;
	private static final int _LF = 10;
	private int _last=-1; // The last char we've read
	private int _ch = -1;   // currently read char
	
	public QuoteReader(InputStream i){
		in = i;
	}
	
	public String readLine() throws IOException
	   {
	      StringBuffer sb = new StringBuffer("");
	      if (_last != -1) sb.append((char) _last);
	      _ch = in.read();
	      while(_ch != _CR && _ch != _LF && _ch != -1)
	      {
	         sb.append((char) _ch);
	         _ch = in.read();
	      }
	      // Read the next byte and check if it's a LF
	      _last = in.read();
	      if (_last == _LF) {
	         _last = -1;
	      }
	      return(new String(sb));
	   }

}
