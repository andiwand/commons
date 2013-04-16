package at.andiwand.commons.lwxml.writer;

import java.io.IOException;
import java.util.Iterator;

import at.andiwand.commons.lwxml.LWXMLEvent;

public class LWXMLMultiWriter extends LWXMLWriter implements
	Iterable<LWXMLWriter> {

    private final Iterable<LWXMLWriter> iterable;

    public LWXMLMultiWriter(Iterable<LWXMLWriter> iterable) {
	this.iterable = iterable;
    }

    @Override
    public Iterator<LWXMLWriter> iterator() {
	return iterable.iterator();
    }

    @Override
    public LWXMLEvent getCurrentEvent() {
	return iterable.iterator().next().getCurrentEvent();
    }

    @Override
    public boolean isCurrentEventWritten() {
	return iterable.iterator().next().isCurrentEventWritten();
    }

    @Override
    public void writeEvent(LWXMLEvent event) throws IOException {
	for (LWXMLWriter writer : iterable) {
	    writer.writeEvent(event);
	}
    }

    @Override
    public void writeEvent(LWXMLEvent event, String value) throws IOException {
	for (LWXMLWriter writer : iterable) {
	    writer.writeEvent(event, value);
	}
    }

    @Override
    public void write(int c) throws IOException {
	for (LWXMLWriter writer : iterable) {
	    writer.write(c);
	}
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
	for (LWXMLWriter writer : iterable) {
	    writer.write(cbuf, off, len);
	}
    }

    @Override
    public void flush() throws IOException {
	for (LWXMLWriter writer : iterable) {
	    writer.flush();
	}
    }

    @Override
    public void close() throws IOException {
	for (LWXMLWriter writer : iterable) {
	    writer.close();
	}
    }

}