package at.andiwand.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;


// TODO: improve
// TODO: fix for android
public class FluidInputStreamReader extends Reader {
	
	private final InputStream in;
	
	private final Charset charset;
	private final CharsetDecoder decoder;
	private ByteBuffer inBuffer;
	private final CharBuffer outBuffer;
	
	private boolean closed;
	
	public FluidInputStreamReader(InputStream in) {
		this(in, Charset.defaultCharset());
	}
	
	public FluidInputStreamReader(InputStream in, Charset charset) {
		this.in = in;
		
		this.charset = charset;
		this.decoder = charset.newDecoder();
		
		inBuffer = ByteBuffer.allocate((int) charset.newEncoder()
				.maxBytesPerChar());
		outBuffer = CharBuffer.allocate((int) decoder.maxCharsPerByte());
	}
	
	public FluidInputStreamReader(InputStream in, String charset) {
		this(in, Charset.forName(charset));
	}
	
	public Charset getCharset() {
		return charset;
	}
	
	public CharsetDecoder getDecoder() {
		return decoder;
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	@Override
	public int read() throws IOException {
		if (closed) return -1;
		
		if ((outBuffer.remaining() == 0) || (outBuffer.position() == 0)) {
			outBuffer.limit(outBuffer.capacity());
			outBuffer.position(0);
			
			int limit = 1;
			while (true) {
				int read = in.read();
				if (read == -1) {
					closed = true;
					return -1;
				}
				
				inBuffer.limit(limit);
				inBuffer.put(limit - 1, (byte) read);
				
				CoderResult coderResult = decoder.decode(inBuffer, outBuffer,
						true);
				if (!coderResult.isError()) break;
				
				limit++;
				
				if (inBuffer.capacity() <= limit) {
					inBuffer.rewind();
					limit = 1;
				}
			}
			
			inBuffer.position(0);
			outBuffer.limit(outBuffer.position());
			outBuffer.position(0);
		}
		
		return outBuffer.get();
	}
	
	@Override
	public int read(char[] cbuf) throws IOException {
		return CharStreamUtil.readCharwise(this, cbuf);
	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return CharStreamUtil.readCharwise(this, cbuf, off, len);
	}
	
	@Override
	public int read(CharBuffer target) throws IOException {
		return CharStreamUtil.readCharwise(this, target);
	}
	
	@Override
	public void close() throws IOException {
		if (closed) return;
		
		closed = true;
		in.close();
	}
	
}