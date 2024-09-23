package exception;

/**
 * Represents an exception thrown when there is an error saving the file for the Bento chatbot.
 * This class extends {@link BentoException} and is used to notify the user when an error occurs
 * during the file saving process.
 */
public class SaveFileErrorException extends BentoException {
    /** The default message displayed when there is an error saving the file. */
    public static final String SAVE_FILE_ERROR_MESSAGE = "\tSomething went wrong with saving the file! Sumimasen!\n";

    /**
     * Constructs a new SaveFileErrorException with the default error message.
     */
    public SaveFileErrorException() {
        super(SAVE_FILE_ERROR_MESSAGE);
    }
}
