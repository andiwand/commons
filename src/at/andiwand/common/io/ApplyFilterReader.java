package at.andiwand.common.io;

import java.io.IOException;
import java.io.Reader;


public class ApplyFilterReader extends CharacterwiseFilterReader {
	
	private final CharacterFilter filter;
	
	public ApplyFilterReader(Reader in, CharacterFilter filter) {
		super(in);
		
		this.filter = filter;
	}
	
	@Override
	public int read() throws IOException {
		int read;
		
		do {
			read = in.read();
			if (read == -1) return -1;
		} while (!filter.accept((char) read));
		
		return read;
	}
	
}