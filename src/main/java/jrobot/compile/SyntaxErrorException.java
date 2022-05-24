package jrobot.compile;

public class SyntaxErrorException extends Exception {
    public SyntaxErrorException(String errorMsg) {
        super(errorMsg);
    }

    public SyntaxErrorException() {
        this("Error syntax");
    }
}
