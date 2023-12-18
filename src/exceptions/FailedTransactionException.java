package exceptions;

/**
 * Purppose: This exception is thrown when the given argument is not valid
 * @version 1.0
 * @since 2023-12-17
 */

public class FailedTransactionException extends Exception{
    private String message;
    public FailedTransactionException(String message) {
        super(message);
        this.message = message;
    }
}
