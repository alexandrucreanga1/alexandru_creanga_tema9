package tema9_ExceptionsAndLogging.exceptions;

public class IncorrectSearchException extends Exception{

    public IncorrectSearchException(String message) {
        super(message);
    }
}
