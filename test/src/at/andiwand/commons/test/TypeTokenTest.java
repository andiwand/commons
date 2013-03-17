package at.andiwand.commons.test;

import java.util.Set;

import at.andiwand.commons.util.TypeToken;


public class TypeTokenTest {
	
	public static void main(String[] args) {
		TypeToken<?> typeToken = new TypeToken<Set<String>>() {};
		System.out.println(typeToken);
		System.out.println(typeToken.getRawType());
		System.out.println(typeToken.isAssignableFrom(String.class));
		System.out.println(typeToken.isAssignableFrom(Set.class));
		System.out.println(typeToken
				.isAssignableFrom(new TypeToken<Set<Integer>>() {}));
		System.out.println(typeToken
				.isAssignableFrom(new TypeToken<Set<String>>() {}));
	}
	
}