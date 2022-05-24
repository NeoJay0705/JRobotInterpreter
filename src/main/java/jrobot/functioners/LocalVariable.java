package jrobot.functioners;

import java.awt.Robot;

import jrobot.compile.SyntaxErrorException;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;

public class LocalVariable implements Functioner {

    @Override
    public void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager) {
        ctxManager.put((String)cmdContext.getParameters()[0], (String)cmdContext.getParameters()[1]);
    }

    @Override
    public CommandContext parse(String command, String parameters)
            throws SyntaxErrorException {
        return new CommandContext(command, parameters.split("=", 2));
    }

    @Override
    public String getCommandName() {
        return "VAR";
    }

}
