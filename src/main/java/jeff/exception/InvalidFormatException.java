package jeff.exception;

public class InvalidFormatException extends Exception {
    //Lets the user know which task type caused the exception
    public InvalidFormatException(String formatError) {
        super(formatError);
    }
}
