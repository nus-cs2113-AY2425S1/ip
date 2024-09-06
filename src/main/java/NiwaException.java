public class NiwaException extends RuntimeException {
    String message;
    public NiwaException() {
        super();
    }
    public NiwaException(String message) {
        super(message);
    }

}
