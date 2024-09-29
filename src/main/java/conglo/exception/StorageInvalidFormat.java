package conglo.exception;

/**
 * Exception thrown when the storage file contains an invalid task format.
 */
public class StorageInvalidFormat extends CongloException {

    /**
     * Constructs a StorageInvalidFormat exception with the specified invalid line.
     *
     * @param line The line from the storage file that contains an invalid task format.
     */
    public StorageInvalidFormat(String line) {
        super("Invalid task format: " + line);
    }
}
