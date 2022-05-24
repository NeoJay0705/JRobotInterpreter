package jrobot.functioners.t;

import java.awt.Robot;

import jrobot.compile.SyntaxErrorException;
import jrobot.functioners.Functioner;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;

public class PinPad implements Functioner {

    @Override
    public void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager) {
        Object[] xy = new Object[2];

        xy[0] = 600; xy[1] = 500;
        functionManager.getHandler("MOVE").ifPresent(handler -> handler.function(robot, new CommandContext("MOVE", xy), functionManager, ctxManager));
        functionManager.getHandler("CLICK").ifPresent(handler -> handler.function(robot, null, functionManager, ctxManager));
        xy[0] = 650; xy[1] = 500;
        functionManager.getHandler("MOVE").ifPresent(handler -> handler.function(robot, new CommandContext("MOVE", xy), functionManager, ctxManager));
        functionManager.getHandler("CLICK").ifPresent(handler -> handler.function(robot, null, functionManager, ctxManager));
        xy[0] = 700; xy[1] = 500;
        functionManager.getHandler("MOVE").ifPresent(handler -> handler.function(robot, new CommandContext("MOVE", xy), functionManager, ctxManager));
        functionManager.getHandler("CLICK").ifPresent(handler -> handler.function(robot, null, functionManager, ctxManager));
        xy[0] = 600; xy[1] = 530;
        functionManager.getHandler("MOVE").ifPresent(handler -> handler.function(robot, new CommandContext("MOVE", xy), functionManager, ctxManager));
        functionManager.getHandler("CLICK").ifPresent(handler -> handler.function(robot, null, functionManager, ctxManager));
        xy[0] = 700; xy[1] = 600;
        functionManager.getHandler("MOVE").ifPresent(handler -> handler.function(robot, new CommandContext("MOVE", xy), functionManager, ctxManager));
        functionManager.getHandler("CLICK").ifPresent(handler -> handler.function(robot, null, functionManager, ctxManager));
    }

    @Override
    public CommandContext parse(String command, String parameters)
            throws SyntaxErrorException {
        return new CommandContext(command, null);
    }

    @Override
    public String getCommandName() {
        return "PINPAD";
    }
    
}
