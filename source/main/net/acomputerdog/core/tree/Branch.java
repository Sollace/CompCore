package net.acomputerdog.core.tree;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A branch structure.  Contains no direct items but contains any number of Leafs and Branches
 *
 * @param <T> The type of data of this Branch
 */
public class Branch<T> {
    /**
     * The parent branch of this Branch
     */
    private final Branch<T> parent;

    /**
     * The branches held by this Branch
     */
    private final Set<Branch<T>> branches;

    /**
     * The leaves held by this Branch
     */
    private final Set<Leaf<T>> leaves;

    /**
     * The tree that this branch is a part of
     */
    private final Tree<T> tree;

    /**
     * Creates a new Branch
     * @param tree The tree to be on
     * @param parent The parent branch
     */
    public Branch(Tree<T> tree, Branch<T> parent) {
        if (tree == null) {
            throw new IllegalArgumentException("Cannot attach to a null tree!");
        }
        if (parent == null) {
            throw new IllegalArgumentException("Cannot attach to a null branch!");
        }
        this.tree = tree;
        this.parent = parent;
        branches = new HashSet<Branch<T>>();
        leaves = new HashSet<Leaf<T>>();
    }

    /**
     * Protected constructor for creating the root branch
     *
     * @param tree The tree to attach to
     */
    protected Branch(Tree<T> tree) {
        this.tree = tree;
        this.parent = null;
        branches = new HashSet<Branch<T>>();
        leaves = new HashSet<Leaf<T>>();
    }


    /**
     * Check if this branch is a root branch (it has no parent)
     * @return return true if this is a root branch
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * Gets the parent of this Branch
     * @return return the parent of this Branch
     */
    public Branch getParent() {
        return parent;
    }

    /**
     * Gets the branches on this branch
     * @return return the branches attached to this branch
     */
    public Set<Branch<T>> getBranches() {
        return Collections.unmodifiableSet(branches);
    }

    /**
     * Gets the leaves attached to this Branch
     * @return return the leaves attached to this branch
     */
    public Set<Leaf<T>> getLeaves() {
        return Collections.unmodifiableSet(leaves);
    }

    /**
     * Gets the number of branches on this Branch
     * @return return the number of branches
     */
    public int getNumBranches() {
        return branches.size();
    }

    /**
     * Gets the number of leaves on this branch
     * @return return the number of leaves
     */
    public int getNumLeaves() {
        return leaves.size();
    }

    /**
     * Add a branch to this Branch
     * @param branch the branch to add
     */
    public void addBranch(Branch<T> branch) {
        if (branch == null) {
            throw new IllegalArgumentException("Cannot attach a null branch!");
        }
        if (branch == this) {
            throw new IllegalArgumentException("Cannot attach a branch to itself!");
        }
        branches.add(branch);
    }

    /**
     * Adds a leaf to this branch
     * @param leaf The leaf to add
     */
    public void addLeaf(Leaf<T> leaf) {
        leaves.add(leaf);
    }

    /**
     * Gets the tree that this branch is a part of
     * @return return the branch that this tree is a part of
     */
    public Tree<T> getTree() {
        return tree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;

        Branch branch = (Branch) o;
        return branches.equals(branch.branches) && leaves.equals(branch.leaves);

    }

    @Override
    public int hashCode() {
        int result = branches.hashCode();
        result = 31 * result + leaves.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branches=" + branches +
                ", leaves=" + leaves +
                '}';
    }
}
