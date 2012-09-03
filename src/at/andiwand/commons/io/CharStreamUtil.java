package at.andiwand.commons.io;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.andiwand.commons.util.CharSequenceUtil;
import at.andiwand.commons.util.collections.CharArrayQueue;


public class CharStreamUtil {
	
	public static final int DEFAULT_BUFFER_SIZE = 8192;
	
	public static char readForward(Reader in) throws IOException {
		int read = in.read();
		if (read == -1) throw new IllegalStateException("end of stream");
		return (char) read;
	}
	
	public static int readForward(Reader in, char[] cbuf) throws IOException {
		int read = in.read(cbuf);
		if (read == -1) throw new IllegalStateException("end of stream");
		return read;
	}
	
	public static int readForward(Reader in, char[] cbuf, int off, int len)
			throws IOException {
		int read = in.read(cbuf, off, len);
		if (read == -1) throw new IllegalStateException("end of stream");
		return read;
	}
	
	public static int readForward(Reader in, CharBuffer target)
			throws IOException {
		int read = in.read(target);
		if (read == -1) throw new IllegalStateException("end of stream");
		return read;
	}
	
	public static int readCharacterwise(Reader in, char[] cbuf)
			throws IOException {
		if (cbuf == null) throw new NullPointerException();
		if (cbuf.length == 0) return 0;
		
		int i = 0;
		for (int read; (i < cbuf.length) && ((read = in.read()) != -1); i++) {
			cbuf[i] = (char) read;
		}
		
		if (i == 0) return -1;
		return i;
	}
	
	public static int readCharacterwise(Reader in, char[] cbuf, int off, int len)
			throws IOException {
		if (cbuf == null) throw new NullPointerException();
		if ((off < 0) || (len < 0) || (len > (cbuf.length - off)))
			throw new IndexOutOfBoundsException();
		if (len == 0) return 0;
		
		int i = 0;
		for (int read; (i < len) && ((read = in.read()) != -1); i++) {
			cbuf[off + i] = (char) read;
		}
		
		if (i == 0) return -1;
		return i;
	}
	
	public static int readCharacterwise(Reader in, CharBuffer target)
			throws IOException {
		if (target == null) throw new NullPointerException();
		int len = target.remaining();
		if (len == 0) return 0;
		
		int i = 0;
		for (int read; (i < len) && ((read = in.read()) != -1); i++) {
			target.put((char) read);
		}
		
		if (i == 0) return -1;
		return i;
	}
	
	public static String readLine(PushbackReader in) throws IOException {
		StringBuilder builder = new StringBuilder();
		
		int read = in.read();
		if (read == -1) return null;
		
		while (true) {
			char c = (char) read;
			
			if (c == '\n') break;
			if (c == '\r') {
				read = in.read();
				if (read == -1) break;
				c = (char) read;
				if (c == '\n') break;
				in.unread(c);
			}
			
			builder.append(c);
			
			read = in.read();
			if (read == -1) break;
		}
		
		return builder.toString();
	}
	
	// TODO: improve
	public static String readUntilChar(Reader in, char c) throws IOException {
		StringBuilder builder = new StringBuilder();
		
		while (true) {
			int read = in.read();
			if (read == c) return builder.toString();
			if (read == -1) throw new IllegalStateException("end of stream");
			
			builder.append((char) read);
		}
	}
	
	// TODO: improve
	public static String readUntilChar(Reader in, Set<Character> chars)
			throws IOException {
		StringBuilder builder = new StringBuilder();
		
		while (true) {
			int read = in.read();
			if (chars.contains((char) read)) return builder.toString();
			if (read == -1) throw new IllegalStateException("end of stream");
			
			builder.append((char) read);
		}
	}
	
	// TODO: improve
	public static String readUntilString(Reader in, String string)
			throws IOException {
		StringBuilder builder = new StringBuilder();
		
		int read;
		while ((read = in.read()) != -1) {
			builder.append((char) read);
			if ((builder.length() >= string.length())
					&& CharSequenceUtil.endsWith(builder, string))
				return builder.substring(0, builder.length() - string.length());
		}
		
		throw new IllegalStateException("end of stream");
	}
	
