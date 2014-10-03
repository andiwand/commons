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
    
    public static boolean isPowerOf2(int i) {
		return (i != 0) && ((i & (i - 1)) == 0);
	}
    
    public static byte[] mapDoubleToInteger(double d, int len) {
		byte[] result = new byte[len];
		mapDoubleToInteger(d, result, 0, len);
		return result;
	}
    
	public static void mapDoubleToInteger(double d, byte[] b, int off, int len) {
		BigInteger maxPositive = BigInteger.ZERO.setBit(len << 2).subtract(
				BigInteger.ONE);
		BigInteger value = BigDecimal.valueOf(d)
				.multiply(new BigDecimal(maxPositive)).toBigInteger();
		byte[] bytes = value.toByteArray();
		System.arraycopy(bytes, 0, b, off, Math.min(bytes.length, len));
	}
	
	public static double mapIntegerToDouble(byte[] b) {
		BigInteger maxPositive = BigInteger.ZERO.setBit(b.length << 2)
				.subtract(BigInteger.ONE);
		BigInteger value = new BigInteger(b);
		return new BigDecimal(value).divide(new BigDecimal(maxPositive),
				MathContext.DECIMAL64).doubleValue();
	}
    
    private NumberUtil() {}
    
}