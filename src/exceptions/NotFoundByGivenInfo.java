package exceptions;

/**
 * Purppose: This exception is thrown when the given info is not found in the database
 * @version 1.0
 * @since 2023-12-17
 */

public class NotFoundByGivenInfo extends Exception{
    public NotFoundByGivenInfo(String message) {
        super(message);
    }
}
