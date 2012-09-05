package at.andiwand.commons.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


public class AdvancedDataInputStream extends DataInputStream {
	
	public AdvancedDataInputStream(InputStream in) {
		super(in);
	}
	
	public long readUnsignedInt() throws IOException {
		return readInt() & 0xffffffffl;
	}
	
}