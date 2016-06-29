package gwt.mobx.client;

import com.google.gwt.core.client.JavaScriptObject;
import gwt.interop.utils.shared.functional.JsConsumer;
import gwt.interop.utils.shared.functional.JsPredicate;
import gwt.interop.utils.shared.functional.JsProcedure;
import gwt.interop.utils.shared.collections.Array;
import gwt.interop.utils.client.plainobjects.JsPlainObj;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * MobX is a battle tested library that makes state management simple and scalable by
 * transparently applying functional reactive programming (TFRP)
 */
@JsType(isNative=true, namespace= JsPackage.GLOBAL)
public class MobX {
    /**
     * Makes the supplied javascript object observable.
     *
     * If a plain JavaScript object is passed to observable (that is, an object that wasn't created
     * using a constructor function), MobX will recursively pass all its values through observable.
     * This way the complete object (tree) is in-place instrumented to make it observable
     *
     * @param obj The Plain javascript object to make observable
     * @param <O> A subclass of JavaScriptObject
     * @return An augmented version of the supplied object that will update any views that depend
     *         on it.
     */
    public static native <O extends JavaScriptObject> O observable(O obj);

    /**
     * Makes the supplied Object Literal observable.
     *
     * MobX will recursively pass all the supplied object literal values through observable.
     * This way the complete object (tree) is in-place instrumented to make it observable
     *
     * @param jsPlainObj The object to make observable
     * @param <O> A subclass of JsPlainObj
     * @return An augmented version of the supplied object that will update any views that depend
     *         on it.
     */
    public static native <O extends JsPlainObj> O observable(O jsPlainObj);

    /**
     * Makes an Array observable. This works recursively as well, so all (future) values of the
     * array will be observable as well.
     *
     * This method will will instrument a clone of the provided array instead of the original one.
     * In practice, these arrays work just as fine as native arrays and all native methods are
     * supported, including index assignments, up-to and including the length of the array
     *
     * @param array The javascript array to make observable.
     * @param <T> The type of object the array contains
     * @return An ObservableArray wrapper.
     */
    public static native <T> ObservableArray<T> observable(Array<T> array);

    /**
     * Creates an observable int value
     *
     * @param value The initial value
     * @return A ObservableIntValue
     */
    @JsMethod(name = "observable")
    public static native ObservableIntValue observableValue(int value);

    /**
     * Creates an observable value
     *
     * @param value The initial value
     * @param <T> The type of the observable value
     * @return A ObservableValue
     */
    @JsMethod(name = "observable")
    public static native <T> ObservableValue<T> observableValue(T value);

    /**
     * Use computed if you want to reactively produce a new value that can be used by other
     * observers and autorun if you don't want to produce a new value but rather invoke some
     * imperative code like logging, network requests etc.
     *
     * <p>Computed properties can be optimized away in many cases by MobX as they are assumed
     * to be pure. So they will not be invoked when their input parameters didn't modifiy or
     * if they are not observed by some other computed value or autorun.</p>
     *
     * @param exprFn A function returning the ComputedIntValue
     * @return A ComputedIntValue
     */
    public static native ComputedIntValue computed(ComputedIntExpression exprFn);

    /**
     * Use computed if you want to reactively produce a new value that can be used by other
     * observers and autorun if you don't want to produce a new value but rather invoke some
     * imperative code like logging, network requests etc.
     *
     * <p>Computed properties can be optimized away in many cases by MobX as they are assumed
     * to be pure. So they will not be invoked when their input parameters didn't modifiy or
     * if they are not observed by some other computed value or autorun.</p>
     *
     * @param exprFn A function returning the ComputedValue
     * @param <T> The type of object returned by exprFn
     * @param <T2> The type of object the returned ComputedValue holds
     * @return A ComputedValue
     */
    public static native <T, T2 extends T> ComputedValue<T2> computed(ComputedExpression<T> exprFn);

