package atom.exception;

public class InvalidDeadlineFormatException extends RuntimeException {

    @Override
    public String getMessage() {
        String message = "Incorrect command format!! Try again..\n" +
                "\nMaybe this might be of assistance:" +
                "\n-> \"deadline <task> /by <date/time>\"";

        return message;
    }
}
