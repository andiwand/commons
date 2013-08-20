package at.stefl.commons.test;

import java.io.ByteArrayOutputStream;

public class ByteArrayOutputStreamTest {

    public static void main(String[] args) throws Throwable {
	long start;
	long end;

	start = System.nanoTime();
	{
	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    for (int i = 0; i < 100000000; i++) {
		out.write(i);
	    }

	    out.toByteArray();
	    out.close();
	}
	end = System.nanoTime();
	System.out.println("java " + (end - start) / 1000000000d);

	start = System.nanoTime();
	{
	    at.stefl.commons.io.ByteArrayOutputStream out = new at.stefl.commons.io.ByteArrayOutputStream();

	    for (int i = 0; i < 100000000; i++) {
		out.write(i);
	    }

	    out.toByteArray();
	    out.close();
	}
	end = System.nanoTime();
	System.out.println("me " + (end - start) / 1000000000d);
    }

}