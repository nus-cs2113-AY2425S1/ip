package hsien.exception;

public class HsienException extends Exception{
    public HsienException() {
        super();
    }

    // Constructor with a custom error message
    public HsienException(String message) {
        super(message);
    }
}
