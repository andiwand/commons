package at.stefl.commons.lwxml.writer;

import java.io.IOException;

import at.stefl.commons.io.CharStreamUtil;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.LWXMLIllegalEventException;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.util.array.ArrayUtil;
import at.stefl.commons.util.string.CharSequenceUtil;

// TODO: remove and implement a better solution (-> GrowableArray)
public class LWXMLEventQueueWriter extends LWXMLWriter {

    private static final LWXMLEvent[] EVENT_ARRAY = LWXMLEvent.values();

    private static final double CHAR_PER_EVENT = 4;

    public static final int INITIAL_EVENT_SIZE = 10;
    public static final int INITIAL_VALUE_SIZE = 4;
    public static final int INITIAL_CHAR_SIZE = (int) (INITIAL_VALUE_SIZE * CHAR_PER_EVENT);

    private int[] eventArray = new int[INITIAL_EVENT_SIZE];
    private int[] valueArray = new int[INITIAL_EVENT_SIZE];
    private int[] offsetArray = new int[INITIAL_VALUE_SIZE];
    private int[] lengthArray = new int[INITIAL_VALUE_SIZE];
    private char[] charArray = new char[INITIAL_CHAR_SIZE];

    private int eventCount;
    private int valueCount;
    private int charCount;

    private LWXMLEvent lastEvent;
    private boolean eventWritten;

    private void ensureEventSize(int usage) {
	eventArray = (int[]) ArrayUtil.growGeometric(eventArray, eventCount
		+ usage, 2);
	valueArray = (int[]) ArrayUtil.growGeometric(valueArray, eventCount
		+ usage, 2);
    }

    private void ensureValueSize(int usage) {
	offsetArray = (int[]) ArrayUtil.growGeometric(offsetArray, valueCount
		+ usage, 2);
	lengthArray = (int[]) ArrayUtil.growGeometric(lengthArray, valueCount
		+ usage, 2);
    }

    private void ensureCharSize(int usage) {
	charArray = (char[]) ArrayUtil.growGeometric(charArray, charCount
		+ usage, 2);
    }

    @Override
    public LWXMLEvent getCurrentEvent() {
	return lastEvent;
    }

    @Override
    public boolean isCurrentEventWritten() {
	return eventWritten;
    }

    public int getEventCount() {
	return eventCount;
    }

    public int getValueCount() {
	return valueCount;
    }

    public int getCharCount() {
	return charCount;
    }

    private void finishLastEvent() {
	if (lastEvent == null)
	    return;
	if (eventWritten)
	    return;

	if (lastEvent.hasValue()) {
	    lengthArray[valueCount] = charCount - offsetArray[valueCount];
	    valueCount++;
	}

	eventCount++;
	eventWritten = true;
    }

    @Override
    public void writeEvent(LWXMLEvent event) {
	if (event == null)
	    throw new NullPointerException();
	if (event == LWXMLEvent.END_DOCUMENT)
	    throw new LWXMLWriterException("cannot write event (" + event + ")");

	if ((lastEvent != null) && !lastEvent.isFollowingEvent(event))
	    throw new LWXMLWriterException("given event (" + event
		    + ") cannot follow last event (" + lastEvent + ")");

	finishLastEvent();

	ensureEventSize(1);
	eventArray[eventCount] = event.ordinal();
	valueArray[eventCount] = event.hasValue() ? valueCount : -1;

	if (event.hasValue()) {
	    ensureValueSize(1);
	    offsetArray[valueCount] = charCount;
	}

	lastEvent = event;
	eventWritten = false;
    }

    @Override
    public void writeEvent(LWXMLEvent event, String value) {
	if (!event.hasValue())
	    throw new LWXMLIllegalEventException(event);
	if (value == null)
	    throw new NullPointerException();

	writeEvent(event);
	write(value);
	finishLastEvent();
    }

    private void checkWrite(int len) {
	if (lastEvent == null)
	    throw new LWXMLWriterException("no current event");
	if (!lastEvent.hasValue())
	    throw new LWXMLWriterException("current event has no value");
	if (eventWritten)
	    throw new LWXMLWriterException("value is already written");

	ensureCharSize(len);
    }

    @Override
    public void write(int c) {
	checkWrite(1);
	charArray[charCount] = (char) c;
	charCount++;
    }

    @Override
    public void write(char[] cbuf) {
	write(cbuf, 0, cbuf.length);
    }

    @Override
    public void write(char[] cbuf, int off, int len) {
	checkWrite(len);
	System.arraycopy(cbuf, off, charArray, charCount, len);
	charCount += len;
    }

    @Override
    public void write(String str) {
	write(str, 0, str.length());
    }

    @Override
    public void write(String str, int off, int len) {
	checkWrite(len);
	str.getChars(off, off + len, charArray, charCount);
	charCount += len;
    }

    @Override
    public LWXMLEventQueueWriter append(char c) {
	write(c);
	return this;
    }

    @Override
    public LWXMLEventQueueWriter append(CharSequence csq) {
	return append(csq, 0, csq.length());
    }

    @Override
    public LWXMLEventQueueWriter append(CharSequence csq, int start, int end) {
	int length = end - start;
	checkWrite(length);
	CharSequenceUtil.copy(csq, charArray, start, end);
	charCount += length;
	return this;
    }

    public void write(LWXMLReader in) throws IOException {
	CharStreamUtil streamUtil = new CharStreamUtil();

	while (true) {
	    LWXMLEvent event = in.readEvent();
	    if (event == LWXMLEvent.END_DOCUMENT)
		return;

	    writeEvent(event);
	    streamUtil.writeStream(in, this);
	}
    }

    public void writeTo(LWXMLWriter out) throws IOException {
	finishLastEvent();

	for (int i = 0; i < eventCount; i++) {
	    LWXMLEvent event = EVENT_ARRAY[eventArray[i]];
	    out.writeEvent(event);

	    if (event.hasValue()) {
		int value = valueArray[i];
		out.write(charArray, offsetArray[value], lengthArray[value]);
	    }
	}
    }

    public void reset() {
	eventCount = 0;
	valueCount = 0;
	charCount = 0;

	lastEvent = null;
	eventWritten = false;
    }

    @Override
    public void flush() {
	finishLastEvent();
    }

    @Override
    public void close() {
	flush();
    }

}