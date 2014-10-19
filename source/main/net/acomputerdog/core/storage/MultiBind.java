package net.acomputerdog.core.storage;

import java.util.HashMap;
import java.util.Map;

/**
 * Similar to a HashMap, but with a category->key->value mapping.
 *
 * @param <C> The category (1st) identifier type
 * @param <K> The key (2nd) identifier type
 * @param <V> The value type
 */
public class MultiBind<C, K, V> {
    private final Map<C, Map<K, V>> bindData = new HashMap<C, Map<K, V>>();

    public void put(C category, K key, V value) {
        getOrCreateCategory(category).put(key, value);
    }

    public V getValue(C category, K key) {
        Map<K, V> cat = getCategory(category);
        if (cat == null) {
            return null;
        }
        return cat.get(key);
    }

    public Map<K, V> getCategory(C category) {
        return bindData.get(category);
    }

    public boolean categoryExists(C category) {
        return bindData.containsKey(category);
    }

    public boolean keyExists(C category, K key) {
        Map<K, V> cat = getCategory(category);
        return cat != null && cat.containsKey(key);
    }

    private Map<K, V> getOrCreateCategory(C category) {
        Map<K, V> cat = getCategory(category);
        if (cat == null) {
            bindData.put(category, cat = new HashMap<K, V>());
        }
        return cat;
    }
}
