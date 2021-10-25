package tema9_ExceptionsAndLogging.exceptions;

public class EmptyInputException extends Exception{

    public EmptyInputException(String message) {
        super(message);
    }
}
