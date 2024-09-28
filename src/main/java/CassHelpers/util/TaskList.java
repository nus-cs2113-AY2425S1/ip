package CassHelpers.util;

import CassHelpers.commands.*;
import CassHelpers.exceptions.*;
import CassHelpers.types.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    private boolean isRunning = true;

    public TaskList(Storage storage) {
        this.taskList = new ArrayList<>();
        this.storage = storage;
    }

    public TaskList(ArrayList<Task> taskList, Storage storage) {

        this.taskList = taskList;
        this.storage = storage;
    }

    public boolean getRunningState() {
        return this.isRunning;
    }

    public void setRunningState(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }
}
