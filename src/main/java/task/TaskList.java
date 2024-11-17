package task;

import java.util.ArrayList;
/**
 * The TaskList class is a specialized version of {@link ArrayList} that stores
 * {@link Task} objects. It provides a collection of tasks that can be manipulated
 * during the program's execution and saved/loaded from storage.
 *
 * This class extends {@link ArrayList}, inheriting its methods for list manipulation.
 *
 * @author Tan Ping Hui
 */
public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }
    public TaskList(TaskList tasks) {
        super(tasks);  // Initialise with data in storage
    }
}
