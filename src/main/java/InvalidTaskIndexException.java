public class InvalidTaskIndexException extends AppalException {
    public static final String INVALID_TASK_INDEX_MESSAGE = "Please input a valid task number, pal!" +
            "\nType 'list' to see your tasks and each task's corresponding number!";

    public InvalidTaskIndexException() {
        super(INVALID_TASK_INDEX_MESSAGE);
    }
}
