package Exceptions;

public class OutputException extends Exception {
    public OutputException() {
        super();
    }
    public OutputException(String message) {
        super(message);
    }
    public OutputException(String message, Throwable cause) {
        super(message, cause);
    }
    public OutputException(Throwable cause) {
        super(cause);
    }
}
