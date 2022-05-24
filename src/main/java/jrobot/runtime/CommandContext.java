package jrobot.runtime;

public class CommandContext {
    private String commandName;
    private Object[] parameters;

    public CommandContext(String commandName) {
        this(commandName, null);
    }

    public CommandContext(String commandName, Object[] parameters) {
        this.commandName = commandName;
        this.parameters = parameters;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public Object[] getParameters() {
        return this.parameters;
    }
}
