package at.andiwand.common.io;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import at.andiwand.common.util.ArrayUtil;


// TODO: improve (boxing crap)
public class UntilCharacterReader extends CharacterwiseFilterReader {
	
	private boolean closed;
	
	private Set<Character> characterSet;
	
	public UntilCharacterReader(Reader in, char... characters) {
		super(in);
		
		this.characterSet = ArrayUtil.toHashSet(characters);
	}
	
	public UntilCharacterReader(Reader in, Set<Character> characterSet) {
		super(in);
		
		this.characterSet = new HashSet<Character>(characterSet);
	}
	
	@Override
	public int read() throws IOException {
		if (closed) return -1;
		
		int read = in.read();
		
		if ((read == -1) || characterSet.contains((char) read)) {
			closed = true;
			return -1;
		}
		
		return read;
	}
	
}