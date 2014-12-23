package net.acomputerdog.core.java;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Reflection utilities
 */
public class Reflect {
    /**
     * Map of FieldInstances to Fields
     */
    private static final Map<FieldInstance, Field> fieldMap = new HashMap<FieldInstance, Field>();

    /**
     * Gets the value of a field, even if it is private
     *
     * @param cls      The class containing the field
     * @param instance An instance of the class to get from, or null if static
     * @param name     The name of the field
     * @param <T>      The type of the field
     * @return Return a T representing the value of the field
     */
    public static <T> T getFieldValue(Class cls, Object instance, String name) {
        FieldInstance fid = new FieldInstance(cls, name);
        Field field = fieldMap.get(fid);
        if (field == null) {
            try {
                field = cls.getDeclaredField(name);
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Field does not exist!", e);
            }
            fieldMap.put(fid, field);
        }
        try {
            return (T) field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to access field!", e);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Field does not contain a value of type T!", e);
        }
    }

    /**
     * Class used to hold a class and field name.  Used for caching hashmap.
     */
    private static class FieldInstance {
        /**
         * The class of the field
         */
        private final Class cls;
        /**
         * The name of the field
         */
        private final String name;

        private FieldInstance(Class cls, String name) {
            if (cls == null || name == null) {
                throw new IllegalArgumentException("Class and Name cannot be null!");
            }
            this.cls = cls;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldInstance)) return false;

            FieldInstance fieldInstance = (FieldInstance) o;

            if (!cls.equals(fieldInstance.cls)) return false;

            return name.equals(fieldInstance.name);

        }

        @Override
        public int hashCode() {
            int result = cls.hashCode();
            result = 31 * result + (name.hashCode());
            return result;
        }
    }
}
