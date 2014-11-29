package net.acomputerdog.core.tree;

public class ImmutableLeaf<T> extends Leaf<T> {
    public ImmutableLeaf(Branch branch, T item) {
        super(branch, item);
    }

    @Override
    public void setItem(T item) {
        //immutable :)
    }
}
