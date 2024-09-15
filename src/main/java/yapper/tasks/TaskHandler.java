package yapper.tasks;

import java.util.ArrayList;
import java.util.List;
import yapper.io.OutputStringHandler;
import yapper.io.StringStorage;

// manages Tasks for Yapper
public class TaskHandler {
    private List<Task> tasks;
    
    public TaskHandler(int maxCapacity) {
        tasks = new ArrayList<>();
    }
    // Getters
    public List<Task> getAllTasks() {
        return tasks;
    }
    public int getCurrTaskTotal() {
        return tasks.size();
    }
//    public Task getTask(int taskOrdinal) {
//        return tasks[taskOrdinal];
//    }

    // TaskManager Operations
    public void addTask(Task task) {
        tasks.add(task);
        task.printAddedTask(tasks.size());
    }
    public void updateTaskStatus(int taskOrdinal, boolean isDone) {
        Task task = tasks.get(taskOrdinal);

        boolean isAlreadyInDesiredState =
                ( isDone && task.isDone() ) || ( !isDone && !task.isDone() );
        if (isAlreadyInDesiredState) {
            StringStorage.printWithDividers("This task is already " +
                    (isDone ? "" : "not") + " marked as done" );
            return;
        }

        task.setDoneStatus(isDone);
        OutputStringHandler.printTaskStatus(task, isDone);
    }
}
