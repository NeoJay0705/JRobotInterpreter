package jrobot.compile;

import java.util.List;

import jrobot.runtime.CommandContext;
import jrobot.runtime.FunctionNotFoundException;

public interface ScriptParser {
    List<CommandContext> parse(String script) throws SyntaxErrorException, FunctionNotFoundException;
}
