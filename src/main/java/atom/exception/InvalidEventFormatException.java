package atom.exception;

public class InvalidEventFormatException extends RuntimeException {

    @Override
    public String getMessage() {
        String message = "Incorrect command format!! Try again..\n" +
                "\nMaybe this might be of assistance:" +
                "\n-> \"event <task> /from <date/time> /to <date/time>\"";

        return message;
    }
}
