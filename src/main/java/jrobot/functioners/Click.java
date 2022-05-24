package jrobot.functioners;

import java.awt.Robot;
import java.awt.event.InputEvent;

import jrobot.compile.SyntaxErrorException;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;

public class Click implements Functioner {

    @Override
    public void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager) {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(50);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(50);
    }

    @Override
    public CommandContext parse(String command, String parameters)
            throws SyntaxErrorException {
        return new CommandContext(command);
    }

    @Override
    public String getCommandName() {
        return "CLICK";
    }
    
}
