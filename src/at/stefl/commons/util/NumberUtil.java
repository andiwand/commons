package at.stefl.commons.util;

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

    private NumberUtil() {
    }

}