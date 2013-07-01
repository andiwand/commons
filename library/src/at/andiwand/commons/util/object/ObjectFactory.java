package at.andiwand.commons.util.object;

public interface ObjectFactory<T, C> {

    public T create(C context);

}