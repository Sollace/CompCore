package net.acomputerdog.core.storage;

import java.util.Collections;
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
    /**
     * The map that holds the actual data for this MultiBind
     */
    private final Map<C, Map<K, V>> bindData = new HashMap<C, Map<K, V>>();

    /**
     * Creates a new MultiBind
     */
    public MultiBind() {
        super();
    }

    /**
     * Adds a value to this MultiBind
     *
     * @param category The category
     * @param key      The key
     * @param value    The value
     */
    public void put(C category, K key, V value) {
        getOrCreateCategory(category).put(key, value);
    }

    /**
     * Gets a value from this MultiBind
     * @param category The category
     * @param key The key
     * @return Return the value
     */
    public V getValue(C category, K key) {
        Map<K, V> cat = getCategory(category);
        if (cat == null) {
            return null;
        }
        return cat.get(key);
    }

    /**
     * Gets all the mappings from a specified category
     * @param category The category to get
     * @return Return all the mappings from the category
     */
    public Map<K, V> getCategory(C category) {
        return Collections.unmodifiableMap(bindData.get(category));
    }

    /**
     * Check if a category exists.
     * @param category The category to check
     * @return Return true if the category exists, false otherwise
     */
    public boolean categoryExists(C category) {
        return bindData.containsKey(category) && bindData.get(category).size() > 0;
    }

    /**
     * Check if a key exists
     * @param category The category to check
     * @param key The key to check
     * @return Return true if the key has a value
     */
    public boolean keyExists(C category, K key) {
        Map<K, V> cat = getCategory(category);
        return cat != null && cat.containsKey(key);
    }

    /**
     * Get or create a map of values for a category
     * @param category The category
     * @return Return a map of values for a category
     */
    private Map<K, V> getOrCreateCategory(C category) {
        Map<K, V> cat = getCategory(category);
        if (cat == null) {
            bindData.put(category, cat = new HashMap<K, V>());
        }
        return cat;
    }
}