    /**
     * autorun can be used in those cases where you want to create a reactive function that will
     * never have observers itself. This is usually the case when you need to bridge from reactive
     * to imperative code, for example for logging, persistence or UI-updating code. When autorun
     * is used, the provided function will always be triggered when one of its dependencies
     * changes. In contrast, computed(function) creates functions that only re-evaluate if it has
     * observers on its own, otherwise its value is considered to be irrelevant. As a rule of
     * thumb: use autorun if you have a function that should run automatically but that doesn't
     * result in a new value. Use computed for everything else. Autoruns are about initiating
     * effects, not about producing new values.
     *
     * @param view The view callback to execute
     * @return A DisposerFunction to cancel the autorun callback
     */
    public static native DisposerFunction autorun(JsProcedure view); //scope???

    /**
     * Just like autorun except that the action won't be invoked synchronously but asynchronously
     * after the minimum amount of milliseconds has passed. The action will be run and observed.
     * However, instead of running the action immediately when the values it observes have
     * changed, the minimumDelay will be awaited before re-execution the action again.
     *
     * <p>If observed values are changed multiple times while waiting, the action is still
     * triggered only once, so in a sense it achieves a similar effect than a transaction. This
     * might be useful for stuff that is expensive and doesn't need to happen synchronously;
     * such as debouncing server communication.</p>
     *
     * @param view The view callback to execute
     * @param minimumDelay The minium delay in ms to pass before the view function is invoked
     * @return A DisposerFunction to cancel the autorun callback
     */
    public static native DisposerFunction autorunAsync(JsProcedure view, int minimumDelay); //scope???

    /**
     * creates a dynamic keyed observable map. Optionally takes an object or entries array with
     * initially values. Only string values are accepted as keys
     *
     * @return An ObservableMap
     */
    public static native ObservableMap map();

    /**
     * when observes and runs the given predicate until it returns true. Once that happens, the
     * given effect is executed and the autorunner is disposed. The function returns a
     * disposer to cancel the autorunner prematurely.
     *
     * <p>This function is really useful to dispose or cancel stuff in a reactive way</p>
     *
     * @param predicate The predicate to test
     * @param effect The function to call when the predicate matches
     * @return A DisposerFunction to cancel the when callback
     */
    public static native DisposerFunction when(JsPredicate predicate, JsProcedure effect); //scope???

    /**
     * transaction can be used to batch a bunch of updates without notifying any observers until
     * the end of the transaction. transaction takes a single, parameterless worker function as
     * argument and runs it. No observers are notified until this function has completed.
     * Note that transaction runs completely synchronously. Transactions can be nested. Only after
     * completing the outermost transaction pending reactions will be run.
     *
     * @param worker The transaction worker callback
     */
    public static native void transaction(JsProcedure worker);

    /**
     * Recursively converts an ObservableArray object to a JSON structure. Supports observable
     * arrays, objects, maps and primitives. Computed values and other non-enumerable
     * properties won't be part of the result. Cycles are supported by default, but this
     * can be disabled to improve performance.
     *
     * @param array         The ObservableArray to convert
     * @param supportCycles True to support cyclic structures
     * @param <O> The JSON literal
     * @return A JSON structure
     */
    public static native <O extends JsPlainObj> O toJSON(ObservableArray array, boolean supportCycles);

    /**
     * Recursively converts an (observable) object to a JSON structure. Supports observable
     * arrays, objects, maps and primitives. Computed values and other non-enumerable
     * properties won't be part of the result. Cycles are supported by default, but this
     * can be disabled to improve performance.
     *
     * @param jsPlainObj    The (observable) object to convert
     * @param supportCycles True to support cyclic structures
     * @param <O> The JSON literal type
     * @return A JSON structure
     */
    public static native <O extends JsPlainObj> O toJSON(JsPlainObj jsPlainObj, boolean supportCycles);

    /**
     * Recursively converts an (observable) object to a JSON structure. Supports observable
     * arrays, objects, maps and primitives. Computed values and other non-enumerable
     * properties won't be part of the result. Cycles are supported by default, but this
     * can be disabled to improve performance.
     *
     * @param javaScriptObject The (observable) object to convert
     * @param supportCycles True to support cyclic structures
     * @param <O> The Javascript object
     * @return A JSON structure
     */
    public static native <O extends JavaScriptObject> O toJSON(JavaScriptObject javaScriptObject, boolean supportCycles);

    //TODO test and document these

