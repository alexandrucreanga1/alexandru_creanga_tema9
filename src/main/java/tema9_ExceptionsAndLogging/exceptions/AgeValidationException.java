package tema9_ExceptionsAndLogging.exceptions;

public class AgeValidationException extends Exception{

    public AgeValidationException(String message) {
        super(message);
    }
}
