package Exceptions;

public class CommandException extends Exception {
    public CommandException() {
        super();
    }
    public CommandException(String message) {
        super("ERROR :: Command Exception :: " + message);
    }
    public CommandException(String message, Throwable cause) {
        super("ERROR :: Command Exception :: " + message, cause);
    }
    public CommandException(Throwable cause) {
        super(cause);
    }
}
