package net.acomputerdog.core.tree;

/**
 * An immutable leaf
 *
 * @param <T> The type of this leaf
 */
public final class ImmutableLeaf<T> extends Leaf<T> {
    /**
     * Creates a new ImmutableLeaf
     * @param branch The branch to attach to
     * @param item The item this ImmutableLeaf holds
     */
    public ImmutableLeaf(Branch branch, T item) {
        super(branch, item);
    }

    @Override
    public void setItem(T item) {
        //immutable :)
    }
}
