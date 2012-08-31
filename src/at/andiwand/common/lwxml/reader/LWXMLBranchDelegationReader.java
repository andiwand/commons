package at.andiwand.common.lwxml.reader;

import java.io.IOException;
import java.nio.CharBuffer;

import at.andiwand.common.lwxml.LWXMLEvent;
import at.andiwand.common.lwxml.LWXMLUtil;


public class LWXMLBranchDelegationReader extends LWXMLFilterReader<LWXMLReader> {
	
	private LWXMLEvent lastEvent;
	private LWXMLBranchReader branchReader;
	
	public LWXMLBranchDelegationReader(LWXMLReader in) {
		super(in);
	}
	
	public LWXMLBranchReader getBranchReader() {
		return (branchReader == null) ? (branchReader = new LWXMLBranchReader(
				in)) : branchReader;
	}
	
	@Override
	public LWXMLEvent readEvent() throws IOException {
		if (branchReader != null) {
			LWXMLUtil.flush(branchReader);
			branchReader = null;
		}
		
		return lastEvent = in.readEvent();
	}
	
	@Override
	public LWXMLEvent getCurrentEvent() {
		return lastEvent;
	}
	
	@Override
	public int read() throws IOException {
		if (branchReader != null) return -1;
		return in.read();
	}
	
	@Override
	public int read(char[] cbuf) throws IOException {
		if (branchReader != null) return -1;
		return in.read(cbuf);
	}
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		if (branchReader != null) return -1;
		return in.read(cbuf, off, len);
	}
	
	@Override
	public int read(CharBuffer target) throws IOException {
		if (branchReader != null) return -1;
		return in.read(target);
	}
	
	@Override
	public long skip(long n) throws IOException {
		if (branchReader != null) return 0;
		return in.skip(n);
	}
	
}