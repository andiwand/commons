package at.stefl.commons.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import at.stefl.commons.io.CharStreamUtil;

public class Base64InputStream extends InputStream {

    public static void main(String[] args) throws Throwable {
	String base64 = "UG9seWZvbiB6d2l0c2NoZXJuZCBhw59lbiBNw6R4Y2hlbnMgVsO2Z2VsIFLDvGJlbiwgSm9naHVydCB1bmQgUXVhcms=";
	StringReader stringReader = new StringReader(base64);
	Base64InputStream base64InputStream = new Base64InputStream(
		stringReader);
	InputStreamReader inputStreamReader = new InputStreamReader(
		base64InputStream, "utf-8");

	System.out.println(CharStreamUtil.readString(inputStreamReader));
    }

    private final Reader in;
    private boolean closed;

    private final char[] inBuffer = new char[4];
    private final byte[] outBuffer = new byte[3];
    private int index = 3;

    public Base64InputStream(Reader in) {
	this.in = in;
    }

    @Override
    public int read() throws IOException {
	if (closed)
	    return -1;

	if (index >= 3) {
	    int read = in.read(inBuffer);

	    switch (read) {
	    case -1:
		closed = true;
		return -1;
	    case 4:
		// TODO: close in?
		if (Base64.decode3Byte(inBuffer, outBuffer) < 3)
		    closed = true;
		index = 0;
		break;
	    default:
		throw new IllegalStateException();
	    }
	}

	return outBuffer[index++];
    }

    @Override
    public int available() throws IOException {
	return 3 - index;
    }

    @Override
    public void close() throws IOException {
	if (closed)
	    return;
	closed = true;
	in.close();
    }

}