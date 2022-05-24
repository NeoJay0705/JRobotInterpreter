package jrobot.compile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import jrobot.functioners.Functioner;
import jrobot.runtime.CommandContext;
import jrobot.runtime.FunctionManager;
import jrobot.runtime.FunctionNotFoundException;

/**
 * End command with ";"
 */
public class DefaultScriptParser implements ScriptParser {

    private FunctionManager cmdManager;

    public DefaultScriptParser(FunctionManager cmdManager) {
        this.cmdManager = cmdManager;
    }

    @Override
    public List<CommandContext> parse(String script) throws SyntaxErrorException, FunctionNotFoundException {
        List<CommandContext> commands = new ArrayList<>();

        if (script == null) return commands;

        // Parse out commands
        List<String> rawCommands = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        boolean isPureStringFlag = false;
        for (int i = 0; i < script.length(); i++) {
            if (script.charAt(i) == '#') {
                i = script.indexOf("\n", i);
                continue;
            }

            if (script.charAt(i) == ';' && !isPureStringFlag) {
                rawCommands.add(sb.toString());
                sb.setLength(0);
            } else {
                if (script.charAt(i) == '"' && script.length() > 1 && script.charAt(i - 1) != '\\') {
                    isPureStringFlag = !isPureStringFlag;
                } else {
                    sb.append(script.charAt(i));
                }
            }

        }

        // Build a command-context list
        for (String cmd : rawCommands) {
            cmd = cmd.trim();
            cmd = cmd.replace("\\\\", "tmp/");
            cmd = cmd.replace("\\", "");
            cmd = cmd.replace("tmp/", "\\");
            String[] fetched = cmd.split(" ", 2);

            for (int i = 0; i < fetched.length; i++) {
                fetched[i] = fetched[i].trim();
            }

            Optional<Functioner> functioner = this.cmdManager.getHandler(fetched[0]);
            if (functioner.isPresent()) {
                try {
                    commands.add(functioner.get().parse(fetched[0], fetched.length > 1 ? fetched[1] : null));
                } catch (SyntaxErrorException e) {
                    throw e;
                }
            } else {
                throw new FunctionNotFoundException();
            }
        }

        return commands;
    }
    
}
