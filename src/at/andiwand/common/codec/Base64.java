package at.andiwand.common.codec;

public class Base64 {
	
	public static final char[] STANDARD_ENCODE_TABLE = {'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '+', '/'};
	
	public static final char[] URL_SAFE_ENCODE_TABLE = {'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '-', '_'};
	
	public static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1,
			-1, -1, 0, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
			13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1,
			63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
			41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
	
	public static final char PADDING_CHAR = '=';
	
	public static void encode3Byte(byte[] in, char[] out, char[] table) {
		encode3Byte(in, 0, Math.min(3, in.length), out, 0, table);
	}
	
	public static void encode3Byte(byte[] in, int len, char[] out, char[] table) {
		encode3Byte(in, 0, len, out, 0, table);
	}
	
	public static void encode3Byte(byte[] in, int inOff, int len, char[] out,
			int outOff, char[] table) {
		int code = 0;
		
		code |= (in[inOff++] & 0xff) << 16;
		if (len > 1) code |= (in[inOff++] & 0xff) << 8;
		if (len > 2) code |= (in[inOff] & 0xff) << 0;
		
		encodeInt(code, len, out, outOff, table);
	}
	
	public static void encodeInt(int code, int len, char[] out, int off,
			char[] table) {
		out[off++] = table[(code >> 18) & 0x3f];
		out[off++] = table[(code >> 12) & 0x3f];
		out[off++] = (len > 1) ? table[(code >> 6) & 0x3f] : PADDING_CHAR;
		out[off] = (len > 2) ? table[(code >> 0) & 0x3f] : PADDING_CHAR;
	}
	
	public static String encodeBytesAsString(byte[] in) {
		return encodeBytesAsString(in, 0, in.length);
	}
	
	public static String encodeBytesAsString(byte[] in, int off, int len) {
		return encodeBytesAsString(in, off, len, STANDARD_ENCODE_TABLE);
	}
	
	public static String encodeBytesAsUrlSaveString(byte[] in) {
		return encodeBytesAsUrlSaveString(in, 0, in.length);
	}
	
	public static String encodeBytesAsUrlSaveString(byte[] in, int off, int len) {
		return encodeBytesAsString(in, off, len, URL_SAFE_ENCODE_TABLE);
	}
	
	public static String encodeBytesAsString(byte[] in, int off, int len,
			char[] table) {
		return new String(encodeBytes(in, off, len, table));
	}
	
	public static char[] encodeBytes(byte[] in, int off, int len, char[] table) {
		int size = (len / 3) * 4;
		if ((len % 3) > 0) size += 4;
		char[] result = new char[size];
		encode3Byte(in, off, len, result, 0, table);
		return result;
	}
	
	public static void encodeBytes(byte[] in, int inOff, int len, char[] out,
			int outOff, char[] table) {
		int end = inOff + len;
		
		for (int i = inOff, o = outOff; i < end; i += 3, o += 4) {
			encode3Byte(in, i, Math.min(3, end - i), out, o, table);
		}
	}
	
	public static boolean isDecodeableChar(char c) {
		if (DECODE_TABLE[c] != -1) return true;
		return false;
	}
	
	public static byte decode1Char(char c) {
		byte result = DECODE_TABLE[c];
		if (result == -1) throw new IllegalArgumentException();
		return result;
	}
	
	public static int decode3Byte(char[] in, byte[] out) {
		return decode3Byte(in, 0, out, 0);
	}
	
	public static int decode3Byte(char[] in, int inOff, byte[] out, int outOff) {
		if (in[inOff] == PADDING_CHAR) throw new IllegalStateException();
		
		int code = 0;
		int len = 4;
		
		code |= (decode1Char(in[inOff++]) & 0x3f) << 18;
		if (in[inOff] == PADDING_CHAR) throw new IllegalStateException();
		code |= (decode1Char(in[inOff++]) & 0x3f) << 12;
		if (in[inOff] == PADDING_CHAR) len--;
		code |= (decode1Char(in[inOff++]) & 0x3f) << 6;
		if (in[inOff] == PADDING_CHAR) len--;
		else if (len < 4) throw new IllegalStateException();
		code |= (decode1Char(in[inOff]) & 0x3f) << 0;
		
		return decodeInt(code, len, out, outOff);
	}
	
	public static int decode3Byte(CharSequence in, int inOff, byte[] out,
			int outOff) {
		if (in.charAt(inOff) == PADDING_CHAR)
			throw new IllegalStateException();
		
		int code = 0;
		int len = 4;
		
		code |= (decode1Char(in.charAt(inOff++)) & 0x3f) << 18;
		if (in.charAt(inOff) == PADDING_CHAR)
			throw new IllegalStateException();
		code |= (decode1Char(in.charAt(inOff++)) & 0x3f) << 12;
		if (in.charAt(inOff) == PADDING_CHAR) len--;
		code |= (decode1Char(in.charAt(inOff++)) & 0x3f) << 6;
		if (in.charAt(inOff) == PADDING_CHAR) len--;
		else if (len < 4) throw new IllegalStateException();
		code |= (decode1Char(in.charAt(inOff)) & 0x3f) << 0;
		
		return decodeInt(code, len, out, outOff);
	}
	
	public static int decodeInt(int code, int len, byte[] out, int off) {
		out[off++] = (byte) ((code >> 16) & 0xff);
		if (len > 2) out[off++] = (byte) ((code >> 8) & 0xff);
		if (len > 3) out[off] = (byte) ((code >> 0) & 0xff);
		
		return len - 1;
	}
	
	public static byte[] decodeChars(char[] in) {
		return decodeChars(in, 0, in.length);
	}
	
	public static byte[] decodeChars(char[] in, int inOff, int len) {
		int size = (len / 4) * 3;
		
		if (in[len - 1] == PADDING_CHAR) {
			size--;
			if (in[len - 2] == PADDING_CHAR) size--;
		}
		
		byte[] result = new byte[size];
		decodeChars(in, inOff, len, result, 0);
		return result;
	}
	
	public static void decodeChars(char[] in, int inOff, int len, byte[] out,
			int outOff) {
		int end = inOff + len;
		
		for (int i = inOff, o = outOff; i < end; i += 4, o += 3) {
			decode3Byte(in, i, out, o);
		}
	}
	
	public static byte[] decodeChars(CharSequence in) {
		return decodeChars(in, 0, in.length());
	}
	
	public static byte[] decodeChars(CharSequence in, int inOff, int len) {
		int size = (len / 4) * 3;
		
		if (in.charAt(len - 1) == PADDING_CHAR) {
			size--;
			if (in.charAt(len - 2) == PADDING_CHAR) size--;
		}
		
		byte[] result = new byte[size];
		decodeChars(in, inOff, len, result, 0);
		return result;
	}
	
	public static void decodeChars(CharSequence in, int inOff, int len,
			byte[] out, int outOff) {
		int end = inOff + len;
		
		for (int i = inOff, o = outOff; i < end; i += 4, o += 3) {
			decode3Byte(in, i, out, o);
		}
	}
	
	private Base64() {}
	
}