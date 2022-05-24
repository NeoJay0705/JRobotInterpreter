package jrobot.runtime;

public class ProgramCounter {
    public static String VARNAME = "PC";

    private int counter;
    private int cmdSize;

    public ProgramCounter(int cmdSize) {
        this.counter = 0;
        this.cmdSize = cmdSize;
    }

    public int getCounter() {
        return this.counter;
    }

    public void increament() {
        this.counter++;
    }

    public void setCounter(int at) {
        this.counter = at;
    }

    public boolean uncompleted() {
        return this.counter < cmdSize;
    }
}
