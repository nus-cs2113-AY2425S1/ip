package exception;

/**
 * Represents an exception thrown when there is an error loading the save file for Bento.
 * This class extends {@link BentoException} and is used to notify the user when a save file cannot
 * be found or accessed. A new file will be created if the save file is missing.
 */

public class LoadFileErrorException extends BentoException {
    /** The default file path for the save file. */
    public static final String FILE_PATH = "./data/save.txt";

    /** The default message displayed when the save file cannot be loaded. */
    public static final String LOAD_FILE_ERROR_MESSAGE = "\tHmm... I can't seem to find a save file here. " +
            "Daijobu! I'll create a new one here: " + FILE_PATH + "\n";

    /**
     * Constructs a new LoadFileErrorException with the default error message.
     */
    public LoadFileErrorException() {
        super(LOAD_FILE_ERROR_MESSAGE);
    }
}
