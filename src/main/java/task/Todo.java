package task;
import exception.LeginEmptyTaskException;

public class Todo extends Task{
    private static String getTodoDescription(String input) {
       return input.substring(input.indexOf(" ") + 1);
    }
    public Todo(String input) throws LeginEmptyTaskException {
        super(getTodoDescription(input));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
