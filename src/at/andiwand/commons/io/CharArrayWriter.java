package at.andiwand.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;


// TODO: implement close() ?
// TODO: implement writer
// TODO: implement with LinkedList
public class CharArrayWriter extends Writer {
	
	private static final int DEFAULT_INITIAL_SIZE = 1024;
	
	private static final char[] EMPTY_CHAR_ARRAY = {};
	
	private class BufferNode {
		private char[] buffer;
		private BufferNode next;
	}
	
	private BufferNode headNode;
	private BufferNode currentNode;
	
	private char[] currentBuffer;
	private int currentIndex;
	
	private int count;
	
	public CharArrayWriter() {
		this(DEFAULT_INITIAL_SIZE);
	}
	
	public CharArrayWriter(int initialSize) {
		currentNode = headNode = new BufferNode();
		currentBuffer = currentNode.buffer = new char[initialSize];
	}
	
	@Override
	public String toString() {
		return new String(toCharArray());
	}
	
	public int size() {
		return count;
	}
	
	public char[] toCharArray() {
		if (count == 0) return EMPTY_CHAR_ARRAY;
		
		char[] result = new char[count];
		int index = 0;
		
		for (BufferNode node = headNode; node != currentNode; node = node.next) {
			System.arraycopy(node.buffer, 0, result, index, node.buffer.length);
			index += node.buffer.length;
		}
		
		System.arraycopy(currentBuffer, 0, result, index, currentIndex);
		
		return result;
	}
	
	@Override
	public void write(int c) {
		if (currentIndex >= currentBuffer.length) getMoreSpace(1);
		currentBuffer[currentIndex] = (char) c;
		currentIndex++;
		count++;
	}
	
	@Override
	public void write(char[] cbuf) {
		write(cbuf, 0, cbuf.length);
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) {
		if (cbuf == null) throw new NullPointerException();
		if ((off < 0) || (len < 0) || (len > (cbuf.length - off)))
			throw new IndexOutOfBoundsException();
		
		count += len;
		
		while (len > 0) {
			if (currentIndex >= currentBuffer.length) getMoreSpace(len);
			
			int part = Math.min(currentBuffer.length - currentIndex, len);
			System.arraycopy(cbuf, off, currentBuffer, currentIndex, part);
			
			off += part;
			len -= part;
			currentIndex += part;
		}
	}
	
	@Override
	public void write(String str) {
		write(str, 0, str.length());
	}
	
	@Override
	public void write(String str, int off, int len) {
		if (str == null) throw new NullPointerException();
		if ((off < 0) || (len < 0) || (len > (str.length() - off)))
			throw new IndexOutOfBoundsException();
		
		count += len;
		
		for (int i = 0; i < len; i++) {
			if (currentIndex >= currentBuffer.length) getMoreSpace(len - i);
			currentBuffer[currentIndex] = str.charAt(off + i);
			currentIndex++;
		}
	}
	
	public int write(Reader in) throws IOException {
		int lastCount = count;
		
		while (true) {
			int read = in.read(currentBuffer, currentIndex,
					currentBuffer.length - currentIndex);
			if (read == -1) break;
			
			currentIndex += read;
			count += read;
			
			if (currentIndex >= currentBuffer.length)
				getMoreSpace(currentBuffer.length);
		}
		
		return count - lastCount;
	}
	
	public int write(Reader in, int len) throws IOException {
		int lastCount = count;
		
		while (true) {
			int part = Math.min(currentBuffer.length - currentIndex, len);
			int read = in.read(currentBuffer, currentIndex, part);
			if (read == -1) break;
			
			len -= read;
			currentIndex += read;
			count += read;
			
			if (len > 0) break;
			
			if (currentIndex >= currentBuffer.length)
				getMoreSpace(currentBuffer.length);
		}
		
		return count - lastCount;
	}
	
	@Override
	public Writer append(char c) {
		write(c);
		return this;
	}
	
	@Override
	public Writer append(CharSequence csq) {
		return append(csq, 0, csq.length());
	}
	
	@Override
	public Writer append(CharSequence csq, int start, int end) {
		if ((start < 0) || (end < 0) || (start > end))
			throw new IndexOutOfBoundsException();
		if (csq == null) csq = "null";
		
		count += end - start + 1;
		
		for (int i = start; i <= end; i++) {
			if (currentIndex >= currentBuffer.length)
				getMoreSpace(end - i + 1);
			currentBuffer[currentIndex] = csq.charAt(i);
			currentIndex++;
		}
		
		return this;
	}
	
	public void writeTo(Writer out) throws IOException {
		for (BufferNode node = headNode; node != currentNode; node = node.next) {
			out.write(node.buffer);
		}
		
		out.write(currentBuffer, 0, currentIndex);
	}
	
	private void getMoreSpace(int space) {
		if (currentNode.next != null) {
			currentNode = currentNode.next;
			currentBuffer = currentNode.buffer;
		} else {
			int newSize = Math.max(currentBuffer.length << 1, space);
			currentNode = currentNode.next = new BufferNode();
			currentBuffer = currentNode.buffer = new char[newSize];
		}
		
		currentIndex = 0;
	}
	
	public void reset() {
		currentNode = headNode;
		currentBuffer = currentNode.buffer;
		currentIndex = 0;
		count = 0;
	}
	
	@Override
	public void flush() {}
	
	@Override
	public void close() {}
	
}