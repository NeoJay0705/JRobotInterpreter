package jrobot;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import jrobot.compile.DefaultScriptParser;
import jrobot.compile.ScriptParser;
import jrobot.compile.SyntaxErrorException;
import jrobot.functioners.Click;
import jrobot.functioners.ComKey;
import jrobot.functioners.Delay;
import jrobot.functioners.Elements;
import jrobot.functioners.LocalVariable;
import jrobot.functioners.Loop;
import jrobot.functioners.Move;
import jrobot.functioners.Paste;
import jrobot.functioners.PendingByRGB;
import jrobot.functioners.t.PinPad;
import jrobot.functioners.t.PressCreatePDF;
import jrobot.runtime.CommandContext;
import jrobot.runtime.CommandFunctionManager;
import jrobot.runtime.FunctionManager;
import jrobot.runtime.FunctionNotFoundException;
import jrobot.runtime.ProgramCounter;
import jrobot.runtime.ContextManager;
import jrobot.runtime.DefaultContextManager;

public class ScalableRobotRunner {

    private ContextManager variableManager;
    private FunctionManager functionManager;
    private ScriptParser scriptParser;
    private Robot robot;

    public ScalableRobotRunner(FunctionManager functionManager, ScriptParser scriptParser, ContextManager variableManager) throws AWTException {
        this.functionManager = functionManager;
        this.scriptParser = scriptParser;
        this.variableManager = variableManager;

        this.robot = new Robot();
    }

    public void startRobot(String script) {
        try {
            List<CommandContext> commands = scriptParser.parse(script);
            ProgramCounter pc = new ProgramCounter(commands.size());
            
            for (this.variableManager.put(ProgramCounter.VARNAME, pc); pc.uncompleted();) {
                // final int runtimeIndex = (int)this.variableManager.get("runtimeIndex");
                
                CommandContext commandContext = commands.get(pc.getCounter());
                
                functionManager.getHandler(commandContext.getCommandName())
                    .ifPresent(handler -> {
                        handler.function(this.robot, commandContext, functionManager, this.variableManager);
                    });
                
                // PC++
                pc.increament();
                // this.variableManager.put("runtimeIndex", (int)this.variableManager.get("runtimeIndex") + 1);
                
                Termination.mouseOnLeftTop();
            }
        } catch (SyntaxErrorException | FunctionNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws AWTException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        
        FunctionManager functionManager = new CommandFunctionManager();
        functionManager.register(new ComKey());
        functionManager.register(new Click());
        functionManager.register(new Delay());
        functionManager.register(new Move());
        functionManager.register(new Paste());
        functionManager.register(new PendingByRGB());
        functionManager.register(new PinPad());
        functionManager.register(new PressCreatePDF());
        functionManager.register(new LocalVariable());
        functionManager.register(new Elements());
        functionManager.register(new Loop());

        ScalableRobotRunner scalableRobotRunner = new ScalableRobotRunner(functionManager, new DefaultScriptParser(functionManager), new DefaultContextManager());

        File dir = new File("testFile\\");
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getAbsolutePath().endsWith(".txt")) {
                    System.out.println("****filename=" + file.getAbsolutePath());
                    
                    try {
                        String script = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                        scalableRobotRunner.startRobot(script);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
    }
}
