package net.acomputerdog.core.tree;

/**
 * A "leaf" on a tree.  A leaf is a branch that has data and no children
 *
 * @param <T>
 */
public class Leaf<T> {
    /**
     * Thee data on this leaf
     */
    private T item;

    /**
     * The branch that this leaf is on
     */
    private final Branch<T> branch;

    /**
     * Creates a new Leaf
     * @param branch The branch that this leaf is attached to
     * @param item The item this leaf holds
     */
    public Leaf(Branch<T> branch, T item) {
        if (branch == null) {
            throw new IllegalArgumentException("Cannot attach to a nul branch!");
        }
        this.branch = branch;
        this.item = item;
    }

    /**
     * Creates a new leaf with no item
     *
     * @param branch The branch that this leaf is attached to
     */
    public Leaf(Branch<T> branch) {
        this(branch, null);
    }

    /**
     * Gets the item held by this leaf
     * @return return the item
     */
    public T getItem() {
        return item;
    }

    /**
     * Sets the item held by this leaf
     * @param item Set the item
     */
    public void setItem(T item) {
        this.item = item;
    }

    /**
     * Gets the branch this leaf is on
     * @return return the branch of this leaf
     */
    public Branch<T> getBranch() {
        return branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Leaf)) return false;

        Leaf leaf = (Leaf) o;

        return branch.equals(leaf.branch) && !(item != null ? !item.equals(leaf.item) : leaf.item != null);

    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + branch.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Leaf{" +
                "item=" + item +
                ", branch=" + branch +
                '}';
    }
}
