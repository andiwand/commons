package at.andiwand.common.lwxml.reader;

import at.andiwand.common.lwxml.LWXMLException;


public class LWXMLReaderException extends LWXMLException {
	
	private static final long serialVersionUID = -5114969261011687333L;
	
	public LWXMLReaderException() {}
	
	public LWXMLReaderException(String message) {
		super(message);
	}
	
	public LWXMLReaderException(Throwable cause) {
		super(cause);
	}
	
	public LWXMLReaderException(String message, Throwable cause) {
		super(message, cause);
	}
	
}