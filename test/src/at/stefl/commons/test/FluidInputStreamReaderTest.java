package at.stefl.commons.test;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import at.stefl.commons.io.TeeInputStream;

public class FluidInputStreamReaderTest {

    public static void main(String[] args) throws Throwable {
	String charsetName = "ISO-8859-1";

	byte[] buffer = "¬".getBytes(charsetName);
	ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
	TeeInputStream teeInputStream = new TeeInputStream(inputStream,
		System.out);
	InputStreamReader reader = new InputStreamReader(teeInputStream,
		charsetName);

	System.out.println((char) reader.read());

	reader.close();
    }

}