	public static String readAsString(Reader in) throws IOException {
		CharArrayWriter out = new CharArrayWriter();
		out.write(in);
		return out.toString();
	}
	
	public static void writeCharacterwise(Writer out, char[] cbuf)
			throws IOException {
		if (cbuf == null) throw new NullPointerException();
		
		for (int i = 0; i < cbuf.length; i++) {
			out.write(cbuf[i]);
		}
	}
	
	public static void writeCharacterwise(Writer out, char[] cbuf, int off,
			int len) throws IOException {
		if (cbuf == null) throw new NullPointerException();
		if ((off < 0) || (len < 0) || (len > (cbuf.length - off)))
			throw new IndexOutOfBoundsException();
		
		for (int i = 0; i < len; i++) {
			out.write(cbuf[off + i]);
		}
	}
	
	public static void writeCharacterwise(Writer out, String str)
			throws IOException {
		if (str == null) throw new NullPointerException();
		
		for (int i = 0; i < str.length(); i++) {
			out.write(str.charAt(i));
		}
	}
	
	public static void writeCharacterwise(Writer out, String str, int off,
			int len) throws IOException {
		if (str == null) throw new NullPointerException();
		if ((off < 0) || (len < 0) || (len > (str.length() - off)))
			throw new IndexOutOfBoundsException();
		
		if (len < 0) throw new IllegalArgumentException("len is negativ");
		
		for (int i = 0; i < len; i++) {
			out.write(str.charAt(off + i));
		}
	}
	
	public static void appendCharacterwise(Writer out, CharSequence csq)
			throws IOException {
		if (csq == null) csq = "null";
		
		for (int i = 0; i < csq.length(); i++) {
			out.append(csq.charAt(i));
		}
	}
	
	public static void appendCharacterwise(Writer out, CharSequence csq,
			int start, int end) throws IOException {
		if (start < 0)
			throw new IndexOutOfBoundsException("start cannot be less than 0");
		if (end < 0)
			throw new IndexOutOfBoundsException("end cannot be less than 0");
		if (start > end)
			throw new IndexOutOfBoundsException(
					"start cannot be greater than end");
		if (csq == null) csq = "null";
		
		for (int i = start; i < end; i++) {
			out.append(csq.charAt(i));
		}
	}
	
	public static void flushCharacterwise(Reader in) throws IOException {
		while (in.read() != -1);
	}
	
	public static void flushLine(PushbackReader in) throws IOException {
		for (int read = in.read(); read != -1; read = in.read()) {
			char c = (char) read;
			
			if (c == '\n') break;
			if (c == '\r') {
				read = in.read();
				if (read == -1) break;
				c = (char) read;
				if (c == '\n') break;
				in.unread(c);
			}
		}
	}
	
	public static int flushWhitespace(Reader in) throws IOException {
		for (int read; (read = in.read()) != -1;) {
			if (Character.isWhitespace((char) read)) continue;
			
			return read;
		}
		
		return -1;
	}
	
	public static void flushWhitespace(PushbackReader in) throws IOException {
		for (int read; (read = in.read()) != -1;) {
			if (Character.isWhitespace((char) read)) continue;
			
			in.unread(read);
			return;
		}
	}
	
	public static void flushCharacters(PushbackReader in, char c)
			throws IOException {
		while (true) {
			int read = in.read();
			if (read == -1) return;
			
			if (read == c) continue;
			
			in.unread(read);
			return;
		}
	}
	
	public static void flushCharacters(PushbackReader in, Set<Character> chars)
			throws IOException {
		while (true) {
			int read = in.read();
			if (read == -1) return;
			
			if (chars.contains((char) read)) continue;
			
			in.unread(read);
			return;
		}
	}
	
	public static void flushCharacters(PushbackReader in, CharFilter filter)
			throws IOException {
		while (true) {
			int read = in.read();
			if (read == -1) return;
			
			if (!filter.accept((char) read)) continue;
			
			in.unread(read);
			return;
		}
	}
	
