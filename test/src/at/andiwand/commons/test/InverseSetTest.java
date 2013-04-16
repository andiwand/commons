package at.andiwand.commons.test;

import at.andiwand.commons.util.collection.InverseSet;

public class InverseSetTest {

    public static void main(String[] args) {
	InverseSet<String> infiniteSet = new InverseSet<String>();

	System.out.println(infiniteSet.inverseSet().size());
	System.out.println(infiniteSet);
	System.out.println();

	String a = "asdf";
	System.out.println(a);
	System.out.println(infiniteSet.contains(a));
	System.out.println();

	infiniteSet.removeElement("asdf");
	System.out.println(infiniteSet.inverseSet().size());
	System.out.println(infiniteSet);
	System.out.println();

	System.out.println(a);
	System.out.println(infiniteSet.contains(a));
	System.out.println();

	infiniteSet.add("asdf");
	System.out.println(infiniteSet.inverseSet().size());
	System.out.println(infiniteSet);
	System.out.println();

	System.out.println(a);
	System.out.println(infiniteSet.contains(a));
	System.out.println();
    }

}