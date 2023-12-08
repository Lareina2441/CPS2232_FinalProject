package exceptions;

public class InvalidEmailAddress extends Exception {
    public InvalidEmailAddress(String message) {
        super(message);
    }
}
