package gwt.mobx.client;

import gwt.react.client.components.ReactClass;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseContext;
import gwt.react.client.proptypes.BaseProps;
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
     * used during the rendering of a component forces a re-rendering upon change. It is
     * available through the separate mobx-react package.
     *
     * <p>Tip: when observer needs to be combined with other higher-order-components, make sure
     * that observer is the innermost (first applied) function; otherwise it might do nothing
     * at all.</p>
     *
     * @param reactClass The react class to make an observer
     * @param <P> The prop types
     * @return A wrapped ReactClass
     */
    public static native <P extends BaseProps> ReactClass<P> observer(ReactClass<P> reactClass);

    /**
     * The observer function can be used to turn ReactJS stateless components into reactive
     * components. It wraps the component's render function in mobx.autorun to make sure
     * that any data that is used during the rendering of a component forces a re-rendering
     * upon change. It is available through the separate mobx-react package.
     *
     * <p>Tip: when observer needs to be combined with other higher-order-functions, make sure
     * that observer is the innermost (first applied) function; otherwise it might do nothing
     * at all.</p>
     *
     * @param statelessComponent The stateless component to make an observer
     * @param <P> The prop type
     * @param <C> The context type
     * @return A wrapped StatlessComponent
     */
    public static native <P extends BaseProps, C extends BaseContext> StatelessComponent<P, C> observer(StatelessComponent<P, C> statelessComponent);

}
