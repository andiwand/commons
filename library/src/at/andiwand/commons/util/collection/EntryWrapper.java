package at.andiwand.commons.util.collection;

import java.util.Map.Entry;

import at.andiwand.commons.util.object.ObjectExtractor;
import at.andiwand.commons.util.object.ObjectTransformer;

public class EntryWrapper<K1, V1, K2, V2> extends
	AbstractEntryWrapper<K1, V1, K2, V2> {

    private final ObjectTransformer<? super K1, ? extends K2> keyTransformer;
    private final ObjectExtractor<? super V1, V2> valueExtractor;

    public EntryWrapper(Entry<K1, V1> entry,
	    ObjectTransformer<? super K1, ? extends K2> keyTransformer,
	    ObjectExtractor<? super V1, V2> valueExtractor) {
	super(entry);

	this.keyTransformer = keyTransformer;
	this.valueExtractor = valueExtractor;
    }

    @Override
    public K2 getKey() {
	return keyTransformer.transform(entry.getKey());
    }

    @Override
    public V2 getValue() {
	return valueExtractor.transform(entry.getValue());
    }

    @Override
    public V2 setValue(V2 value) {
	V2 tmp = getValue();
	valueExtractor.insert(entry.getValue(), value);
	return tmp;
    }

}