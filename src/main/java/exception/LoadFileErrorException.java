package exception;

public class LoadFileErrorException extends BentoException {
    public static final String FILE_PATH = "./data/save.txt";
    public static final String LOAD_FILE_ERROR_MESSAGE = "\tHmm... I can't seem to find a save file here. Daijobu! I'll create a new one here: " + FILE_PATH + "\n";

    public LoadFileErrorException() {
        super(LOAD_FILE_ERROR_MESSAGE);
    }
}
