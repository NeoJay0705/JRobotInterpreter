package jrobot.runtime;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import jrobot.functioners.Functioner;

public class CommandFunctionManager implements FunctionManager {

    private Map<String, Functioner> handlers;

    public CommandFunctionManager() {
        this.handlers = new HashMap<>();
    }

    @Override
    public void register(Class<Functioner> functionerClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        this.register(functionerClass.getConstructor().newInstance());
    }

    @Override
    public void register(Functioner funcionInstance) {
        this.handlers.put(funcionInstance.getCommandName(), funcionInstance);
    }

    @Override
    public Optional<Functioner> getHandler(String commandName) {
        return Optional.ofNullable(this.handlers.get(commandName));
    }
    
}
