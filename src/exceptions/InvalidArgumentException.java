package exceptions;

/**
 * Purppose: This exception is thrown when the given argument is not valid
 * @version 1.0
 * @since 2023-12-17
 */

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String message) {
        super(message);
    }
}

