package jrobot.runtime;

public class FunctionNotFoundException extends Exception {
    public FunctionNotFoundException(String errorMsg) {
        super(errorMsg);
    }

    public FunctionNotFoundException() {
        this("Function Not Found");
    }
}
