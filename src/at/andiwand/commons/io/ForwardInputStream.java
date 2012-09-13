package at.andiwand.commons.io;

import java.io.IOException;
import java.io.InputStream;


public class ForwardInputStream extends FilterInputStream {
	
	public ForwardInputStream(InputStream in) {
		super(in);
	}
	
	@Override
	public int read() throws IOException {
		return ByteStreamUtil.readForward(in);
	}
	
	@Override
	public int read(byte[] b) throws IOException {
		return ByteStreamUtil.readForward(in, b);
	}
	
	public int read(byte[] b, int off, int len) throws IOException {
		return ByteStreamUtil.readForward(in, b, off, len);
	}
	
}