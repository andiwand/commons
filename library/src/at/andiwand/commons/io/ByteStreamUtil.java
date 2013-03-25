package at.andiwand.commons.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


// TODO: exceptions
public class ByteStreamUtil {
	
	public static final int DEFAULT_BUFFER_SIZE = 8192;
	
	public static int readTireless(InputStream in, byte[] b) throws IOException {
		if (b.length == 0) return 0;
		
		int result = 0;
		
		while (true) {
			int read = in.read(b, result, b.length - result);
			if (read == -1) break;
			
			result += read;
			if (result == b.length) break;
		}
		
		if (result == 0) return -1;
		return result;
	}
	
	public static int readTireless(InputStream in, byte[] b, int off, int len)
			throws IOException {
		if (len == 0) return 0;
		
		int result = 0;
		
		while (true) {
			int read = in.read(b, off + result, len - result);
			if (read == -1) break;
			
			result += read;
			if (result == len) break;
		}
		
		if (result == 0) return -1;
		return result;
	}
	
	public static int readBytewise(InputStream in, byte[] b) throws IOException {
		if (b.length == 0) return 0;
		
		int result = 0;
		
		while (true) {
			int read = in.read();
			if (read == -1) break;
			
			b[result] = (byte) read;
			
			result++;
			if (result == b.length) break;
		}
		
		if (result == 0) return -1;
		return result;
	}
	
	public static int readBytewise(InputStream in, byte[] b, int off, int len)
			throws IOException {
		if (len == 0) return 0;
		
		int result = 0;
		
		while (true) {
			int read = in.read();
			if (read == -1) break;
			
			b[off + result] = (byte) read;
			
			result++;
			if (result == len) break;
		}
		
		if (result == 0) return -1;
		return result;
	}
	
	public static byte readFully(InputStream in) throws IOException {
		int read = in.read();
		if (read == -1) throw new EOFException();
		return (byte) read;
	}
	
	public static byte[] readFully(InputStream in, int len) throws IOException {
		byte[] b = new byte[len];
		int read = readFully(in, b);
		if (read < len) throw new EOFException();
		return b;
	}
	
	public static int readFully(InputStream in, byte[] b) throws IOException {
		int read = readTireless(in, b);
		if (read < b.length) throw new EOFException();
		return read;
	}
	
	public static int readFully(InputStream in, byte[] b, int off, int len)
			throws IOException {
		int read = readTireless(in, b, off, len);
		if (read < len) throw new EOFException();
		return read;
	}
	
	public static byte[] readBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(in);
		out.close();
		return out.toByteArray();
	}
	
	public static void writeBytewise(OutputStream out, byte[] b)
			throws IOException {
		if (b == null) throw new NullPointerException();
		
		for (int i = 0; i < b.length; i++) {
			out.write(b[i]);
		}
	}
	
	public static void writeBytewise(OutputStream out, byte[] b, int off,
			int len) throws IOException {
		for (int i = 0; i < len; i++) {
			out.write(b[off + i]);
		}
	}
	
	public static void writeStreamBytewise(InputStream in, OutputStream out)
			throws IOException {
		for (int read; (read = in.read()) != -1;) {
			out.write(read);
		}
	}
	
	public static int writeStreamBytewiseLimited(InputStream in,
			OutputStream out, int len) throws IOException {
		int count = 0;
		
		for (int read; (count < len) && ((read = in.read()) != -1); count++) {
			out.write(read);
		}
		
		return count;
	}
	
	public static int writeStreamBuffered(InputStream in, OutputStream out)
			throws IOException {
		return writeStreamBuffered(in, out, DEFAULT_BUFFER_SIZE);
	}
	
	public static int writeStreamBuffered(InputStream in, OutputStream out,
			int bufferSize) throws IOException {
		byte[] b = new byte[bufferSize];
		return writeStreamBuffered(in, out, b);
	}
	
	public static int writeStreamBuffered(InputStream in, OutputStream out,
			byte[] b) throws IOException {
		int count = 0;
		
		for (int read; (read = in.read(b)) != -1; count++) {
			out.write(b, 0, read);
		}
		
		return count;
	}
	
	public static void flushBytewise(InputStream in) throws IOException {
		while (in.read() != -1);
	}
	
	public static long skipBytewise(InputStream in, long n) throws IOException {
		for (long i = 0; i < n; i++) {
			if (in.read() == -1) return i;
		}
		
		return n;
	}
	
	public static boolean equals(InputStream in, byte[] array)
			throws IOException {
		int read;
		
		for (int i = 0; i < array.length; i++) {
			read = in.read();
			if (read == -1) throw new EOFException();
			if (read != array[i]) return false;
		}
		
		return true;
	}
	
	public static boolean equals(InputStream in, byte[] array, int off, int len)
			throws IOException {
		int end = off + len;
		int read;
		
		for (int i = off; i < end; i++) {
			read = in.read();
			if (read == -1) throw new EOFException();
			if (read != array[i]) return false;
		}
		
		return true;
	}
	
	private final int bufferSize;
	private byte[] b;
	
	public ByteStreamUtil() {
		this(DEFAULT_BUFFER_SIZE, false);
	}
	
	public ByteStreamUtil(boolean initBuffer) {
		this(DEFAULT_BUFFER_SIZE, initBuffer);
	}
	
	public ByteStreamUtil(int bufferSize) {
		this(bufferSize, false);
	}
	
	public ByteStreamUtil(int bufferSize, boolean initBuffer) {
		this.bufferSize = bufferSize;
		
		if (initBuffer) initBuffer();
	}
	
	private void initBuffer() {
		if (b == null) b = new byte[bufferSize];
	}
	
	public int getBufferSize() {
		return bufferSize;
	}
	
	public int writeStream(InputStream in, OutputStream out) throws IOException {
		initBuffer();
		return writeStreamBuffered(in, out, b);
	}
	
	public int writeStreamLimited(InputStream in, OutputStream out, int len)
			throws IOException {
		if (len == 0) return 0;
		
		initBuffer();
		int count = 0;
		
		while (count < len) {
			int read = in.read(b, 0, Math.min(bufferSize, len - count));
			if (read == -1) break;
			out.write(b, 0, read);
			count += read;
		}
		
		return count;
	}
	
	public void flush(InputStream in) throws IOException {
		initBuffer();
		while (in.read(b, 0, bufferSize) != -1);
	}
	
}