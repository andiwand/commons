package at.andiwand.commons.io;

import java.io.IOException;
import java.io.InputStream;


public class UntilFilterInputStream extends BytewiseFilterInputStream {
	
	private boolean closed;
	
	private final ByteFilter filter;
	
	public UntilFilterInputStream(InputStream in, ByteFilter filter) {
		super(in);
		
		this.filter = filter;
	}
	
	@Override
	public int read() throws IOException {
		if (closed) return -1;
		
		int read = in.read();
		
		if ((read == -1) || !filter.accept((byte) read)) {
			closed = true;
			return -1;
		}
		
		return read;
	}
	
}