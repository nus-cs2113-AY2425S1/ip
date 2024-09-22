package erika.exception;
/**
 * Represents the exception thrown when the read file has an incorrect format
 */
public class FormatErrorException extends ErikaException {
    /**
     * @param detailedMessage A <code>String</code> detailed message regarding the possible sources of this error
     */
    public FormatErrorException(String detailedMessage) {
        super("Error: command format error, " + detailedMessage);
    }
}
