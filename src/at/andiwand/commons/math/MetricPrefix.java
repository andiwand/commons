package at.andiwand.commons.math;

import java.math.BigDecimal;
import java.util.Map;

import at.andiwand.commons.util.ArrayUtil;
import at.andiwand.commons.util.collection.KeyGenerator;


public enum MetricPrefix implements UnitPrefix {
	
	YOTTA(24, "Y"),
	ZETTA(21, "Z"),
	EXA(18, "E"),
	PETA(15, "P"),
	TERA(12, "T"),
	GIGA(9, "G"),
	MEGA(6, "M"),
	KILO(3, "k"),
	HECTO(2, "h"),
	DECA(1, "da"),
	DECI(-1, "d"),
	CENTI(-2, "c"),
	MILLI(-3, "m"),
	MICRO(-6, "µ"),
	NANO(-9, "n"),
	PICO(-12, "p"),
	FEMTO(-15, "f"),
	ATTO(-18, "a"),
	ZEPTO(-21, "z"),
	YOCTO(-24, "y");
	
	private static final KeyGenerator<String, MetricPrefix> KEY_GENERATOR = new KeyGenerator<String, MetricPrefix>() {
		@Override
		public String generateKey(MetricPrefix value) {
			return value.symbol;
		}
	};
	
	private static final Map<String, MetricPrefix> SYMBOL_MAP;
	
	static {
		SYMBOL_MAP = ArrayUtil.toHashMap(KEY_GENERATOR, values());
	}
	
	public static MetricPrefix getBySymbol(String symbol) {
		return SYMBOL_MAP.get(symbol);
	}
	
	private final String name;
	private final int exponent;
	private final double value;
	private final BigDecimal bigValue;
	private final String symbol;
	
	private MetricPrefix(int exponent, String symbol) {
		this.name = super.toString().toLowerCase();
		this.exponent = exponent;
		this.value = Math.pow(10, exponent);
		this.bigValue = BigDecimal.TEN.pow(exponent);
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public int exponent() {
		return exponent;
	}
	
	@Override
	public double value() {
		return value;
	}
	
	@Override
	public BigDecimal bigValue() {
		return bigValue;
	}
	
	@Override
	public String symbol() {
		return symbol;
	}
	
}