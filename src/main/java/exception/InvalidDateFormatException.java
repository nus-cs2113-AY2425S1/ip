package exception;

/**
 * Represents an exception thrown when the user provides an invalid date format to Bento.
 * This class extends {@link BentoException} and handles errors related to invalid date formatting.
 * It prompts the user to use the correct date format of "dd/MM/yyyy".
 */
public class InvalidDateFormatException extends BentoException {
    /** The default message displayed when the user provides an incorrectly formatted date. */
    public static final String INVALID_DATE_FORMAT_MESSAGE = "\tHmm... That doesn't seem quite right. " +
            "Make sure your date is of the form dd/MM/yyyy!\n";

    /**
     * Constructs a new InvalidDateFormatException with the default error message.
     */
    public InvalidDateFormatException() {
        super(INVALID_DATE_FORMAT_MESSAGE);
    }
}
