package atom.exception;

public class MissingDateException extends RuntimeException {

    @Override
    public String getMessage() {
        String message = "Please enter date to filter by.";
        return message;
    }
}
