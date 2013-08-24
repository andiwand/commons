package at.stefl.commons.lwxml.writer;

import java.io.IOException;
import java.io.Reader;

import at.stefl.commons.io.TeeReader;
import at.stefl.commons.lwxml.LWXMLEvent;

// TODO: implement specific methods?
public class LWXMLTeeWriter extends LWXMLFilterWriter {
    
    private final LWXMLWriter tee;
    
    public LWXMLTeeWriter(LWXMLWriter out, LWXMLWriter tee) {
        super(out);
        
        this.tee = tee;
    }
    
    @Override
    public void writeEvent(LWXMLEvent event) throws IOException {
        out.writeEvent(event);
        tee.writeEvent(event);
    }
    
    @Override
    public void writeEvent(LWXMLEvent event, String value) throws IOException {
        out.writeEvent(event, value);
        tee.writeEvent(event, value);
    }
    
    @Override
    public void write(int c) throws IOException {
        out.write(c);
        tee.write(c);
    }
    
    @Override
    public void write(char[] cbuf) throws IOException {
        out.write(cbuf);
        tee.write(cbuf);
    }
    
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(cbuf, off, len);
        tee.write(cbuf, off, len);
    }
    
    @Override
    public int write(Reader in) throws IOException {
        return out.write(new TeeReader(in, tee));
    }
    
    @Override
    public void write(String str) throws IOException {
        out.write(str);
        tee.write(str);
    }
    
    @Override
    public void write(String str, int off, int len) throws IOException {
        out.write(str, off, len);
        tee.write(str, off, len);
    }
    
    @Override
    public LWXMLWriter append(char c) throws IOException {
        out.append(c);
        tee.append(c);
        return this;
    }
    
    @Override
    public LWXMLWriter append(CharSequence csq) throws IOException {
        out.append(csq);
        tee.append(csq);
        return this;
    }
    
    @Override
    public LWXMLWriter append(CharSequence csq, int start, int end)
            throws IOException {
        out.append(csq, start, end);
        tee.append(csq, start, end);
        return this;
    }
    
    @Override
    public void flush() throws IOException {
        out.flush();
        tee.flush();
    }
    
}