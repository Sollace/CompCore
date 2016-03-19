package net.acomputerdog.core.storage;

import java.util.HashMap;
import java.util.Map;

/**
 * A HashMap with the added method public V get(K key, V def) that allows the user to
 * specify a default value to return if key is not defined.
 *
 * @param <K> The key type
 * @param <V> The value type
 * 
 * @deprecated As of 1.8 this is implemented through {@code HashMap.getOrDefault}
 */
@Deprecated
public class DefaultValueHashMap<K, V> extends HashMap<K, V> {
    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     *
     * @param initialCapacity the initial capacity
     * @param loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *                                  or the load factor is nonpositive
     */
    public DefaultValueHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public DefaultValueHashMap() {
        super();
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public DefaultValueHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     *
     * @param m the map whose mappings are to be placed in this map
     * @throws NullPointerException if the specified map is null
     */
    public DefaultValueHashMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    /**
     * Returns the value to which the specified key is mapped, or
     * {@code defaultValue} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @param def the default mapping of the key
     * @return the value to which the specified key is mapped, or
     * {@code def} if this map contains no mapping for the key
     */
    public V get(K key, V def) {
        if (containsKey(key)) {
            return super.get(key);
        } else {
            return def;
        }
    }
}
