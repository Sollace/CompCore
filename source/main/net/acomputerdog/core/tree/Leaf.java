package net.acomputerdog.core.tree;

public class Leaf<T> {
    private T item;
    private final Branch branch;
    private final String name;

    public Leaf(Branch branch, T item, String name) {
        this.branch = branch;
        this.item = item;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
