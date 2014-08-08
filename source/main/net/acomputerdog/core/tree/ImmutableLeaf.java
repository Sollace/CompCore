package net.acomputerdog.core.tree;

public class ImmutableLeaf<T> extends Leaf<T> {
    public ImmutableLeaf(Branch branch, T item, String name) {
        super(branch, item, name);
    }

    @Override
    public void setItem(T item) {
        //immutable :)
    }
}
