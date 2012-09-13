package at.andiwand.commons.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import at.andiwand.commons.util.ByteDataUtil;


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
	
	public void readFully(byte[] b) throws IOException {
		if (ByteStreamUtil.readFully(in, b) < b.length)
			throw new EOFException();
	}
	
	public void readFully(byte[] b, int off, int len) throws IOException {
		if (ByteStreamUtil.readFully(in, b, off, len) < len)
			throw new EOFException();
	}
	
	private void readDataUnit(int size) throws IOException {
		if (ByteStreamUtil.readForward(in, maxDataUnit, 0, size) < size)
			throw new EOFException();
	}
	
	public boolean readBoolean() throws IOException {
		int read = in.read();
		if (read == -1) throw new EOFException();
		return read != 0;
	}
	
	public byte readByte() throws IOException {
		int read = in.read();
		if (read == -1) throw new EOFException();
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