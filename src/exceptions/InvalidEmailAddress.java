package exceptions;

/**
 * Purppose: This exception is thrown when the given email address is not valid
 * @version 1.0
 * @since 2023-12-17
 */

public class InvalidEmailAddress extends Exception {
    public InvalidEmailAddress(String message) {
        super(message);
    }
}
