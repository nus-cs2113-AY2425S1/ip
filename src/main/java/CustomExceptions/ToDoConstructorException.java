package CustomExceptions;
import TaskChildren.Task;

public class ToDoConstructorException extends Exception{
    public ToDoConstructorException(String message) {
        super("TODO CONSTRUCTOR EXCEPTION: " + errorMessage(message));
        Task.deleteLatestTask();
    }
    private static String errorMessage(String message) {

        String taskString = message.replace("todo ", "");
        if (taskString.isEmpty()) {
            return "MISSING TASK STATEMENT";
        }
        return "UNKNOWN ERROR";
    }
}
