package at.andiwand.commons.test;

import java.io.ByteArrayOutputStream;

import at.andiwand.commons.io.TeeOutputStream;


public class TeeOutputStreamTest {
	
	public static void main(String[] args) throws Throwable {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		TeeOutputStream teeOutputStream = new TeeOutputStream(outputStream,
				System.out);
		
		teeOutputStream.write("hallo welt!".getBytes());
		teeOutputStream.flush();
		
		teeOutputStream.close();
	}
	
}