package task;
import exception.LeginEmptyTaskException;

/**
 * Stores information on a Todo task
 */
public class Todo extends Task{
    private static final int INCREMENT_TO_START_INDEX = 1;
    /**
     * Retrieves the task description fo the Todo task
     *
     * @param input User input in command line
     * @param isFromLoadFile Set to {@code true} when {@code input} information comes from storage text file
     * @return Description of Todo task
     */
    private static String getTodoDescription(String input, boolean isFromLoadFile) {
        if (isFromLoadFile) {
            return input;
        }
        return input.substring(input.indexOf(" ") + INCREMENT_TO_START_INDEX);
    }

    /**
     * Instantiates a Todo member with its description
     *
     * @param input User input in command line
     * @param isFromLoadFile Set to {@code true} when data comes from storage text file
     * @throws LeginEmptyTaskException If task has no description
     */
    public Todo(String input, boolean isFromLoadFile) throws LeginEmptyTaskException {
        super(getTodoDescription(input, isFromLoadFile));
    }

    /**
     * Formats the Todo information to be stored into the storage text file
     *
     * @return Formatted data
     */
    @Override
    public String getWriteInfo() {
        return "T|" + isDone + "|" + task;
    }

    /**
     * Formats the Todo information to be printed out in the command line
     *
     * @return Formatted data
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}