package at.andiwand.common.io;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;


public abstract class CharacterwiseFilterReader extends FilterReader {
	
	public CharacterwiseFilterReader(Reader in) {
		super(in);
	}
	
	@Override
	public abstract int read() throws IOException;
	
	@Override
	public int read(char[] cbuf) throws IOException {
		return CharacterStreamUtil
				.readCharacterwise(this, cbuf, 0, cbuf.length);
	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return CharacterStreamUtil.readCharacterwise(this, cbuf, off, len);
	}
	
	@Override
	public int read(CharBuffer target) throws IOException {
		return CharacterStreamUtil.readCharacterwise(this, target);
	}
	
	@Override
	public long skip(long n) throws IOException {
		return CharacterStreamUtil.skipCharacterwise(this, n);
	}
	
}