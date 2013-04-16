package at.andiwand.commons.util.object;

public interface ObjectTransformer<S, D> {

    public D transform(S source);

}