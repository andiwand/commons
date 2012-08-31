package at.andiwand.common.io;

import java.io.IOException;
import java.io.Writer;


public class ApplyFilterWriter extends CharacterwiseFilterWriter {
	
	private final CharacterFilter filter;
	
	public ApplyFilterWriter(Writer out, CharacterFilter filter) {
		super(out);
		
		this.filter = filter;
	}
	
	@Override
	public void write(int c) throws IOException {
		if (filter.accept((char) c)) out.write(c);
	}
	
}