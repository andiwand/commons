package at.andiwand.commons.util;

import at.andiwand.commons.io.Endian;


public class DataUtil {
	
	public static int getAsInt(byte[] b, Endian endian) {
		return getAsInt(b, 0, endian);
	}
	
	public static int getAsInt(byte[] b, int off, Endian endian) {
		switch (endian) {
		case BIG:
			return (b[off++]) | (b[off++]) | (b[off++]) | (b[off]);
		case LITTLE:
			return 0;
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	//	public static int getAsInt(byte b1, byte b2, byte b3, byte b4,
	//			Endian endianness) {
	//		
	//	}
	
	private DataUtil() {}
	
}