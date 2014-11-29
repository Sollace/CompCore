package net.acomputerdog.core.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Branch<T> {
    private final Branch<T> parent;
    private final List<Branch<T>> branches = new LinkedList<Branch<T>>();
    private final List<Leaf<T>> leaves = new LinkedList<Leaf<T>>();
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

    public List<Branch<T>> getBranches() {
        return Collections.unmodifiableList(branches);
    }

    public List<Leaf<T>> getLeaves() {
        return Collections.unmodifiableList(leaves);
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
