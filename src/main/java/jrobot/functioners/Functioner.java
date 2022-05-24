package jrobot.functioners;

import java.awt.Robot;

import jrobot.compile.SyntaxErrorException;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;

public interface Functioner {
    String getCommandName();

    void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager);

    CommandContext parse(final String command, String parameters) throws SyntaxErrorException;
}
