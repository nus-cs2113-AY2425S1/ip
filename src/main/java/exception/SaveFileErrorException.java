package exception;

public class SaveFileErrorException extends BentoException {
    public static final String SAVE_FILE_ERROR_MESSAGE = "\tSomething went wrong with saving the file! Sumimasen!\n";

    public SaveFileErrorException() {
        super(SAVE_FILE_ERROR_MESSAGE);
    }
}
