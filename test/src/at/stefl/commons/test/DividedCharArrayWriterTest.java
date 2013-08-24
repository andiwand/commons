package at.stefl.commons.test;

import java.io.Reader;

import at.stefl.commons.io.CharStreamUtil;
import at.stefl.commons.io.DividedCharArrayWriter;

public class DividedCharArrayWriterTest {
    
    public static void main(String[] args) throws Throwable {
        DividedCharArrayWriter out = new DividedCharArrayWriter();
        
        out.write("HAAAAAAAAAAAAAAAAAAAAAAAAAAAALLLLLOOOOOO");
        
        Reader in = out.getReader();
        System.out.println(CharStreamUtil.readString(in));
        
        out.reset();
        try {
            System.out.println(in.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        out.close();
    }
    
}