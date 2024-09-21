package erika.exception;

public class FormatErrorException extends ErikaException {
    public FormatErrorException(String detailedMessage) {
        super("Error: command format error, " + detailedMessage);
    }
}
