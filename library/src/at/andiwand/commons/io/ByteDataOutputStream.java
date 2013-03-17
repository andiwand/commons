package at.andiwand.commons.io;

import java.io.IOException;
import java.io.OutputStream;

import at.andiwand.commons.util.ByteDataUtil;


public class ByteDataOutputStream extends DelegationOutputStream {
	
	private final byte[] maxDataUnit = new byte[8];
	
	private Endian endian;
	
	public ByteDataOutputStream(OutputStream out) {
		super(out);
	}
	
	public ByteDataOutputStream(OutputStream out, Endian endian) {
		super(out);
		
		this.endian = endian;
	}
	
	public Endian getEndian() {
		return endian;
	}
	
	public void setEndian(Endian endian) {
		this.endian = endian;
	}
	
	private void writeDataUnit(int size) throws IOException {
		out.write(maxDataUnit, 0, size);
	}
	
	public void writeBoolean(boolean v) throws IOException {
		out.write(v ? 1 : 0);
	}
	
	public void writeByte(byte v) throws IOException {
		out.write(v);
	}
	
	public void writeChar(char v) throws IOException {
		ByteDataUtil.getBytes(endian, v, maxDataUnit);
		writeDataUnit(2);
	}
	
	public void writeShort(short v) throws IOException {
		ByteDataUtil.getBytes(endian, v, maxDataUnit);
		writeDataUnit(2);
	}
	
	public void writeInt(int v) throws IOException {
		ByteDataUtil.getBytes(endian, v, maxDataUnit);
		writeDataUnit(4);
	}
	
	public void writeLong(long v) throws IOException {
		ByteDataUtil.getBytes(endian, v, maxDataUnit);
		writeDataUnit(8);
	}
	
}