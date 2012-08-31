package at.andiwand.common.test;

import java.io.ByteArrayInputStream;

import at.andiwand.common.io.FluidInputStreamReader;
import at.andiwand.common.io.TeeInputStream;


public class FluidInputStreamReaderTest {
	
	public static void main(String[] args) throws Throwable {
		String charsetName = "ISO-8859-1";
		
		byte[] buffer = "¬".getBytes(charsetName);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
		TeeInputStream teeInputStream = new TeeInputStream(inputStream,
				System.out);
		FluidInputStreamReader reader = new FluidInputStreamReader(
				teeInputStream, charsetName);
		
		System.out.println((char) reader.read());
	}
	
}