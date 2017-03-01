package gwt.mobx.client;

import gwt.react.client.components.ComponentConstructorFn;
import gwt.react.client.proptypes.BaseProps;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class MobXDevTools {
    @JsProperty(name = "default")
    public static ComponentConstructorFn<BaseProps> component;
}
