package jrobot.runtime;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import jrobot.functioners.Functioner;

public interface FunctionManager {
    void register(Class<Functioner> functionerClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException ;
    void register(Functioner funcionInstance);
    Optional<Functioner> getHandler(String commandName);
}
