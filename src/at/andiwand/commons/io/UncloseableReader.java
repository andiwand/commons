package at.andiwand.commons.io;

import java.io.IOException;
import java.io.Reader;


public class UncloseableReader extends DelegationReader {
	
	public UncloseableReader(Reader in) {
		super(in);
	}
	
	@Override
	public void close() throws IOException {
		in = ClosedReader.CLOSED_READER;
	}
	
}