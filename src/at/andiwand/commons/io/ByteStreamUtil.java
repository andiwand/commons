package at.andiwand.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ByteStreamUtil {
	
	public static final int DEFAULT_BUFFER_SIZE = 8192;
	
	public static byte readForward(InputStream in) throws IOException {
		int read = in.read();
		if (read == -1) throw new IllegalStateException("end of stream");
		return (byte) read;
	}
	
	public static int readForward(InputStream in, byte[] b) throws IOException {
		int read = in.read(b);
		if (read == -1) throw new IllegalStateException("end of stream");
		return read;
	}
	
	public static int readForward(InputStream in, byte[] b, int off, int len)
			throws IOException {
		int read = in.read(b, off, len);
		if (read == -1) throw new IllegalStateException("end of stream");
		return read;
	}
	
	public static int readBytewise(InputStream in, byte[] b) throws IOException {
		if (b == null) throw new NullPointerException();
		if (b.length == 0) return 0;
		
		int i = 0;
		for (int read; (i < b.length) && ((read = in.read()) != -1); i++) {
			b[i] = (byte) read;
		}
		
		if (i == 0) return -1;
		return i;
	}
	
	public static int readBytewise(InputStream in, byte[] b, int off, int len)
			throws IOException {
		if (b == null) throw new NullPointerException();
		if ((off < 0) || (len < 0) || (len > (b.length - off)))
			throw new IndexOutOfBoundsException();
		if (len == 0) return 0;
		
		int i = 0;
		for (int read; (i < len) && ((read = in.read()) != -1); i++) {
			b[off + i] = (byte) read;
		}
		
		if (i == 0) return -1;
		return i;
	}
	
	public static byte[] readAsByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(in);
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
		if (b == null) throw new NullPointerException();
		if ((off < 0) || (len < 0) || (len > (b.length - off)))
			throw new IndexOutOfBoundsException();
		
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