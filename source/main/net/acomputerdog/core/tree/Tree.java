package net.acomputerdog.core.tree;

public class Tree<T> {
    private final Branch<T> root = new Branch<T>(this, null, "ROOT");

    public Branch<T> root() {
        return root;
    }
}
