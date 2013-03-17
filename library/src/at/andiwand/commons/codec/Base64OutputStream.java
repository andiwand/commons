package at.andiwand.commons.codec;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;


public class Base64OutputStream extends OutputStream {
	
	private final Writer out;
	private boolean closed;
	
	private final char[] encodeTable;
	
	private byte[] inBuffer = new byte[3];
	private char[] outBuffer = new char[4];
	private int index;
	
	public Base64OutputStream(Writer out) {
		this(out, false);
	}
	
	public Base64OutputStream(Writer out, boolean urlSave) {
		this.out = out;
		this.encodeTable = urlSave ? Base64.URL_SAFE_ENCODE_TABLE
				: Base64.STANDARD_ENCODE_TABLE;
	}
	
	@Override
	public void write(int b) throws IOException {
		if (closed) throw new IOException("stream is already closed");
		
		inBuffer[index++] = (byte) b;
		
		if (index >= 3) {
			Base64.encode3Byte(inBuffer, outBuffer, encodeTable);
			out.write(outBuffer);
			index = 0;
		}
	}
	
	@Override
	public void flush() throws IOException {
		out.flush();
	}
	
	@Override
	public void close() throws IOException {
		if (closed) return;
		closed = true;
		
		if (index > 0) {
			Base64.encode3Byte(inBuffer, index, outBuffer, encodeTable);
			out.write(outBuffer);
		}
		
		out.close();
	}
	
}