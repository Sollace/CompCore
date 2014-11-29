package net.acomputerdog.core.tree;

public class Leaf<T> {
    private T item;
    private final Branch branch;

    public Leaf(Branch branch, T item) {
        this.branch = branch;
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Branch getBranch() {
        return branch;
    }
}
