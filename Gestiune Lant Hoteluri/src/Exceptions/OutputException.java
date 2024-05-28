package Exceptions;

public class OutputException extends Exception {
    public OutputException() {
        super();
    }
    public OutputException(String message) {
        super("ERROR :: Output Exception :: " + message);
    }
    public OutputException(String message, Throwable cause) {
        super("ERROR :: Output Exception :: " + message, cause);
    }
    public OutputException(Throwable cause) {
        super(cause);
    }
}
