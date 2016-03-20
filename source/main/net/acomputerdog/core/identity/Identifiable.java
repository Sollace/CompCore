package net.acomputerdog.core.identity;

/**
 * Represents an object that can be "identified" by a non-specific name, and specific id, and a specific definition that can be used to recreate the object
 */
public interface Identifiable<T, V> {
    /**
     * Get the specific (unique) id of this Identifiable.
     *
     * @return return the specific (unique) id of this Identifiable.
     */
    public T getId();

    /**
     * Get the specific (unique) definition of this Identifiable.  Definition should contain enough information to recreate the object.
     * @return return the definition of this Identifiable.
     */
    public V getDefinition();

    /**
     * Get the nonspecific (not unique) name of this Identifiable
     * @return return the name of this Identifiable
     */
    public String getName();
}
