package at.andiwand.commons.util;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import at.andiwand.commons.util.collection.SingleLinkedNode;


// TODO: complete
public class CharArrayBuilder implements CharSequence {
	
	private static final int DEFAULT_INITIAL_SIZE = 16;
	
	private SingleLinkedNode<char[]> headNode;
	private SingleLinkedNode<char[]> lastNode;
	
	private char[] lastBuffer;
	private int lastIndex;
	
	private int length;
	
	public CharArrayBuilder() {
		this(DEFAULT_INITIAL_SIZE);
	}
	
	public CharArrayBuilder(int initialSize) {
		lastNode = headNode = new SingleLinkedNode<char[]>();
		lastNode.setEntry(lastBuffer = new char[initialSize]);
	}
	
	private void getSpace(int space) {
		if (lastNode.hasNext()) {
			lastNode = lastNode.getNext();
			lastBuffer = lastNode.getEntry();
		} else {
			int newSize = Math.max(lastBuffer.length << 1, space);
			lastNode = lastNode.append(new SingleLinkedNode<char[]>());
			lastNode.setEntry(lastBuffer = new char[newSize]);
		}
		
		lastIndex = 0;
	}
	
	@SuppressWarnings("unused")
	private char[] getBuffer(int index) {
		if (index >= length) throw new ArrayIndexOutOfBoundsException(index);
		
		SingleLinkedNode<char[]> currentNode = headNode;
		char[] currentBuffer;
		int currentSize = 0;
		
		while (true) {
			currentBuffer = currentNode.getEntry();
			currentSize += currentBuffer.length;
			if (index < currentSize) return currentBuffer;
			currentNode = currentNode.getNext();
		}
	}
	
	@Override
	public String toString() {
		return new String(toCharArray());
	}
	
	public char[] toCharArray() {
		if (length == 0) return ArrayUtil.EMPTY_CHAR_ARRAY;
		
		char[] result = new char[length];
		int index = 0;
		
		for (char[] buffer : headNode) {
			System.arraycopy(buffer, 0, result, index, buffer.length);
			index += buffer.length;
		}
		
		System.arraycopy(lastBuffer, 0, result, index, lastIndex);
		
		return result;
	}
	
	@Override
	public char charAt(int index) {
		// TODO: implement
		return 0;
	}
	
	@Override
	public int length() {
		return length;
	}
	
	@Override
	public CharSequence subSequence(int start, int end) {
		// TODO: implement
		return null;
	}
	
	public CharArrayBuilder append(char c) {
		if (lastIndex >= lastBuffer.length) getSpace(1);
		lastBuffer[lastIndex] = c;
		lastIndex++;
		length++;
		return this;
	}
	
	public CharArrayBuilder append(char[] cbuf) {
		return append(cbuf, 0, cbuf.length);
	}
	
	public CharArrayBuilder append(char[] cbuf, int off, int len) {
		if (cbuf == null) throw new NullPointerException();
		if ((off < 0) || (len < 0) || (len > (cbuf.length - off)))
			throw new IndexOutOfBoundsException();
		
		length += len;
		
		while (len > 0) {
			if (lastIndex >= lastBuffer.length) getSpace(len);
			
			int part = Math.min(lastBuffer.length - lastIndex, len);
			System.arraycopy(cbuf, off, lastBuffer, lastIndex, part);
			
			off += part;
			len -= part;
			lastIndex += part;
		}
		
		return this;
	}
	
	public CharArrayBuilder append(String str) {
		return append(str, 0, str.length());
	}
	
	public CharArrayBuilder append(String str, int off, int len) {
		if (str == null) throw new NullPointerException();
		if ((off < 0) || (len < 0) || (len > (str.length() - off)))
			throw new IndexOutOfBoundsException();
		
		length += len;
		
		for (int i = 0; i < len; i++) {
			if (lastIndex >= lastBuffer.length) getSpace(len - i);
			lastBuffer[lastIndex] = str.charAt(off + i);
			lastIndex++;
		}
		
		return this;
	}
	
	public CharArrayBuilder append(Reader in) throws IOException {
		while (true) {
			int read = in.read(lastBuffer, lastIndex, lastBuffer.length
					- lastIndex);
			if (read == -1) break;
			
			lastIndex += read;
			length += read;
			
			if (lastIndex >= lastBuffer.length) getSpace(lastBuffer.length);
		}
		
		return this;
	}
	
	public CharArrayBuilder append(Reader in, int len) throws IOException {
		while (true) {
			int part = Math.min(lastBuffer.length - lastIndex, len);
			int read = in.read(lastBuffer, lastIndex, part);
			if (read == -1) break;
			
			len -= read;
			lastIndex += read;
			length += read;
			
			if (len > 0) break;
			
			if (lastIndex >= lastBuffer.length) getSpace(lastBuffer.length);
		}
		
		return this;
	}
	
	public CharArrayBuilder append(CharSequence csq, int start, int end) {
		if ((start < 0) || (end < 0) || (start > end))
			throw new IndexOutOfBoundsException();
		if (csq == null) csq = "null";
		
		length += end - start + 1;
		
		for (int i = start; i <= end; i++) {
			if (lastIndex >= lastBuffer.length) getSpace(end - i + 1);
			lastBuffer[lastIndex] = csq.charAt(i);
			lastIndex++;
		}
		
		return this;
	}
	
	public void writeTo(Writer out) throws IOException {
		for (char[] buffer : headNode) {
			if (buffer == lastBuffer) break;
			out.write(buffer);
		}
		
		out.write(lastBuffer, 0, lastIndex);
	}
	
	public void reset() {
		lastNode = headNode;
		lastBuffer = lastNode.getEntry();
		lastIndex = 0;
		length = 0;
	}
	
}