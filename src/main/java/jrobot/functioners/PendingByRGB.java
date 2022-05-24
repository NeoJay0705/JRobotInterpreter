package jrobot.functioners;

import java.awt.Robot;
import java.util.Arrays;
import java.util.stream.Stream;

import jrobot.compile.SyntaxErrorException;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;
import jrobot.tools.ColorChecker;

public class PendingByRGB implements Functioner {

    @Override
    public void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager) {
        int[] params = Arrays.stream(cmdContext.getParameters()).mapToInt(Integer.class::cast).toArray();
        
        ColorChecker colorChecker = new ColorChecker(params[0], params[1], params[2], params[3], params[4]);
        int isEqual = params[5];
        int pollingTime = params[6];

        while (isEqual == 1 ? colorChecker.equals(robot.getPixelColor(colorChecker.X, colorChecker.Y).getRGB()) : !colorChecker.equals(robot.getPixelColor(colorChecker.X, colorChecker.Y).getRGB())) {
            try {
                Thread.sleep(pollingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public CommandContext parse(String command, String parameters)
            throws SyntaxErrorException {
        return new CommandContext(command, Stream.of(parameters.split(",")).map(Integer::parseInt).toArray());
    }

    @Override
    public String getCommandName() {
        return "PENDBYRGB";
    }
    
}
