/**
 * Represents a custom exception for the Eva application.
 * This exception is thrown to indicate errors related to
 * the task management operations in the Eva application.
 * It extends the built-in Exception class.
 */
public class EvaException extends Exception {
    public EvaException(String message) {
        super(message);
    }

    public static void validateTaskNumber(int taskNumber, int count) throws EvaException {
        if (taskNumber < 0 || taskNumber >= count) {
            throw new EvaException("Oh no! The task number you provided is out of range.\n" +
                    "Please provide a valid task number between 1 and " + count + ".");
        }
    }
}
