package yapper.tasks;

import yapper.io.OutputStringHandler;
import yapper.io.StringStorage;

// manages Tasks for Yapper
public class TaskHandler {
    private Task[] tasks;
    private int currTaskTotal;

    // Constructor and Getters
    public TaskHandler(int maxCapacity) {
        tasks = new Task[maxCapacity];
        currTaskTotal = 0;
    }
    public Task[] getAllTasks() {
        return tasks;
    }
    public int getCurrTaskTotal() {
        return currTaskTotal;
    }
//    public Task getTask(int taskOrdinal) {
//        return tasks[taskOrdinal];
//    }

    // TaskManager Operations
    public void addTask(Task task) {
        tasks[currTaskTotal] = task;
        currTaskTotal++;
        task.printAddedTask(currTaskTotal);
    }
    public void updateTaskStatus(int taskOrdinal, boolean isDone) {
        Task task = tasks[taskOrdinal];

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
