package eva.exception;

/**
 * Represents exception for the Eva application.
 * This exception is thrown to indicate errors in the Eva application.
 */
public class EvaException extends Exception {

    public EvaException(String message) {
        super(message);
    }

    /**
     * Validates that the given task number is within the valid range.
     * Throws a {@link EvaException} if the task number is out of range.
     *
     * @param taskNumber The task number to be validated.
     * @param count The total number of tasks available in user's task list.
     * @throws EvaException if task number is less than 0 or more than the total task count.
     */
    public static void validateTaskNumber(int taskNumber, int count) throws EvaException {
        if (taskNumber < 0 || taskNumber >= count) {
            throw new EvaException("Oh no! The task number you provided is out of range.\n" +
                    "Please provide a valid task number between 1 and " + count + ".");
        }
    }
}
