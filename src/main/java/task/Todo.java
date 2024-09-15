package task;
import exception.LeginEmptyTaskException;

public class Todo extends Task{
    private static String getTodoDescription(String input, boolean isFromLoadFile) {
        if (isFromLoadFile) {
            return input;
        }
        return input.substring(input.indexOf(" ") + 1);
    }
    public Todo(String input, boolean isFromLoadFile) throws LeginEmptyTaskException {
        super(getTodoDescription(input, isFromLoadFile));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
