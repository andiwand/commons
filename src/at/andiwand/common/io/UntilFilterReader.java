package at.andiwand.common.io;

import java.io.IOException;
import java.io.Reader;


public class UntilFilterReader extends CharacterwiseFilterReader {
	
	private boolean closed;
	
	private final CharacterFilter filter;
	
	public UntilFilterReader(Reader in, CharacterFilter filter) {
		super(in);
		
		this.filter = filter;
	}
	
	@Override
	public int read() throws IOException {
		if (closed) return -1;
		
		int read = in.read();
		
		if ((read == -1) || !filter.accept((char) read)) {
			closed = true;
			return -1;
		}
		
		return read;
	}
	
}