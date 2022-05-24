package jrobot.functioners;

import java.awt.Robot;
import java.util.List;

import jrobot.compile.SyntaxErrorException;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;
import jrobot.runtime.LoopCounter;
import jrobot.runtime.ProgramCounter;

public class Loop implements Functioner {

    @Override
    public void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager) {
        // Type of loop: iteration, constant, if
        
        Object[] params = cmdContext.getParameters();
        String loopType = (String)params[0];
        ProgramCounter pc = (ProgramCounter)ctxManager.get(ProgramCounter.VARNAME);
        LoopCounter lc = (LoopCounter)ctxManager.get(LoopCounter.VARNAME);
        if (lc == null) {
            lc = new LoopCounter();
            ctxManager.put(LoopCounter.VARNAME, lc);
        }
        switch(loopType) {
        case "CONST":
            lc.newRound(pc.getCounter(), Integer.parseInt((String)params[1]));
            break;
        case "IN":
            List<Object> elements = (List)ctxManager.get((String)params[1]);
            lc.newRound(pc.getCounter(), elements.size());
            break;
//        case "WHILE":
//            
//            break;
        case "END":
            int gotoIndex = lc.endRound();
            if (gotoIndex != -1) {
                pc.setCounter(gotoIndex);
            }
            break;
        default:
            
        }
    }

    @Override
    public CommandContext parse(String command, String parameters)
            throws SyntaxErrorException {
        return new CommandContext(command, parameters.split(","));
    }

    @Override
    public String getCommandName() {
        return "LOOP";
    }

}
