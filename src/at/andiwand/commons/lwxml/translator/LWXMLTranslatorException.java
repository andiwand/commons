package at.andiwand.commons.lwxml.translator;

import at.andiwand.commons.lwxml.LWXMLException;


public class LWXMLTranslatorException extends LWXMLException {
	
	private static final long serialVersionUID = -5114969261011687333L;
	
	public LWXMLTranslatorException() {
		super();
	}
	
	public LWXMLTranslatorException(String message) {
		super(message);
	}
	
	public LWXMLTranslatorException(Throwable cause) {
		super(cause);
	}
	
	public LWXMLTranslatorException(String message, Throwable cause) {
		super(message, cause);
	}
	
}