package AlyBot;

public class AlyException extends Exception {
    public AlyException(String message) {
        super(message);
    }

    public AlyException(String message, Throwable cause) {
        super(message, cause);
    }
}
