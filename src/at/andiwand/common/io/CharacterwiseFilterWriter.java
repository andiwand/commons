package at.andiwand.common.io;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;


public abstract class CharacterwiseFilterWriter extends FilterWriter {
	
	public CharacterwiseFilterWriter(Writer out) {
		super(out);
	}
	
	@Override
	public abstract void write(int c) throws IOException;
	
	@Override
	public void write(char[] cbuf) throws IOException {
		CharacterStreamUtil.writeCharacterwise(this, cbuf);
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		CharacterStreamUtil.writeCharacterwise(this, cbuf, off, len);
	}
	
	@Override
	public void write(String str) throws IOException {
		CharacterStreamUtil.writeCharacterwise(this, str);
	}
	
	@Override
	public void write(String str, int off, int len) throws IOException {
		CharacterStreamUtil.writeCharacterwise(this, str, off, len);
	}
	
	@Override
	public Writer append(char c) throws IOException {
		write(c);
		return this;
	}
	
	@Override
	public Writer append(CharSequence csq) throws IOException {
		CharacterStreamUtil.appendCharacterwise(this, csq);
		return this;
	}
	
	@Override
	public Writer append(CharSequence csq, int start, int end)
			throws IOException {
		CharacterStreamUtil.appendCharacterwise(this, csq, start, end);
		return this;
	}
	
}