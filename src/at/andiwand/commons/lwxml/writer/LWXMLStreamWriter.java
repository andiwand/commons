package at.andiwand.commons.lwxml.writer;

import java.io.IOException;
import java.io.Writer;

import at.andiwand.commons.lwxml.LWXMLEvent;
import at.andiwand.commons.lwxml.LWXMLIllegalEventException;


public class LWXMLStreamWriter extends LWXMLWriter {
	
	private boolean closed;
	private Writer out;
	
	private LWXMLEvent lastEvent;
	private boolean eventWritten;
	
	public LWXMLStreamWriter(Writer out) {
		this.out = out;
	}
	
	@Override
	public LWXMLEvent getCurrentEvent() {
		return lastEvent;
	}
	
	@Override
	public boolean isCurrentEventWritten() {
		return eventWritten;
	}
	
	private void finishLastEvent() throws IOException {
		finishLastEvent(null);
	}
	
	private void finishLastEvent(LWXMLEvent nextEvent) throws IOException {
		if (lastEvent == null) return;
		if (eventWritten) return;
		
		switch (lastEvent) {
		case PROCESSING_INSTRUCTION_DATA:
			out.write("?>");
			break;
		case COMMENT:
			out.write("-->");
			break;
		case END_ELEMENT:
			out.write(">");
			break;
		case ATTRIBUTE_VALUE:
			out.write("\"");
			break;
		case END_ATTRIBUTE_LIST:
			if ((nextEvent != null)
					&& (nextEvent != LWXMLEvent.END_EMPTY_ELEMENT))
				out.write(">");
			break;
		case CDATA:
			out.write("]]>");
			break;
		}
		
		eventWritten = true;
	}
	
	public void writeEvent(LWXMLEvent event) throws IOException {
		if (closed) throw new LWXMLWriterException("already closed");
		
		if (event == null) throw new NullPointerException();
		if (event == LWXMLEvent.END_DOCUMENT)
			throw new LWXMLWriterException("cannot write event (" + event + ")");
		
		if ((lastEvent != null) && !lastEvent.isFollowingEvent(event))
			throw new LWXMLWriterException("given event (" + event
					+ ") cannot follow last event (" + lastEvent + ")");
		
		finishLastEvent(event);
		
		eventWritten = false;
		
		switch (event) {
		case PROCESSING_INSTRUCTION_TARGET:
			out.write("<?");
			break;
		case PROCESSING_INSTRUCTION_DATA:
			out.write(" ");
			break;
		case COMMENT:
			out.write("<!--");
			break;
		case START_ELEMENT:
			out.write("<");
			break;
		case END_EMPTY_ELEMENT:
			out.write("/>");
			eventWritten = true;
			break;
		case END_ELEMENT:
			out.write("</");
			break;
		case ATTRIBUTE_NAME:
			out.write(" ");
			break;
		case ATTRIBUTE_VALUE:
			out.write("=\"");
			break;
		case END_ATTRIBUTE_LIST:
			break;
		case CHARACTERS:
			break;
		case CDATA:
			out.write("<![CDATA[");
			break;
		default:
			throw new LWXMLIllegalEventException(event);
		}
		
		lastEvent = event;
	}
	
	@Override
	public void writeEvent(LWXMLEvent event, String value) throws IOException {
		if (!event.hasValue()) throw new LWXMLIllegalEventException(event);
		if (value == null) throw new NullPointerException();
		
		writeEvent(event);
		out.write(value);
		finishLastEvent();
	}
	
	private void checkWrite() {
		if (closed) throw new LWXMLWriterException("already closed");
		if (lastEvent == null)
			throw new LWXMLWriterException("no current event");
		if (!lastEvent.hasValue())
			throw new LWXMLWriterException("current event has no value");
		if (eventWritten)
			throw new LWXMLWriterException("value already written");
	}
	
	@Override
	public void write(int c) throws IOException {
		checkWrite();
		out.write(c);
	}
	
	@Override
	public void write(char[] cbuf) throws IOException {
		checkWrite();
		out.write(cbuf);
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		checkWrite();
		out.write(cbuf, off, len);
	}
	
	@Override
	public void write(String str) throws IOException {
		checkWrite();
		out.write(str);
	}
	
	@Override
	public void write(String str, int off, int len) throws IOException {
		checkWrite();
		out.write(str, off, len);
	}
	
	@Override
	public LWXMLWriter append(char c) throws IOException {
		checkWrite();
		out.append(c);
		return this;
	}
	
	@Override
	public LWXMLWriter append(CharSequence csq) throws IOException {
		checkWrite();
		out.append(csq);
		return this;
	}
	
	@Override
	public LWXMLWriter append(CharSequence csq, int start, int end)
			throws IOException {
		checkWrite();
		out.append(csq, start, end);
		return this;
	}
	
	@Override
	public void flush() throws IOException {
		if (closed) return;
		
		finishLastEvent();
		out.flush();
	}
	
	@Override
	public void close() throws IOException {
		if (closed) return;
		
		try {
			flush();
			out.close();
		} finally {
			closed = true;
		}
	}
	
}