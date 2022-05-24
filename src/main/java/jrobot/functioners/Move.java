package jrobot.functioners;

import java.awt.Robot;
import java.util.stream.Stream;

import jrobot.compile.SyntaxErrorException;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;

public class Move implements Functioner {

    @Override
    public void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager) {
        try {
            robot.mouseMove((int)cmdContext.getParameters()[0], (int)cmdContext.getParameters()[1]);
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommandContext parse(String command, String parameters)
            throws SyntaxErrorException {
        return new CommandContext(command, Stream.of(parameters.split(",")).map(Integer::parseInt).toArray());
    }

    @Override
    public String getCommandName() {
        return "MOVE";
    }
    
}
