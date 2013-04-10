package at.andiwand.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import at.andiwand.commons.util.array.ArrayUtil;
import at.andiwand.commons.util.collection.SingleLinkedNode;


// TODO: implement close() ?
// TODO: implement writer
// TODO: implement with LinkedList
public class CharArrayWriter extends Writer {
	
	private static final int DEFAULT_INITIAL_SIZE = 16;
	
	private SingleLinkedNode<char[]> headNode;
	private SingleLinkedNode<char[]> currentNode;
	
	private char[] currentBuffer;
	private int currentIndex;
	
	private int size;
	
	public CharArrayWriter() {
		this(DEFAULT_INITIAL_SIZE);
	}
	
	public CharArrayWriter(int initialSize) {
		currentNode = headNode = new SingleLinkedNode<char[]>();
		currentNode.setEntry(currentBuffer = new char[initialSize]);
	}
	
	@Override
	public String toString() {
		return new String(toCharArray());
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public char[] toCharArray() {
		if (size == 0) return ArrayUtil.EMPTY_CHAR_ARRAY;
		
		char[] result = new char[size];
		int index = 0;
		
		for (char[] buffer : headNode) {
			if (buffer == currentBuffer) break;
			System.arraycopy(buffer, 0, result, index, buffer.length);
			index += buffer.length;
		}
		
		System.arraycopy(currentBuffer, 0, result, index, currentIndex);
		
		return result;
	}
	
	@Override
	public void write(int c) {
		if (currentIndex >= currentBuffer.length) getMoreSpace(1);
		currentBuffer[currentIndex] = (char) c;
		currentIndex++;
		size++;
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
		
		size += len;
		
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
		size += len;
		
		while (len > 0) {
			if (currentIndex >= currentBuffer.length) getMoreSpace(len);
			
			int part = Math.min(currentBuffer.length - currentIndex, len);
			str.getChars(off, off + part, currentBuffer, currentIndex);
			
			off += part;
			len -= part;
			currentIndex += part;
		}
		
		if (str == null) throw new NullPointerException();
		if ((off < 0) || (len < 0) || (len > (str.length() - off)))
			throw new IndexOutOfBoundsException();
		
		size += len;
		
		for (int i = 0; i < len; i++) {
			if (currentIndex >= currentBuffer.length) getMoreSpace(len - i);
			currentBuffer[currentIndex] = str.charAt(off + i);
			currentIndex++;
		}
	}
	
	public int write(Reader in) throws IOException {
		int lastCount = size;
		
		while (true) {
			int read = in.read(currentBuffer, currentIndex,
					currentBuffer.length - currentIndex);
			if (read == -1) break;
			
			currentIndex += read;
			size += read;
			
			if (currentIndex >= currentBuffer.length)
				getMoreSpace(currentBuffer.length);
		}
		
		return size - lastCount;
	}
	
	public int write(Reader in, int len) throws IOException {
		int lastCount = size;
		
		while (true) {
			int part = Math.min(currentBuffer.length - currentIndex, len);
			int read = in.read(currentBuffer, currentIndex, part);
			if (read == -1) break;
			
			len -= read;
			currentIndex += read;
			size += read;
			
			if (len > 0) break;
			
			if (currentIndex >= currentBuffer.length)
				getMoreSpace(currentBuffer.length);
		}
		
		return size - lastCount;
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
		
		size += end - start + 1;
		
		for (int i = start; i <= end; i++) {
			if (currentIndex >= currentBuffer.length)
				getMoreSpace(end - i + 1);
			currentBuffer[currentIndex] = csq.charAt(i);
			currentIndex++;
		}
		
		return this;
	}
	
	public void writeTo(Writer out) throws IOException {
		for (char[] buffer : headNode) {
			if (buffer == currentBuffer) break;
			out.write(buffer);
		}
		
		out.write(currentBuffer, 0, currentIndex);
	}
	
	private void getMoreSpace(int space) {
		if (currentNode.hasNext()) {
			currentNode = currentNode.getNext();
			currentBuffer = currentNode.getEntry();
		} else {
			int newSize = Math.max(currentBuffer.length << 1, space);
			currentNode = currentNode.append(new SingleLinkedNode<char[]>());
			currentNode.setEntry(currentBuffer = new char[newSize]);
		}
		
		currentIndex = 0;
	}
	
	public void reset() {
		currentNode = headNode;
		currentBuffer = currentNode.getEntry();
		currentIndex = 0;
		size = 0;
	}
	
	@Override
	public void flush() {}
	
	@Override
	public void close() {}
	
}