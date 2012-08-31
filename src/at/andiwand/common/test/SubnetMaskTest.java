package at.andiwand.common.test;

import at.andiwand.common.network.ip.SubnetMask;


public class SubnetMaskTest {
	
	public static void main(String[] args) {
		String string = "255.255.255.255";
		System.out.println(string);
		SubnetMask mask = new SubnetMask(string);
		System.out.println(mask);
	}
	
}