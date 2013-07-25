package at.stefl.commons.network.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

// TODO: improve
public abstract class IPAddress {

    @Override
    public int hashCode() {
	return Arrays.hashCode(toByteArray());
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (obj == this)
	    return true;

	if (!(obj instanceof IPAddress))
	    return false;
	IPAddress address = (IPAddress) obj;

	return Arrays.equals(toByteArray(), address.toByteArray());
    }

    @Override
    public abstract String toString();

    public abstract byte[] toByteArray();

    public InetAddress toInetAddress() {
	try {
	    return InetAddress.getByAddress(toByteArray());
	} catch (UnknownHostException e) {
	    throw new IllegalStateException("Unreachable section!");
	}
    }

}