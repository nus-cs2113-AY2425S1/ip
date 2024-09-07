public class EmptyTaskException extends AppalException {
    public static final String EMPTY_TASK_MESSAGE = "Please specify a task, pal!" +
            "\nExample input: [COMMAND] [TASK]";

    public EmptyTaskException() {
        super(EMPTY_TASK_MESSAGE);
    }
}