    public static native boolean isObservable(Object o);
    public static native boolean isObservable(Object o, String property);
    public static native <T> T expr(JsProcedure expr);
    public static native <T> T asReference(T obj);
    public static native <T> T asStructure(T obj);
    public static native <T> T asFlat(T obj);

    /**
     * Enable/Disable strict mode. When enabled, all mutations to observables need to occur within
     * in an action.
     *
     * @param enable enable/disable strict mode
     */
    public static native void useStrict(boolean enable);

    /**
     * Run the supplied block of code in an action.
     *
     * @param block The code to execute in the action
     */
    public static native void runInAction(JsProcedure block);

    /**
     * Run the supplied block of code in an action.
     *
     * @param name The debug name to give the action
     * @param block The code to execute in the action
     */
    public static native void runInAction(String name, JsProcedure block);


    public static native void whyRun();
    public static native void whyRun(ComputedIntValue computed);
    public static native void whyRun(ComputedValue computed);
    public static native void whyRun(Object obj, String property);

    /**
     * An observable int value
     */
    @JsType(isNative=true, namespace = JsPackage.GLOBAL, name="ObservableValue")
    public interface ObservableIntValue {
        /**
         * Access the int value
         *
         * @return The value as a java int
         */
        int get();

        /**
         * Set the value of the observable int.
         *
         * @param value The value to set
         */
        void set(int value);

        /**
         * Registers an observer function that will fire each time the stored value is replaced.
         * Returns a function to cancel the observer
         *
         * @param callback The ObserveIntCallBack to call when the value changes
         * @return A DisposerFunction to cancel the observer
         */
        DisposerFunction observe(ObserveIntCallBack callback);
    }

    /**
     * An observable value
     */
    @JsType(isNative = true)
    public interface ObservableValue<T> {
        /**
         * Access the value
         *
         * @return The value
         */
        T get();

        /**
         * Set the value
         *
         * @param value The value to set
         */
        void set(T value);

        /**
         * Registers an observer function that will fire each time the stored value is replaced.
         * Returns a function to cancel the observer
         *
         * @param callback The ObserveCallBack to call when the value changes
         * @return A DisposerFunction to cancel the observer
         */
        DisposerFunction observe(ObserveCallBack<T> callback);

        /**
         * Registers an observer function that will fire each time the stored value is replaced.
         * Returns a function to cancel the observer
         *
         * @param callback The ObserveCallBack to call when the value changes
         * @param fireImmediately If true, the supplied callback will be called immediately after
         *                          this method is called.
         * @return A DisposerFunction to cancel the observer
         */
        DisposerFunction observe(ObserveCallBack<T> callback, boolean fireImmediately);
    }

    /**
     * A computed int value
     */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name="ComputedValue")
    public interface ComputedIntValue {
        int get();

        /**
         * Registers an observer function that will fire each time the computed value changes.
         * Returns a function to cancel the observer
         *
         * @param callback The ObserveIntCallBack to call when the value changes
         * @param fireImmediately If true, the callback will fire immediatley observe is called
         * @return A DisposerFunction to cancel the observer
         */
        DisposerFunction observe(ObserveIntCallBack callback, boolean fireImmediately);
    }

    /**
     * A computed value
     */
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name="ComputedValue")
    public interface ComputedValue<T2> {
        T2 get();

        /**
         * Registers an observer function that will fire each time the computed value changes.
         * Returns a function to cancel the observer
         *
         * @param callback The ObserveCallBack to call when the value changes
         * @param fireImmediately If true, the callback will fire immediatley observe is called
         * @return A DisposerFunction to cancel the observer
         */
        DisposerFunction observe(ObserveCallBack<T2> callback, boolean fireImmediately);
    }

    @JsFunction
    public interface ComputedExpression<T2> {
        T2 compute();
    }

    @JsFunction
    public interface ComputedIntExpression {
        int compute();
    }

    @JsFunction
    public interface DisposerFunction {
        void dispose();
    }

    @JsFunction
    public interface ObserveCallBack<T> {
        void onChange(T newValue, T oldValue);
    }

    @JsFunction
    public interface ObserveIntCallBack {
        void onChange(int newValue, int oldValue);
    }
}
