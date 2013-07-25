package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import at.stefl.commons.util.array.ArrayUtil;

// TODO: implement close() ?
// TODO: implement input stream
// TODO: implement with LinkedList
public class ByteArrayOutputStream extends OutputStream {

    private static final int DEFAULT_INITIAL_SIZE = 16;

    private class BufferNode {
	private byte[] buffer;
	private BufferNode next;
    }

    private BufferNode headNode;
    private BufferNode currentNode;

    private byte[] currentBuffer;
    private int currentIndex;

    private int count;

    public ByteArrayOutputStream() {
	this(DEFAULT_INITIAL_SIZE);
    }

    public ByteArrayOutputStream(int initialSize) {
	currentNode = headNode = new BufferNode();
	currentBuffer = currentNode.buffer = new byte[initialSize];
    }

    @Override
    public String toString() {
	return new String(toByteArray());
    }

    public String toString(String charsetName)
	    throws UnsupportedEncodingException {
	return new String(toByteArray(), charsetName);
    }

    public int size() {
	return count;
    }

    public byte[] toByteArray() {
	if (count == 0)
	    return ArrayUtil.EMPTY_BYTE_ARRAY;

	byte[] result = new byte[count];
	int index = 0;

	for (BufferNode node = headNode; node != currentNode; node = node.next) {
	    System.arraycopy(node.buffer, 0, result, index, node.buffer.length);
	    index += node.buffer.length;
	}

	System.arraycopy(currentBuffer, 0, result, index, currentIndex);

	return result;
    }

    @Override
    public void write(int b) {
	if (currentIndex >= currentBuffer.length)
	    getMoreSpace(1);
	currentBuffer[currentIndex] = (byte) b;
	currentIndex++;
	count++;
    }

    @Override
    public void write(byte[] b) {
	write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) {
	if (b == null)
	    throw new NullPointerException();
	if ((off < 0) || (len < 0) || (len > (b.length - off)))
	    throw new IndexOutOfBoundsException();

	count += len;

	while (len > 0) {
	    if (currentIndex >= currentBuffer.length)
		getMoreSpace(len);

	    int part = Math.min(currentBuffer.length - currentIndex, len);
	    System.arraycopy(b, off, currentBuffer, currentIndex, part);

	    off += part;
	    len -= part;
	    currentIndex += part;
	}
    }

    public int write(InputStream in) throws IOException {
	int lastCount = count;

	while (true) {
	    int read = in.read(currentBuffer, currentIndex,
		    currentBuffer.length - currentIndex);
	    if (read == -1)
		break;

	    currentIndex += read;
	    count += read;

	    if (currentIndex >= currentBuffer.length)
		getMoreSpace(currentBuffer.length);
	}

	return count - lastCount;
    }

    public int write(InputStream in, int len) throws IOException {
	int lastCount = count;

	while (true) {
	    int part = Math.min(currentBuffer.length - currentIndex, len);
	    int read = in.read(currentBuffer, currentIndex, part);
	    if (read == -1)
		break;

	    len -= read;
	    currentIndex += read;
	    count += read;

	    if (len > 0)
		break;

	    if (currentIndex >= currentBuffer.length)
		getMoreSpace(currentBuffer.length);
	}

	return count - lastCount;
    }

    public void writeTo(OutputStream out) throws IOException {
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
	    currentBuffer = currentNode.buffer = new byte[newSize];
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
    public void flush() {
    }

    @Override
    public void close() {
    }

}