package at.stefl.commons.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class NumberUtil {
    
    public static byte parseByte(String value, byte nullValue) {
        return (value == null) ? nullValue : Byte.parseByte(value);
    }
    
    public static short parseShort(String value, short nullValue) {
        return (value == null) ? nullValue : Short.parseShort(value);
    }
    
    public static int parseInt(String value, int nullValue) {
        return (value == null) ? nullValue : Integer.parseInt(value);
    }
    
    public static long parseLong(String value, long nullValue) {
        return (value == null) ? nullValue : Long.parseLong(value);
    }
    
    public static float parseFloat(String value, float nullValue) {
        return (value == null) ? nullValue : Float.parseFloat(value);
    }
    
    public static double parseDouble(String value, double nullValue) {
        return (value == null) ? nullValue : Double.parseDouble(value);
    }
    
    public static boolean isValidUnsignedByte(int value) {
        return (value & 0xff00) == 0;
    }
    
    public static boolean isValidUnsignedShort(int value) {
        return (value & 0xffff0000) == 0;
    }
    
    public static boolean isValidUnsignedInt(long value) {
        return (value & 0xffffffff00000000l) == 0;
    }
    
    public static boolean isPowerOf2(int v) {
		return (v != 0) && ((v & (v - 1)) == 0);
	}
    
    public static boolean isPowerOf2(long v) {
		return (v != 0) && ((v & (v - 1)) == 0);
	}
    
    public static byte[] mapDoubleToInteger(double d, int len) {
		byte[] result = new byte[len];
		mapDoubleToInteger(d, result, 0, len);
		return result;
	}
    
    public static void mapDoubleToInteger(double d, byte[] b) {
    	mapDoubleToInteger(d, b, 0, b.length);
    }
    
    // TODO: maybe fix positive mapping
	public static void mapDoubleToInteger(double d, byte[] b, int off, int len) {
		BigInteger maxPositive = BigInteger.ZERO.setBit((b.length << 3) - 1);
		BigInteger value = BigDecimal.valueOf(d)
				.multiply(new BigDecimal(maxPositive)).toBigInteger();
		byte[] bytes = value.toByteArray();
		System.arraycopy(bytes, 0, b, off, Math.min(bytes.length, len));
	}
	
	public static double mapIntegerToDouble(byte[] i) {
		BigInteger maxPositive = BigInteger.ZERO.setBit((i.length << 3) - 1);
		BigInteger value = new BigInteger(i);
		return new BigDecimal(value).divide(new BigDecimal(maxPositive),
				MathContext.DECIMAL64).doubleValue();
	}
	
	public static double mapIntegerToDouble(byte i) {
		return (double) i / Byte.MAX_VALUE;
	}
	
	public static double mapIntegerToDouble(short i) {
		return (double) i / Short.MAX_VALUE;
	}
	
	public static double mapIntegerToDouble(int i) {
		return (double) i / Integer.MAX_VALUE;
	}
	
	public static double mapIntegerToDouble(long i) {
		return (double) i / Long.MAX_VALUE;
	}
    
    private NumberUtil() {}
    
}