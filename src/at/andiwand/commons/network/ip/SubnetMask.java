package at.andiwand.commons.network.ip;

// TODO: improve
public class SubnetMask {

    private static final int PREFIX_MIN = 0;
    private static final int PREFIX_MAX = 32;

    public static final SubnetMask EMPTY = new SubnetMask(PREFIX_MIN);
    public static final SubnetMask FULL = new SubnetMask(PREFIX_MAX);

    private final int prefix;

    public SubnetMask(int prefix) {
	if ((prefix < PREFIX_MIN) || (prefix > PREFIX_MAX))
	    throw new IllegalArgumentException("Illegal prefix!");

	this.prefix = prefix;
    }

    public SubnetMask(IPv4Address address) {
	long binary = address.toInt() & 0xffffffffl;
	binary = ~binary & 0xffffffffl;
	binary++;

	int bitCount = Long.bitCount(binary);
	if (bitCount != 1)
	    throw new IllegalArgumentException("Illegal address!");

	prefix = Long.numberOfLeadingZeros(binary) - 31;
    }

    public SubnetMask(String address) {
	this(new IPv4Address(address));
    }

    @Override
    public String toString() {
	return toPrefixString();
    }

    public String toPrefixString() {
	return "/" + prefix;
    }

    public String toDottedString() {
	return toIPv4Address().toDottedString();
    }

    public int getPrefix() {
	return prefix;
    }

    public int toInt() {
	return (int) ~((1l << (PREFIX_MAX - prefix)) - 1);
    }

    public IPv4Address toIPv4Address() {
	return new IPv4Address(toInt());
    }

}