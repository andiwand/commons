package at.andiwand.common.io;

import java.io.IOException;
import java.io.OutputStream;


public class UncloseableOutputStream extends DelegationOutputStream {
	
	public UncloseableOutputStream(OutputStream out) {
		super(out);
	}
	
	@Override
	public void close() throws IOException {
		out = ClosedOutputStream.CLOSED_OUTPUT_STREAM;
	}
	
}