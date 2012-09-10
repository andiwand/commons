package at.andiwand.commons.lwxml.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;

import at.andiwand.commons.io.ApplyFilterReader;
import at.andiwand.commons.io.CharFilter;
import at.andiwand.commons.io.CharStreamUtil;
import at.andiwand.commons.io.ForwardReader;
import at.andiwand.commons.io.UntilCharSequenceReader;
import at.andiwand.commons.io.UntilFilterReader;
import at.andiwand.commons.lwxml.LWXMLConstants;
import at.andiwand.commons.lwxml.LWXMLEvent;


// TODO: improve code
// TODO: improve malformed xml handling
public class LWXMLStreamReader extends LWXMLReader {
	
	private static final int PUSHBACK_BUFFER_SIZE = 1;
	
	private static final CharFilter WHITESPACE_FILTER = new CharFilter() {
		public boolean accept(char c) {
			return !LWXMLConstants.isWhitespace(c);
		}
	};
	
	private static final CharFilter END_ELEMENT_FILTER = new CharFilter() {
		public boolean accept(char c) {
			return c != '>';
		}
	};
	
	private static final CharFilter ATTRIBUTE_NAME_FILTER = new CharFilter() {
		public boolean accept(char c) {
			if (c == '=') return false;
			return true;
		}
	};
	
	private static final CharFilter ATTRIBUTE_VALUE_FILTER = new CharFilter() {
		public boolean accept(char c) {
			return c != '"';
		}
	};
	
	private static final CharFilter CHARACTERS_FILTER = new CharFilter() {
		public boolean accept(char c) {
			return c != '<';
		}
	};
	
	private final CharFilter startElementFilter = new CharFilter() {
		public boolean accept(char c) {
			if (LWXMLConstants.isWhitespace(c)) return false;
			
			switch (c) {
			case '/':
			case '>':
				try {
					LWXMLStreamReader.this.in.unread(c);
					return false;
				} catch (IOException e) {
					throw new IllegalStateException("unreachable section");
				}
			}
			
			return true;
		}
	};
	
	private boolean closed;
	private final PushbackReader in;
	private final ForwardReader fin;
	
	private LWXMLEvent lastEvent;
	
	private boolean handleAttributeList;
	private boolean handleEndEmptyElement;
	
	private Reader eventReader;
	
	public LWXMLStreamReader(InputStream in) {
		this(new InputStreamReader(in));
	}
	
	public LWXMLStreamReader(Reader in) {
		this.in = new PushbackReader(in, PUSHBACK_BUFFER_SIZE);
		this.fin = new ForwardReader(this.in);
	}
	
	@Override
	public LWXMLEvent getCurrentEvent() {
		return lastEvent;
	}
	
	@Override
	public LWXMLEvent readEvent() throws IOException {
		return lastEvent = readNextEventImpl();
	}
	
	private LWXMLEvent readNextEventImpl() throws IOException {
		if (closed) return LWXMLEvent.END_DOCUMENT;
		if (eventReader != null)
			CharStreamUtil.flushCharwise(eventReader);
		
		if (lastEvent != null) {
			switch (lastEvent) {
			case PROCESSING_INSTRUCTION_TARGET:
				handleProcessingInstructionData();
				return LWXMLEvent.PROCESSING_INSTRUCTION_DATA;
			case ATTRIBUTE_NAME:
				handleAttributeValue();
				return LWXMLEvent.ATTRIBUTE_VALUE;
			case CHARACTERS:
				return handleElement();
			}
		}
		
		if (handleAttributeList) return handleAttributeList();
		if (handleEndEmptyElement) {
			handleEndEmptyElement();
			return LWXMLEvent.END_EMPTY_ELEMENT;
		}
		
		int read = in.read();
		switch (read) {
		case -1:
			close();
			return LWXMLEvent.END_DOCUMENT;
		case '<':
			return handleElement();
		default:
			in.unread(read);
			handleCharacters();
			return LWXMLEvent.CHARACTERS;
		}
	}
	
