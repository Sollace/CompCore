package net.acomputerdog.core.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
    private final List<Branch<T>> branches = new LinkedList<Branch<T>>();

    /**
     * The leaves held by this Branch
     */
    private final List<Leaf<T>> leaves = new LinkedList<Leaf<T>>();

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
        this.parent = parent;
        this.tree = tree;
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
    public List<Branch<T>> getBranches() {
        return Collections.unmodifiableList(branches);
    }

    /**
     * Gets the leaves attached to this Branch
     * @return return the leaves attached to this branch
     */
    public List<Leaf<T>> getLeaves() {
        return Collections.unmodifiableList(leaves);
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
}
