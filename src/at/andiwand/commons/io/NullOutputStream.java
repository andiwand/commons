package at.andiwand.commons.io;

import java.io.OutputStream;

public class NullOutputStream extends OutputStream {

    public static final NullOutputStream NULL = new NullOutputStream();

    protected NullOutputStream() {
    }

    @Override
    public void write(int b) {
    }

    @Override
    public void write(byte[] b) {
    }

    @Override
    public void write(byte[] b, int off, int len) {
    }

}