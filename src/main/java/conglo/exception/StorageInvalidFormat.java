package conglo.exception;

public class StorageInvalidFormat extends CongloException {
    public StorageInvalidFormat(String line) {
        super("Invalid task format: " + line);
    }
}
