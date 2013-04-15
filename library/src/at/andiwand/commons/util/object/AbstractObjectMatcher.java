package at.andiwand.commons.util.object;

public abstract class AbstractObjectMatcher<T> implements ObjectMatcher<T> {
	
	private final Class<T> matchedClass;
	
	public AbstractObjectMatcher(Class<T> matchedClass) {
		this.matchedClass = matchedClass;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!matchedClass.isAssignableFrom(obj.getClass())) return false;
		
		return matches((T) obj);
	}
	
	@Override
	public Class<T> getMatchedClass() {
		return matchedClass;
	}
	
}