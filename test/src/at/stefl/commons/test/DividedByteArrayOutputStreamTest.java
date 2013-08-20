package at.stefl.commons.test;

import java.io.InputStream;
import java.io.InputStreamReader;

import at.stefl.commons.io.CharStreamUtil;
import at.stefl.commons.io.DividedByteArrayOutputStream;

public class DividedByteArrayOutputStreamTest {

    public static void main(String[] args) throws Throwable {
	DividedByteArrayOutputStream out = new DividedByteArrayOutputStream();

	out.write("HAAAAAAAAAAAAAAAAAAAAAAAAAAAALLLLLOOOOOO".getBytes());

	InputStream in = out.getInputStream();
	System.out
		.println(CharStreamUtil.readString(new InputStreamReader(in)));

	out.reset();
	try {
	    System.out.println(in.read());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	out.close();
    }

}