	private LWXMLEvent handleElement() throws IOException {
		int c = in.read();
		
		switch (c) {
		case -1:
			close();
			return LWXMLEvent.END_DOCUMENT;
		case '/':
			handleEndElement();
			return LWXMLEvent.END_ELEMENT;
		case '!':
			return handleCallsign();
		case '?':
			handleProcessingInstructionTarget();
			return LWXMLEvent.PROCESSING_INSTRUCTION_TARGET;
		default:
			in.unread(c);
			handleStartElement();
			return LWXMLEvent.START_ELEMENT;
		}
	}
	
	private void handleEndElement() throws IOException {
		eventReader = new ApplyFilterReader(fin, WHITESPACE_FILTER);
		eventReader = new UntilFilterReader(eventReader, END_ELEMENT_FILTER);
	}
	
	private void handleEndEmptyElement() throws IOException {
		handleEndEmptyElement = false;
		eventReader = null;
	}
	
	private LWXMLEvent handleCallsign() throws IOException {
		int c = fin.read();
		
		switch (c) {
		case '-':
			if (fin.read() != '-')
				throw new LWXMLReaderException(
						"malformend tag: comment was expected");
			
			handleComment();
			return LWXMLEvent.COMMENT;
		case '[':
			// TODO: improve
			char[] buffer = new char[6];
			fin.read(buffer);
			if (!"CDATA[".equals(new String(buffer)))
				throw new LWXMLReaderException(
						"malformed tag: cdata was expected");
			
			handleCDATA();
			return LWXMLEvent.CDATA;
		default:
			throw new LWXMLReaderException(
					"malformed tag: comment or cdata was expected");
		}
	}
	
	private void handleComment() throws IOException {
		eventReader = new UntilCharSequenceReader(fin, "-->");
	}
	
	private void handleCDATA() throws IOException {
		eventReader = new UntilCharSequenceReader(fin, "]]>");
	}
	
	private void handleProcessingInstructionTarget() throws IOException {
		eventReader = new UntilFilterReader(fin, WHITESPACE_FILTER);
	}
	
	private void handleProcessingInstructionData() throws IOException {
		try {
			CharStreamUtil.flushChars(in, WHITESPACE_FILTER);
		} catch (IllegalStateException e) {
			throw new LWXMLReaderException("end of stream", e);
		}
		
		eventReader = new UntilCharSequenceReader(fin, "?>");
	}
	
	private void handleStartElement() throws IOException {
		handleAttributeList = true;
		eventReader = new UntilFilterReader(in, startElementFilter);
	}
	
	private LWXMLEvent handleAttributeList() throws IOException {
		CharStreamUtil.flushChars(in, WHITESPACE_FILTER);
		int c = fin.read();
		
		switch (c) {
		case '/':
			if (fin.read() != '>')
				throw new LWXMLReaderException("malformed tag: expected '>'");
			handleEndEmptyElement = true;
		case '>':
			handleAttributeList = false;
			eventReader = null;
			return LWXMLEvent.END_ATTRIBUTE_LIST;
		default:
			in.unread(c);
			handleAttributeName();
			return LWXMLEvent.ATTRIBUTE_NAME;
		}
	}
	
	private void handleAttributeName() throws IOException {
		eventReader = new ApplyFilterReader(fin, WHITESPACE_FILTER);
		eventReader = new UntilFilterReader(eventReader, ATTRIBUTE_NAME_FILTER);
	}
	
	// TODO: handle malformed xml
	private void handleAttributeValue() throws IOException {
		CharStreamUtil.flushUntilChar(in, '"');
		eventReader = new UntilFilterReader(fin, ATTRIBUTE_VALUE_FILTER);
	}
	
	private void handleCharacters() throws IOException {
		eventReader = new UntilFilterReader(in, CHARACTERS_FILTER);
	}
	
	@Override
	public int read() throws IOException {
		if (eventReader == null) return -1;
		return eventReader.read();
	}
	
	public void close() throws IOException {
		if (closed) return;
		
		closed = true;
		eventReader = null;
		
		in.close();
	}
	
}