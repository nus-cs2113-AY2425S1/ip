package Yukee.exception;

public class EmptyDescriptionException extends YukeeException {
    public EmptyDescriptionException(String taskType) {
        super("OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}