	// TODO: improve
	public static void flushUntilCharacter(Reader in, char c)
			throws IOException {
		while (true) {
			int read = in.read();
			if (read == -1) throw new IllegalStateException("end of stream");
			if (read == c) break;
		}
	}
	
	// TODO: improve
	public static void flushUntilCharacter(Reader in, Set<Character> chars)
			throws IOException {
		while (true) {
			int read = in.read();
			if (read == -1) throw new IllegalStateException("end of stream");
			if (chars.contains((char) read)) break;
		}
	}
	
	// TODO: improve
	public static void flushUntilString(Reader in, String string)
			throws IOException {
		CharArrayQueue queue = new CharArrayQueue(string.length());
		
		int read;
		while ((read = in.read()) != -1) {
			if ((queue.size() >= string.length())
					&& CharSequenceUtil.equals(queue, string)) return;
			queue.put((char) read);
		}
		
		throw new IllegalStateException("end of stream");
	}
	
	// TODO: improve
	public static Matcher flushUntilMatch(Reader in, Pattern pattern)
			throws IOException {
		StringBuilder builder = new StringBuilder();
		
		int read;
		while ((read = in.read()) != -1) {
			builder.append((char) read);
			
			Matcher matcher = pattern.matcher(builder);
			if (matcher.matches()) return matcher;
		}
		
		throw new IllegalStateException("end of stream");
	}
	
	// TODO: improve
	public static Matcher flushUntilFind(Reader in, Pattern pattern)
			throws IOException {
		StringBuilder builder = new StringBuilder();
		
		int read;
		while ((read = in.read()) != -1) {
			builder.append((char) read);
			
			Matcher matcher = pattern.matcher(builder);
			if (matcher.find()) return matcher;
		}
		
		throw new IllegalStateException("end of stream");
	}
	
	public static void flushBuffered(Reader in) throws IOException {
		flushBuffered(in, DEFAULT_BUFFER_SIZE);
	}
	
	public static void flushBuffered(Reader in, int bufferSize)
			throws IOException {
		char[] cbuf = new char[bufferSize];
		while (in.read(cbuf, 0, bufferSize) != -1);
	}
	
	public static long skipCharacterwise(Reader in, long n) throws IOException {
		for (long i = 0; i < n; i++) {
			if (in.read() == -1) return i;
		}
		
		return n;
	}
	
	private final int bufferSize;
	private char[] cbuf;
	
	public CharStreamUtil() {
		this(DEFAULT_BUFFER_SIZE);
	}
	
	public CharStreamUtil(boolean initBuffer) {
		this(DEFAULT_BUFFER_SIZE, false);
	}
	
	public CharStreamUtil(int bufferSize) {
		this(bufferSize, false);
	}
	
	public CharStreamUtil(int bufferSize, boolean initBuffer) {
		this.bufferSize = bufferSize;
		
		if (initBuffer) initBuffer();
	}
	
	private void initBuffer() {
		if (cbuf == null) cbuf = new char[bufferSize];
	}
	
	public int getBufferSize() {
		return bufferSize;
	}
	
	public int writeStream(Reader in, Writer out) throws IOException {
		initBuffer();
		int count = 0;
		
		for (int read; (read = in.read(cbuf)) != -1; count++) {
			out.write(cbuf, 0, read);
		}
		
		return count;
	}
	
	public int writeStreamLimited(Reader in, Writer out, int len)
			throws IOException {
		if (len == 0) return 0;
		
		initBuffer();
		int count = 0;
		
		while (count < len) {
			int read = in.read(cbuf, 0, Math.min(bufferSize, len - count));
			if (read == -1) break;
			out.write(cbuf, 0, read);
			count += read;
		}
		
		return count;
	}
	
	public void flush(Reader in) throws IOException {
		initBuffer();
		while (in.read(cbuf, 0, bufferSize) != -1);
	}
	
}