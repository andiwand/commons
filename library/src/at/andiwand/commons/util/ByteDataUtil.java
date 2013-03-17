package at.andiwand.commons.util;

import at.andiwand.commons.io.Endian;


public class ByteDataUtil {
	
	public static char getAsChar(Endian endian, byte[] b) {
		switch (endian) {
		case LITTLE:
			return (char) ((b[0] & 0xff) | ((b[1] & 0xff) << 8));
		case BIG:
			return (char) (((b[0] & 0xff) << 8) | (b[1] & 0xff));
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static char getAsChar(Endian endian, byte[] b, int off) {
		switch (endian) {
		case LITTLE:
			return (char) ((b[off++] & 0xff) | ((b[off] & 0xff) << 8));
		case BIG:
			return (char) (((b[off++] & 0xff) << 8) | (b[off] & 0xff));
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static char getAsChar(Endian endian, byte b1, byte b2) {
		switch (endian) {
		case LITTLE:
			return (char) ((b1 & 0xff) | ((b2 & 0xff) << 8));
		case BIG:
			return (char) (((b1 & 0xff) << 8) | (b2 & 0xff));
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static short getAsShort(Endian endian, byte[] b) {
		switch (endian) {
		case LITTLE:
			return (short) ((b[0] & 0xff) | ((b[1] & 0xff) << 8));
		case BIG:
			return (short) (((b[0] & 0xff) << 8) | (b[1] & 0xff));
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static short getAsShort(Endian endian, byte[] b, int off) {
		switch (endian) {
		case LITTLE:
			return (short) ((b[off++] & 0xff) | ((b[off] & 0xff) << 8));
		case BIG:
			return (short) (((b[off++] & 0xff) << 8) | (b[off] & 0xff));
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static short getAsShort(Endian endian, byte b1, byte b2) {
		switch (endian) {
		case LITTLE:
			return (short) ((b1 & 0xff) | ((b2 & 0xff) << 8));
		case BIG:
			return (short) (((b1 & 0xff) << 8) | (b2 & 0xff));
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static int getAsInt(Endian endian, byte[] b) {
		switch (endian) {
		case LITTLE:
			return (b[0] & 0xff) | ((b[1] & 0xff) << 8) | ((b[2] & 0xff) << 16)
					| ((b[3] & 0xff) << 24);
		case BIG:
			return ((b[0] & 0xff) << 24) | ((b[1] & 0xff) << 16)
					| ((b[2] & 0xff) << 8) | (b[3] & 0xff);
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static int getAsInt(Endian endian, byte[] b, int off) {
		switch (endian) {
		case LITTLE:
			return (b[off++] & 0xff) | ((b[off++] & 0xff) << 8)
					| ((b[off++] & 0xff) << 16) | ((b[off] & 0xff) << 24);
		case BIG:
			return ((b[off++] & 0xff) << 24) | ((b[off++] & 0xff) << 16)
					| ((b[off++] & 0xff) << 8) | (b[off] & 0xff);
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static int getAsInt(Endian endian, byte b1, byte b2, byte b3, byte b4) {
		switch (endian) {
		case LITTLE:
			return (b1 & 0xff) | ((b2 & 0xff) << 8) | ((b3 & 0xff) << 16)
					| ((b4 & 0xff) << 24);
		case BIG:
			return ((b1 & 0xff) << 24) | ((b2 & 0xff) << 16)
					| ((b3 & 0xff) << 8) | (b4 & 0xff);
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static long getAsLong(Endian endian, byte[] b) {
		switch (endian) {
		case LITTLE:
			return (b[0] & 0xffl) | ((b[1] & 0xffl) << 8)
					| ((b[2] & 0xffl) << 16) | ((b[3] & 0xffl) << 24)
					| ((b[4] & 0xffl) << 32) | ((b[5] & 0xffl) << 40)
					| ((b[6] & 0xffl) << 48) | ((b[7] & 0xffl) << 56);
		case BIG:
			return ((b[0] & 0xffl) << 56) | ((b[1] & 0xffl) << 48)
					| ((b[2] & 0xffl) << 40) | ((b[3] & 0xffl) << 32)
					| ((b[4] & 0xffl) << 24) | ((b[5] & 0xffl) << 16)
					| ((b[6] & 0xffl) << 8) | (b[7] & 0xffl);
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static long getAsLong(Endian endian, byte[] b, int off) {
		switch (endian) {
		case LITTLE:
			return (b[0] & 0xffl) | ((b[1] & 0xffl) << 8)
					| ((b[2] & 0xffl) << 16) | ((b[3] & 0xffl) << 24)
					| ((b[4] & 0xffl) << 32) | ((b[5] & 0xffl) << 40)
					| ((b[6] & 0xffl) << 48) | ((b[7] & 0xffl) << 56);
		case BIG:
			return ((b[0] & 0xffl) << 56) | ((b[1] & 0xffl) << 48)
					| ((b[2] & 0xffl) << 40) | ((b[3] & 0xffl) << 32)
					| ((b[4] & 0xffl) << 24) | ((b[5] & 0xffl) << 16)
					| ((b[6] & 0xffl) << 8) | (b[7] & 0xffl);
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static long getAsLong(Endian endian, byte b1, byte b2, byte b3,
			byte b4, byte b5, byte b6, byte b7, byte b8) {
		switch (endian) {
		case LITTLE:
			return (b1 & 0xffl) | ((b2 & 0xffl) << 8) | ((b3 & 0xffl) << 16)
					| ((b4 & 0xffl) << 24) | ((b5 & 0xffl) << 32)
					| ((b6 & 0xffl) << 40) | ((b7 & 0xffl) << 48)
					| ((b8 & 0xffl) << 56);
		case BIG:
			return ((b1 & 0xffl) << 56) | ((b2 & 0xffl) << 48)
					| ((b3 & 0xffl) << 40) | ((b4 & 0xffl) << 32)
					| ((b5 & 0xffl) << 24) | ((b6 & 0xffl) << 16)
					| ((b7 & 0xffl) << 8) | (b8 & 0xffl);
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static byte[] getBytes(Endian endian, char v) {
		byte[] result = new byte[2];
		getBytes(endian, v, result);
		return result;
	}
	
	public static void getBytes(Endian endian, char v, byte[] b) {
		switch (endian) {
		case LITTLE:
			b[0] = (byte) v;
			b[1] = (byte) (v >> 8);
			return;
		case BIG:
			b[0] = (byte) (v >> 8);
			b[1] = (byte) v;
			return;
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static void getBytes(Endian endian, char v, byte[] b, int off) {
		switch (endian) {
		case LITTLE:
			b[off++] = (byte) v;
			b[off] = (byte) (v >> 8);
			return;
		case BIG:
			b[off++] = (byte) (v >> 8);
			b[off] = (byte) v;
			return;
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static byte[] getBytes(Endian endian, short v) {
		byte[] result = new byte[2];
		getBytes(endian, v, result);
		return result;
	}
	
	public static void getBytes(Endian endian, short v, byte[] b) {
		switch (endian) {
		case LITTLE:
			b[0] = (byte) v;
			b[1] = (byte) (v >> 8);
			return;
		case BIG:
			b[0] = (byte) (v >> 8);
			b[1] = (byte) v;
			return;
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static void getBytes(Endian endian, short v, byte[] b, int off) {
		switch (endian) {
		case LITTLE:
			b[off++] = (byte) v;
			b[off] = (byte) (v >> 8);
			return;
		case BIG:
			b[off++] = (byte) (v >> 8);
			b[off] = (byte) v;
			return;
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static byte[] getBytes(Endian endian, int v) {
		byte[] result = new byte[4];
		getBytes(endian, v, result);
		return result;
	}
	
	public static void getBytes(Endian endian, int v, byte[] b) {
		switch (endian) {
		case LITTLE:
			b[0] = (byte) v;
			b[1] = (byte) (v >> 8);
			b[2] = (byte) (v >> 16);
			b[3] = (byte) (v >> 24);
			return;
		case BIG:
			b[0] = (byte) (v >> 24);
			b[1] = (byte) (v >> 16);
			b[2] = (byte) (v >> 8);
			b[3] = (byte) v;
			return;
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static void getBytes(Endian endian, int v, byte[] b, int off) {
		switch (endian) {
		case LITTLE:
			b[off++] = (byte) v;
			b[off++] = (byte) (v >> 8);
			b[off++] = (byte) (v >> 16);
			b[off] = (byte) (v >> 24);
			return;
		case BIG:
			b[off++] = (byte) (v >> 24);
			b[off++] = (byte) (v >> 16);
			b[off++] = (byte) (v >> 8);
			b[off] = (byte) v;
			return;
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static byte[] getBytes(Endian endian, long v) {
		byte[] result = new byte[8];
		getBytes(endian, v, result);
		return result;
	}
	
	public static void getBytes(Endian endian, long v, byte[] b) {
		switch (endian) {
		case LITTLE:
			b[0] = (byte) v;
			b[1] = (byte) (v >> 8);
			b[2] = (byte) (v >> 16);
			b[3] = (byte) (v >> 24);
			b[4] = (byte) (v >> 32);
			b[5] = (byte) (v >> 40);
			b[6] = (byte) (v >> 48);
			b[7] = (byte) (v >> 56);
			return;
		case BIG:
			b[0] = (byte) (v >> 56);
			b[1] = (byte) (v >> 48);
			b[2] = (byte) (v >> 40);
			b[3] = (byte) (v >> 32);
			b[4] = (byte) (v >> 24);
			b[5] = (byte) (v >> 16);
			b[6] = (byte) (v >> 8);
			b[7] = (byte) v;
			return;
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	public static void getBytes(Endian endian, long v, byte[] b, int off) {
		switch (endian) {
		case LITTLE:
			b[off++] = (byte) v;
			b[off++] = (byte) (v >> 8);
			b[off++] = (byte) (v >> 16);
			b[off++] = (byte) (v >> 24);
			b[off++] = (byte) (v >> 32);
			b[off++] = (byte) (v >> 40);
			b[off++] = (byte) (v >> 48);
			b[off] = (byte) (v >> 56);
			return;
		case BIG:
			b[off++] = (byte) (v >> 56);
			b[off++] = (byte) (v >> 48);
			b[off++] = (byte) (v >> 40);
			b[off++] = (byte) (v >> 32);
			b[off++] = (byte) (v >> 24);
			b[off++] = (byte) (v >> 16);
			b[off++] = (byte) (v >> 8);
			b[off] = (byte) v;
			return;
		default:
			throw new InaccessibleSectionException();
		}
	}
	
	private ByteDataUtil() {}
	
}