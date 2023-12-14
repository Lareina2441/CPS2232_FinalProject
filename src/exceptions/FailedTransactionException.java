package exceptions;

public class FailedTransactionException extends Exception{
    private String message;
    public FailedTransactionException(String message) {
        super(message);
        this.message = message;
    }
}
