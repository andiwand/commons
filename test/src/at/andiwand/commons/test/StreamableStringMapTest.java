package at.andiwand.commons.test;

import java.io.IOException;
import java.io.StringReader;

import at.andiwand.commons.io.StreamableStringMap;


public class StreamableStringMapTest {
	
	public static void main(String[] args) throws IOException {
		String source = "hallo";
		StringReader in = new StringReader(source);
		
		StreamableStringMap<String> map = new StreamableStringMap<String>();
		map.put("hallo", "hi.");
		map.put("not?", "not.");
		
		System.out.println(map.match(in));
	}
	
}