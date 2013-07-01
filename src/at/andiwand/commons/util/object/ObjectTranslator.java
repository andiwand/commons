package at.andiwand.commons.util.object;

public interface ObjectTranslator<S, D> extends ObjectTransformer<S, D> {

    public S retransform(D from);

}