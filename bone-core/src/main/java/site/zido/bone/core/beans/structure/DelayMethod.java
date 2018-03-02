package site.zido.bone.core.beans.structure;

import site.zido.bone.core.utils.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * site.zido.core.beans.structure
 *
 * @author zido
 */
public class DelayMethod {
    private String[] paramNames;
    private Class<?>[] paramTypes;
    private Method method;
    private Object target;

    public Object execute(Object... params) {
        return ReflectionUtils.execute(method, target, params);
    }

    public String[] getParamNames() {
        return paramNames;
    }

    public void setParamNames(String[] paramNames) {
        this.paramNames = paramNames;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }
}
