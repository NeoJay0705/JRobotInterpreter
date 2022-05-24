package jrobot.functioners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.KeyEvent;
import java.awt.Robot;

import jrobot.compile.SyntaxErrorException;
import jrobot.runtime.CommandContext;
import jrobot.runtime.ContextManager;
import jrobot.runtime.FunctionManager;

public class ComKey implements Functioner {

    private Map<String, Integer> keycodeMap;

    public ComKey() {
        this.keycodeMap = new HashMap<>();
        init();
    }

    private void init() {
        keycodeMap.put("TAB", KeyEvent.VK_TAB);
        keycodeMap.put("ENTER", KeyEvent.VK_ENTER);
        keycodeMap.put("ESC", KeyEvent.VK_ESCAPE);
        keycodeMap.put("F8", KeyEvent.VK_F8);
        keycodeMap.put("DEL", KeyEvent.VK_DELETE);
        keycodeMap.put("LEFT", KeyEvent.VK_LEFT);
        keycodeMap.put("RIGHT", KeyEvent.VK_RIGHT);
        keycodeMap.put("END", KeyEvent.VK_END);
        keycodeMap.put("HOME", KeyEvent.VK_HOME);
        keycodeMap.put("SHIFT", KeyEvent.VK_SHIFT);
        keycodeMap.put("CTRL", KeyEvent.VK_CONTROL);
        keycodeMap.put("BACKSPACE", KeyEvent.VK_BACK_SPACE);
    }

    

    @Override
    public void function(Robot robot, CommandContext cmdContext, FunctionManager functionManager, ContextManager ctxManager) {
        int[] params = Arrays.stream(Arrays.copyOfRange(cmdContext.getParameters(), 1, cmdContext.getParameters().length)).mapToInt(i -> (Integer) i).toArray();

        try {
            Utils.combinationKeys(robot, (int)cmdContext.getParameters()[0], params);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommandContext parse(String command, String parameters)
            throws SyntaxErrorException {
        String[] parsed = parameters.split(",");
        Object[] params = new Object[parsed.length];
        params[0] = Integer.parseInt(parsed[0]);
        List<Object> params1 = new ArrayList<>();
        params1.add(Integer.parseInt(parsed[0]));
        for (int i = 1; i < parsed.length; i++) {
            if (keycodeMap.containsKey(parsed[i])) 
                params1.add(keycodeMap.get(parsed[i]));
            else {
                // String to key value
                parsed[i].chars().forEach(c -> params1.add(c));
            }
        }
        return new CommandContext(command, params1.toArray());
    }

    @Override
    public String getCommandName() {
        return "COMKEY";
    }
    
}
