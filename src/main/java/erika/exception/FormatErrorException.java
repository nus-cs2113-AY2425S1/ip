package erika.exception;

/** Represents the exception thrown when the read file has an incorrect format. */
public class FormatErrorException extends ErikaException {
    public FormatErrorException(String detailedMessage) {
        super("Error: command format error, " + detailedMessage);
    }
}
