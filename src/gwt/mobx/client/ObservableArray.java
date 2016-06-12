package gwt.mobx.client;

import gwt.interop.utils.shared.collections.Array;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * An observable array
 *
 * Bear in mind that Array.isArray(observable([])) will yield false, so whenever you need to
 * pass an observable array to an external library, it is a good idea to create a shallow
 * copy before passing it to other libraries or built-in functions
 * (which is good practice anyway) by using array.slice() or array.peek(). So
 * Array.isArray(observable([]).slice()) will yield true.
 *
 * Unlike the built-in implementation of the functions sort and reverse, observableArray.sort
 * and reverse will not change the array in-place, but only will return a sorted / reversed
 * copy.
 *
 * @param <T> The type of Array element
 */
@JsType(isNative=true, namespace = JsPackage.GLOBAL, name="ObservableArray")
public interface ObservableArray<T> extends Array<T> {
    //TODO spliceWithArray(index: number, deleteCount?: number, newItems?: T[]): T[];
    //TODO observe(listener: (changeData: IArrayChange<T>|IArraySplice<T>) => void, fireImmediately?: boolean): Lambda;

    /**
     * Clear all the entries in the array
     *
     * @return The removed entries
     */
    Array<T> clear();

    /**
     * Returns an array with all the values which can safely be passed to other libraries, similar
     * to slice(). In contrast to slice, peek doesn't create a defensive copy. Use this in
     * performance critical applications if you know for sure that you use the array in a
     * read-only manner. In performance critical sections it is recommend to use a flat
     * observable array as well
     *
     * @return A read-only array
     */
    Array<T> peek();

    /**
     * Replaces all existing entries in the array with new ones
     *
     * @param newItems The items to replace with
     * @return ?
     */
    ObservableArray<T> replace(Array<T> newItems);

    /**
     * Replaces all existing entries in the array with new ones
     *
     * @param newItems The items to replace with
     * @return ?
     */
    ObservableArray<T> replace(ObservableArray<T> newItems);

    /**
     * Find an item within the array
     *
     * @param testFn The test function to use
     * @return The matching item or null of not found
     */
    T find(TestFn<T> testFn);

    //TODO find(predicate: (item: T, index: number, array: IObservableArray<T>) => boolean, thisArg?: any, fromIndex?: number): T;

    /**
     * Remove a single item by value from the array. Returns true if the item was found and removed
     *
     * @param value The item to remove
     * @return True if the item was removed
     */
    boolean remove(T value);
}
