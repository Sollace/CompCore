package net.acomputerdog.core.storage;

import net.acomputerdog.core.identity.Identifiable;

import java.util.*;

/**
 * A registry that stores Identifiables
 *
 * @param <T> The type of object to store
 */
public class Registry<T extends Identifiable> {
    /**
     * Map of definitions to identifiables
     */

    private final Map<String, T> defMap;
    /**
     * Map of ids to identifiables
     */
    private final Map<String, T> idMap;

    /**
     * Creates a new Identifiable
     */
    public Registry() {
        defMap = new LinkedHashMap<String, T>();
        idMap = new LinkedHashMap<String, T>();
    }

    /**
     * Registers an Identifiable
     * @param item The identifiable to add
     * @param <T2> The type of the Identifable
     * @return Return the item
     */
    public <T2 extends T> T2 register(T2 item) {
        defMap.put(item.getDefinition(), item);
        idMap.put(item.getId(), item);
        return item;
    }

    /**
     * Check if the definition maps to a known identifiable
     * @param def The definition
     * @return Return true if the definition is defined
     */
    public boolean isDefined(String def) {
        return defMap.containsKey(def);
    }

    /**
     * Check if an item is defined in this registry
     * @param item the item
     * @return Return true if the item is defined
     */
    public boolean isDefined(T item) {
        return defMap.containsValue(item);
    }

    /**
     * gets an item from its definition
     * @param def The definition
     * @return Return the item
     */
    public T getFromDef(String def) {
        return defMap.get(def);
    }

    /**
     * Gets an item from its id
     * @param id The ID
     * @return Return the item
     */
    public T getFromId(String id) {
        return idMap.get(id);
    }

    /**
     * Get a collection of all items in this registry
     * @return Return a collection of all items in this registry
     */
    public Collection<T> getItems() {
        return defMap.values();
    }

    /**
     * Get a set of all definitions in this registry
     * @return Return a set of all definitions in this Registry
     */
    public Set<String> getDefinitions() {
        return Collections.unmodifiableSet(defMap.keySet());
    }

    /**
     * Get a set of all ids in this registry
     * @return Return a set of all ids in this Registry
     */
    public Set<String> getIds() {
        return Collections.unmodifiableSet(idMap.keySet());
    }
}
