package task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }
    public TaskList(TaskList tasks) {
        super(tasks);  // Initialise with data in storage
    }
}
