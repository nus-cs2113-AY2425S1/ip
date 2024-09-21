package erika.exception;

public class ErikaException extends RuntimeException {
    public ErikaException(String message) {
        super(message);
    }
    public String getMessage() {
        return super.getMessage();
    }
}
