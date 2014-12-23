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
     * @return return the root branch
     */
    public Branch<T> getRoot() {
        return root;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tree)) return false;

        Tree tree = (Tree) o;

        return root.equals(tree.root);

    }

    @Override
    public int hashCode() {
        return root.hashCode();
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}
