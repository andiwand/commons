package at.andiwand.commons.util.collection;

import java.util.Map.Entry;

import at.andiwand.commons.util.object.ObjectTransformer;


public class EntryTransformer<K1, V1, K2, V2> implements
		ObjectTransformer<Entry<K1, V1>, Entry<K2, V2>> {
	
	private final ObjectTransformer<? super K1, ? extends K2> keyTransformer;
	private final ObjectTransformer<? super V1, ? extends V2> valueTransformer;
	
	public EntryTransformer(
			ObjectTransformer<? super K1, ? extends K2> keyTransformer,
			ObjectTransformer<? super V1, ? extends V2> valueTransformer) {
		this.keyTransformer = keyTransformer;
		this.valueTransformer = valueTransformer;
	}
	
	@Override
	public Entry<K2, V2> transform(Entry<K1, V1> source) {
		K2 key = keyTransformer.transform(source.getKey());
		V2 value = valueTransformer.transform(source.getValue());
		// TODO: improve return type
		return new SimpleImmutableEntry<K2, V2>(key, value);
	}
	
}