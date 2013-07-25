package at.stefl.commons.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import at.stefl.commons.util.ByteDataUtil;

public class ByteDataInputStream extends DelegationInputStream {

    private final byte[] maxDataUnit = new byte[8];

    private Endian endian;

    public ByteDataInputStream(InputStream in) {
	super(in);
    }

    public ByteDataInputStream(InputStream in, Endian endian) {
	super(in);

	this.endian = endian;
    }

    public Endian getEndian() {
	return endian;
    }

    public void setEndian(Endian endian) {
	this.endian = endian;
    }

    public byte[] readFully(int len) throws IOException {
	return ByteStreamUtil.readFully(in, len);
    }

    public void readFully(byte[] b) throws IOException {
	ByteStreamUtil.readFully(in, b);
    }

    public void readFully(byte[] b, int off, int len) throws IOException {
	ByteStreamUtil.readFully(in, b, off, len);
    }

    private void readDataUnit(int size) throws IOException {
	ByteStreamUtil.readFully(in, maxDataUnit, 0, size);
    }

    public boolean readBoolean() throws IOException {
	int read = in.read();
	if (read == -1)
	    throw new EOFException();
	return read != 0;
    }

    public byte readByte() throws IOException {
	int read = in.read();
	if (read == -1)
	    throw new EOFException();
	return (byte) read;
    }

    public char readChar() throws IOException {
	readDataUnit(2);
	return ByteDataUtil.getAsChar(endian, maxDataUnit);
    }

    public short readShort() throws IOException {
	readDataUnit(2);
	return ByteDataUtil.getAsShort(endian, maxDataUnit);
    }

    public int readInt() throws IOException {
	readDataUnit(4);
	return ByteDataUtil.getAsInt(endian, maxDataUnit);
    }

    public long readLong() throws IOException {
	readDataUnit(8);
	return ByteDataUtil.getAsLong(endian, maxDataUnit);
    }

}