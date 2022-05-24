package jrobot.functioners.t;

import java.awt.Robot;

import jrobot.compile.SyntaxErrorException;
import jrobot.functioners.Functioner;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;

public class PressCreatePDF implements Functioner {

    @Override
    public void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager) {
        Object[] xy = new Object[2];

        xy[0] = 650; xy[1] = 540;
        functionManager.getHandler("MOVE").ifPresent(handler -> handler.function(robot, new CommandContext("MOVE", xy), functionManager, ctxManager));
        functionManager.getHandler("CLICK").ifPresent(handler -> handler.function(robot, null, functionManager, ctxManager));
    }

    @Override
    public CommandContext parse(String command, String parameters)
            throws SyntaxErrorException {
        return null;
    }

    @Override
    public String getCommandName() {
        return "PRINT";
    }
    
}
