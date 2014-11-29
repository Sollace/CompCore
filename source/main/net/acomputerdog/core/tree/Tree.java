package net.acomputerdog.core.tree;

public class Tree<T> {
    private final Branch<T> root = new Branch<T>(this, null);

    public Branch<T> root() {
        return root;
    }
}
