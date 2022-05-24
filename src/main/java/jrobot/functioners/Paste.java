package jrobot.functioners;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import jrobot.compile.SyntaxErrorException;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;
import jrobot.tools.ClipBoard;

public class Paste implements Functioner {

    private ClipBoard cp = new ClipBoard();

    @Override
    public void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager) {
        cp.CutBookInit();
        Object content = ctxManager.get((String)cmdContext.getParameters()[0]);
        cp.setBookContents(content == null ? String.valueOf(cmdContext.getParameters()[0]) : String.valueOf(content));
        try {
            Utils.combinationKeys(robot, 10, KeyEvent.VK_CONTROL, 0x56);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommandContext parse(String command, String parameters)
            throws SyntaxErrorException {
        return new CommandContext(command, new Object[] {parameters});
    }

    @Override
    public String getCommandName() {
        return "PASTE";
    }
    
}
