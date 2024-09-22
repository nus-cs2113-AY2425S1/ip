package niwa.exception;

public class NiwaException extends Exception {
    String message;
    public NiwaException() {
        super();
    }
    public NiwaException(String message) {
        super(message);
    }

}
