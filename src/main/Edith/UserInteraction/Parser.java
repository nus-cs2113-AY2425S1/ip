package UserInteraction;

import TaskTypes.Task;
import java.util.ArrayList;
import static UserInteraction.TaskList.*;


public class Parser {
    public static boolean understandUser(TaskList tasks, String enteredString, Storage storage) {
        if (enteredString.equals("bye")) {
            return true;
        } else if (enteredString.equals("list")) {
            tasks.listTasks();
        } else if (enteredString.contains("mark")) {
            tasks.changeTaskStatus(enteredString, storage);
        } else if (enteredString.contains("delete")) {
            tasks.deleteTask(enteredString, storage);
        } else {
            tasks.addTaskToList(enteredString, storage);
        }
        return false;
    }
}
