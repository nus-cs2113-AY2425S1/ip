package Tasks;

import java.util.ArrayList;

public class TaskList {
    private final int DEFAULT_TASKS = 100;
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>(DEFAULT_TASKS);
    }

    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

}