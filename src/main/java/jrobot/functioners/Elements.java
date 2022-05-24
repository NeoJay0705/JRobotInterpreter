package jrobot.functioners;

import java.awt.Robot;
import java.util.Arrays;
import java.util.List;

import jrobot.compile.SyntaxErrorException;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;

public class Elements implements Functioner {

    @Override
    public void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager) {
        List<Object> elements = Arrays.asList(Arrays.copyOfRange(cmdContext.getParameters(), 1, cmdContext.getParameters().length));
        ctxManager.put((String)cmdContext.getParameters()[0], elements);
    }

    @Override
    public CommandContext parse(String command, String parameters)
            throws SyntaxErrorException {
        String[] varWithElements = parameters.split("=", 2);
        Object[] elements = varWithElements[1].split(",");
        Object[] formated = new Object[elements.length + 1];
        formated[0] = varWithElements[0];
        for (int i = 1; i < formated.length; i++) {
            formated[i] = elements[i - 1];
        }
        return new CommandContext(command, formated);
    }

    @Override
    public String getCommandName() {
        return "ARR";
    }

}
