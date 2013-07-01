package at.andiwand.commons.io;

import java.io.IOException;
import java.io.InputStream;

public class LimitedInputStream extends DelegationInputStream {

    private final int limit;
    private int left;

    public LimitedInputStream(InputStream in, int limit) {
	super(in);

	if (limit < 0)
	    throw new IllegalArgumentException("limit < 0");
	this.limit = limit;
	this.left = limit;
    }

    public int getLimit() {
	return limit;
    }

    public int getLeft() {
	return left;
    }

    @Override
    public int read() throws IOException {
	if (left <= 0)
	    return -1;
	left--;
	return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
	return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
	if (left <= 0)
	    return -1;
	int result = in.read(b, off, Math.min(left, len));
	left -= result;
	return result;
    }

    @Override
    public int available() throws IOException {
	return Math.min(left, in.available());
    }

}