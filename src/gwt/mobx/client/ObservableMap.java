package gwt.mobx.client;

import gwt.react.shared.utils.Array;
import gwt.react.shared.utils.StringMap;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * creates a dynamic keyed observable map. Optionally takes an object or entries array with
 * initially values. Only string values are accepted as keys
 */
@JsType(isNative=true, namespace = JsPackage.GLOBAL, name="ObservableMap")
public interface ObservableMap<T> {
    /**
     * Returns whether this map has the provided key. Note that the presence of a key is an
     * observable fact in itself
     *
     * @param key The key value to test for
     * @return true if the key is within the map
     */
    boolean has(String key);

    /**
     * Sets the given key to value. The provided key will be added to the map if it didn't exist yet
     *
     * @param key The key to set
     * @param value The value to set
     */
    void set(String key, T value);

    /**
     * Deletes the given key and its value from the map
     *
     * @param key The key to delete
     */
    void delete(String key);

    /**
     * Returns the value at the given key (or undefined). Make sure that you guard get calls with has
     *
     * @param key The key of the value to retrieve
     * @return The value or null if it doesn't exist
     */
    T get(String key);

    /**
     * Returns all keys present in this map. The insertion order is preserved
     *
     * @return An Array of keys
     */
    Array<String> keys();

    /**
     * Returns all values present in this map. Insertion order is preserved
     *
     * @return An Array of values
     */
    Array<T> values();

    /**
     * Invokes the given callback for each key / value pair in the map
     *
     * @param forEachFn The function to call for each key/value pair
     */
    void forEach(ForEachFn<T> forEachFn);

    /**
     * Removes all entries from this map
     */
    void clear();

    /**
     * Returns the amount of entries in this map
     *
     * @return The size of the map
     */

    @JsProperty(name="size") int size();

    /**
     * Returns a shallow plain object representation of this map. (For a deep copy use
     * MobX.toJSON(map)).
     *
     * @return A plain object representation of this map, suitable to transmit as JSON
     */
    StringMap<T> toJs();

    /**
     * Registers a listener that fires upon each change in this map, similarly to the events that
     * are emitted for Object.observe.
     *
     * @param observeMapCallback The ObserveMapCallback to call when the value changes
     * @return A DisposerFunction to cancel the observer
     */
    MobX.DisposerFunction observe(ObserveMapCallback<T> observeMapCallback);

    /**
     * Copies all entries from the provided ObservableMap into this map
     *
     * @param toMerge The map to merge
     */
    void merge(ObservableMap<T> toMerge);

    @JsFunction
    interface ForEachFn<T> {
        void forEach(T value, String key, ObservableMap<T> map);
    }

    @JsFunction
    interface ObserveMapCallback<T> {
        void onChange(ChangeInfo<T> info);
    }

    @JsType(isNative = true)
    public class ChangeInfo<T> {
        public String name;
        public ObservableMap<T> object;
        public T oldValue;
        public String type;
    }
}