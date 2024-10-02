package atom.exception;

public class EmptyDeadlineException extends RuntimeException {

    @Override
    public String getMessage() {
        String message = "Huh?? Cannot identify deadline task..";
        return message;
    }
}
