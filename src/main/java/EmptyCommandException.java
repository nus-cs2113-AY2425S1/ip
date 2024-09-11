public class EmptyCommandException extends UranusExceptions {
    public EmptyCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Description cannot be empty!";
    }
}
