package UranusExceptions;

public class EmptyDescriptionException extends UranusExceptions {
    public EmptyDescriptionException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Description cannot be empty!";
    }
}
