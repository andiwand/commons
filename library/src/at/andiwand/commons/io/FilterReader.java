package at.andiwand.commons.io;

import java.io.Reader;

public abstract class FilterReader extends DelegationReader {

    public FilterReader(Reader in) {
	super(in);
    }

}