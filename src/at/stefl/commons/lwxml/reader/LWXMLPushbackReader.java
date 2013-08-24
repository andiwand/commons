package at.stefl.commons.lwxml.reader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.util.LinkedList;

import at.stefl.commons.io.ClosedReader;
import at.stefl.commons.io.CountingReader;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.LWXMLIllegalFollowerException;

// TODO: improve exception handling
public class LWXMLPushbackReader extends LWXMLFilterReader {
    
    // removed Deque because of Android 1.6
    // private Deque<LWXMLEvent> eventStack = new LinkedList<LWXMLEvent>();
    // private Deque<String> valueStack = new LinkedList<String>();
    private LinkedList<LWXMLEvent> eventStack = new LinkedList<LWXMLEvent>();
    private LinkedList<String> valueStack = new LinkedList<String>();
    
    private LWXMLEvent currentEvent;
    private String currentValue;
    private Reader valueReader;
    private CountingReader countingReader;
    
    private boolean streamReading;
    
    public LWXMLPushbackReader(LWXMLReader in) {
        super(in);
    }
    
    @Override
    public LWXMLEvent getCurrentEvent() {
        return currentEvent;
    }
    
    @Override
    public LWXMLEvent readEvent() throws IOException {
        streamReading = eventStack.size() == 0;
        
        if (streamReading) {
            currentValue = null;
            countingReader = null;
            
            LWXMLEvent tmp = in.readEvent();
            if ((currentEvent != null) && !currentEvent.isFollowingEvent(tmp)) throw new LWXMLIllegalFollowerException(
                    currentEvent, tmp);
            
            currentEvent = tmp;
            valueReader = in;
        } else {
            currentEvent = eventStack.removeFirst();
            
            if (currentEvent.hasValue()) {
                currentValue = valueStack.removeFirst();
                
                if (currentValue != null) {
                    valueReader = new StringReader(currentValue);
                    countingReader = new CountingReader(valueReader);
                    valueReader = countingReader;
                } else {
                    valueReader = in;
                    streamReading = true;
                }
            } else {
                valueReader = ClosedReader.CLOSED_READER;
            }
        }
        
        return currentEvent;
    }
    
    public void unreadEvent() {
        eventStack.addFirst(currentEvent);
        if (currentEvent.hasValue()) valueStack.addFirst(null);
    }
    
    public void unreadEvent(LWXMLEvent event) {
        unreadEvent(event, null);
    }
    
    public void unreadEvent(String value) {
        unreadEvent(currentEvent, value);
    }
    
    public void unreadEvent(LWXMLEvent event, String value) {
        if (event.hasValue() && (value == null)) throw new IllegalArgumentException(
                "value necessary");
        if ((eventStack.size() != 0)
                && !event.isFollowingEvent(eventStack.getFirst())) throw new LWXMLIllegalFollowerException(
                eventStack.getFirst(), event);
        
        eventStack.addFirst(event);
        if (event.hasValue()) valueStack.addFirst(value);
    }
    
    public LWXMLEvent touchEvent() throws IOException {
        LWXMLEvent result = readEvent();
        unreadEvent();
        return result;
    }
    
    @Override
    public String readValue() throws IOException {
        if (streamReading) return in.readValue();
        
        if (!LWXMLEvent.hasValue(currentEvent)) return null;
        return currentValue.substring((int) countingReader.count());
    }
    
    @Override
    public int read() throws IOException {
        return valueReader.read();
    }
    
    @Override
    public int read(char[] cbuf) throws IOException {
        return valueReader.read(cbuf);
    }
    
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return valueReader.read(cbuf, off, len);
    }
    
    @Override
    public int read(CharBuffer target) throws IOException {
        return valueReader.read(target);
    }
    
    @Override
    public long skip(long n) throws IOException {
        return valueReader.skip(n);
    }
    
}