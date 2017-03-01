package gwt.mobx.client;

import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.components.Component;
import gwt.react.client.components.ComponentConstructorFn;
import gwt.react.client.components.ComponentUtils;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseContext;
import gwt.react.client.proptypes.BaseProps;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * MobX React integration
 */
@JsType(isNative=true, namespace= JsPackage.GLOBAL)
public class MobXReact {
    /**
     * The observer function can be used to turn ReactJS components into reactive components. It
     * wraps the component's render function in mobx.autorun to make sure that any data that is
     * used during the rendering of a component forces a re-rendering upon change.
     *
     * <p>Tip: when observer needs to be combined with other higher-order-components, make sure
     * that observer is the innermost (first applied) function; otherwise it might do nothing
     * at all.</p>
     *
     * @param type The react Component class to make an observer
     * @param <P> The prop types
     * @return A wrapped Component class
     */
    @JsOverlay
    public static <P extends BaseProps, S extends JsPlainObj, T extends Component<P, S>> ComponentConstructorFn<P> observer(Class<T> type) {
        return observer(ComponentUtils.getCtorFn(type));
    }

    public static native <P extends BaseProps> ComponentConstructorFn<P> observer(ComponentConstructorFn<P> componentConstructorFn);

    /**
     * The observer function can be used to turn ReactJS stateless components into reactive
     * components. It wraps the component's render function in mobx.autorun to make sure
     * that any data that is used during the rendering of a component forces a re-rendering
     * upon change.
     *
     * <p>Tip: when observer needs to be combined with other higher-order-functions, make sure
     * that observer is the innermost (first applied) function; otherwise it might do nothing
     * at all.</p>
     *
     * @param statelessComponent The stateless component to make an observer
     * @param <P> The prop type
     * @param <C> The context type
     * @return A wrapped StatelessComponent
     */
    public static native <P extends BaseProps, C extends BaseContext> StatelessComponent<P> observer(StatelessComponent<P> statelessComponent);

}
