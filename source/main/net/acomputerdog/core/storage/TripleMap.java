package net.acomputerdog.core.storage;

import net.acomputerdog.core.hash.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps 3 object together so that the entire set can be retrieved by using any of the them as a key.
 */
public class TripleMap<T, K, U> {

    private final Map<T, TSMItem> item1Map = new HashMap<T, TSMItem>();
    private final Map<K, TSMItem> item2Map = new HashMap<K, TSMItem>();
    private final Map<U, TSMItem> item3Map = new HashMap<U, TSMItem>();

    public void addItems(T string1, K string2, U string3) {
        TSMItem items = new TSMItem(string1, string2, string3);
        putItem(item1Map, string1, items);
        putItem(item2Map, string2, items);
        putItem(item3Map, string3, items);
    }

    private <V> void putItem(Map<V, TSMItem> cat, V item, TSMItem items) {
        if (item != null && (item instanceof String && !((String)item).trim().isEmpty())) {
            cat.put(item, items);
        }
    }

    public TSMItem getFrom1(T item) {
        return item1Map.get(item);
    }

    public TSMItem getFrom2(K item) {
        return item2Map.get(item);
    }

    public TSMItem getFrom3(U item) {
        return item3Map.get(item);
    }

    public boolean hasItem1(T item1) {
        return item1Map.containsKey(item1);
    }

    public boolean hasItem2(K item2) {
        return item2Map.containsKey(item2);
    }

    public boolean hasItem3(U item3) {
        return item3Map.containsKey(item3);
    }

    /**
     * A binding of three strings
     */
    public class TSMItem {
        private final T item1;
        private final K item2;
        private final U item3;

        private TSMItem(T item1, K item2, U item3) {
            this.item1 = item1;
            this.item2 = item2;
            this.item3 = item3;
        }

        public T getItem1() {
            return item1;
        }

        public K getItem2() {
            return item2;
        }

        public U getItem3() {
            return item3;
        }

        @Override
        public int hashCode() {
            int hash = Hash.SEED;
            hash = Hash.hash(hash, item1);
            hash = Hash.hash(hash, item2);
            hash = Hash.hash(hash, item3);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            if (obj instanceof TripleMap.TSMItem) {
                TSMItem other = (TSMItem) obj;
                return doesEqual(other.item1, item1) && doesEqual(other.item2, item2) && doesEqual(other.item3, item3);
            }
            return false;
        }

        @Override
        public String toString() {
            return "[" + item1 + ',' + item2 + ',' + item3 + ']';
        }

        private boolean doesEqual(Object obj1, Object obj2) {
            if (obj1 == null) {
                return obj2 == null;
            }
            return obj1.equals(obj2);
        }
    }
}
