package at.andiwand.commons.test;

import at.andiwand.commons.network.ip.IPv4Address;

public class IPv4AddressTest {

    public static void main(String[] args) {
	IPv4Address address = new IPv4Address("192.168.255.254");
	System.out.println(address);
	System.out.println(address.toInetAddress());
    }

}