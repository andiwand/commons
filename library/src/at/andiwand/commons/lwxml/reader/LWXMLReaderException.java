package at.andiwand.commons.lwxml.reader;

import at.andiwand.commons.lwxml.LWXMLException;

public class LWXMLReaderException extends LWXMLException {

    private static final long serialVersionUID = -5114969261011687333L;

    public LWXMLReaderException() {
    }

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