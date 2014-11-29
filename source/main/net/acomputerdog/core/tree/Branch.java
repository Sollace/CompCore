package net.acomputerdog.core.tree;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Branch<T> {
    private final Branch<T> parent;
    private final Set<Branch<T>> branches = new HashSet<Branch<T>>();
    private final Set<Leaf<T>> leaves = new HashSet<Leaf<T>>();
    private final Tree<T> tree;

    public Branch(Tree<T> tree, Branch<T> parent) {
        this.parent = parent;
        this.tree = tree;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public Branch getParent() {
        return parent;
    }

    public Set<Branch<T>> getBranches() {
        return Collections.unmodifiableSet(branches);
    }

    public Set<Leaf<T>> getLeaves() {
        return Collections.unmodifiableSet(leaves);
    }

    public int getNumBranches() {
        return branches.size();
    }

    public int getNumLeaves() {
        return leaves.size();
    }

    public void addBranch(Branch<T> branch) {
        branches.add(branch);
    }

    public void addLeaf(Leaf<T> leaf) {
        leaves.add(leaf);
    }

    public Tree<T> getTree() {
        return tree;
    }
}
