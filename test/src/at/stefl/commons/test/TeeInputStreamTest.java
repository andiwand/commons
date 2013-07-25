package at.stefl.commons.test;

import java.io.ByteArrayInputStream;

import at.stefl.commons.io.TeeInputStream;

public class TeeInputStreamTest {

    public static void main(String[] args) throws Throwable {
	byte[] array = "hallo welt!".getBytes();
	ByteArrayInputStream inputStream = new ByteArrayInputStream(array);
	TeeInputStream teeInputStream = new TeeInputStream(inputStream,
		System.out);

	teeInputStream.read(new byte[array.length]);

	teeInputStream.close();
    }

}