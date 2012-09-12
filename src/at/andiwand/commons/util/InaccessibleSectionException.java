package at.andiwand.commons.util;

public class InaccessibleSectionException extends RuntimeException {
	
	private static final long serialVersionUID = 6515283105006148955L;
	
	public InaccessibleSectionException() {
		this("inaccessible section");
	}
	
	public InaccessibleSectionException(Throwable cause) {
		this("inaccessible section", cause);
	}
	
	public InaccessibleSectionException(String message) {
		super(message);
	}
	
	public InaccessibleSectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
}