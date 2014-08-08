package net.acomputerdog.core.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Branch<T> {
    private final Branch<T> parent;
    private final String name;
    private final Map<String, Branch<T>> branches = new LinkedHashMap<String, Branch<T>>();
    private final Map<String, Leaf<T>> leaves = new LinkedHashMap<String, Leaf<T>>();
    private final Tree<T> tree;

    public Branch(Tree<T> tree, Branch<T> parent, String name) {
        this.parent = parent;
        this.tree = tree;
        this.name = name;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public Branch getParent() {
        return parent;
    }

    public Collection<Branch<T>> getBranches() {
        return Collections.unmodifiableCollection(branches.values());
    }

    public Collection<Leaf<T>> getLeaves() {
        return Collections.unmodifiableCollection(leaves.values());
    }

    public int getNumBranches() {
        return branches.size();
    }

    public int getNumLeaves() {
        return leaves.size();
    }

    public boolean hasBranch(String name) {
        return branches.containsKey(name);
    }

    public boolean hasLeaf(String name) {
        return leaves.containsKey(name);
    }

    public void addBranch(Branch<T> branch) {
        branches.put(branch.getName(), branch);
    }

    public void addLeaf(Leaf<T> leaf) {
        leaves.put(leaf.getName(), leaf);
    }

    public Tree<T> getTree() {
        return tree;
    }

    public String getName() {
        return name;
    }

    public Branch<T> getBranch(String name) {
        return branches.get(name);
    }

    public Leaf<T> getLeaf(String name) {
        return leaves.get(name);
    }
}
