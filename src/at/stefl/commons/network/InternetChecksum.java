package at.stefl.commons.network;

public class InternetChecksum {
    
    public static short calculateChecksum(byte[] buffer) {
        return calculateChecksum(buffer, 0, buffer.length);
    }
    
    public static short calculateChecksum(byte[] buffer, int len) {
        return calculateChecksum(buffer, 0, len);
    }
    
    public static short calculateChecksum(byte[] buffer, int off, int len) {
        if ((len & 0x01) != 0) throw new IllegalArgumentException(
                "illegal length");
        
        int result = 0;
        int end = off + len;
        
        for (int i = off; i < end;) {
            result += ((buffer[i++] & 0xff) << 8) | ((buffer[i++] & 0xff) << 0);
        }
        
        result = ((result >> 0) & 0xffff) + ((result >> 16) & 0xffff);
        
        return (short) ~result;
    }
    
    private InternetChecksum() {}
    
}