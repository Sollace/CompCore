package net.acomputerdog.core.tree;

/**
 * A tree-modeled data structure
 *
 * @param <T> The type of the data
 */
public class Tree<T> {
    /**
     * Root branch of this Tree
     */
    private final Branch<T> root;

    /**
     * Creates a new Tree
     */
    public Tree() {
        root = new Branch<T>(this, null);
    }

    /**
     * Gets the root branch of this Tree
     *
     * @return return the root branch of this tree
     */
    public Branch<T> getRoot() {
        return root;
    }
}
