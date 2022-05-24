package jrobot.runtime;

public interface ContextManager {
    Object get(String var);
    void put(String var, Object value);
}
