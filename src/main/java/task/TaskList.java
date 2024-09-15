package task;

import java.util.ArrayList;

public class TaskList {
    private int taskNumber;
    private Task[] tasks;

    //Constructor for task.TaskList
    public TaskList(){
        taskNumber = 0;

        //Assume there will be no more than 100 tasks
        tasks = new Task[100];
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Storage of new task in the task list.
     *
     * @param task Name of the task is added.
     */
    public void storeTask(Task task) {
        if (taskNumber >= tasks.length) {
            return;
        }
        tasks[taskNumber] = task;
        taskNumber += 1;
    }

    /**
     * Displays all tasks in the array.
     *
     * @return A string representing all the tasks.
     */
    public String displayTasks() {
        String taskList = "";
        for (int i = 0; i < taskNumber; i += 1) {
            taskList += (i + 1) + ". " + tasks[i] + "\n";
        }
        return taskList.toString();
    }

    /**
     * Marks the task as done.
     *
     * @param index The task number to be marked as done.
     * @return A confirmation message.
     */
    public String markTaskAsDone(int index) {
        if (index < 1 || index > taskNumber) {
            return "Invalid task number.";
        }
        Task task = tasks[index - 1];
        task.setAsDone();
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Marks the task as undone.
     *
     * @param index The task number to be marked as undone.
     * @return A confirmation message.
     */
    public String markTaskAsNotDone(int index) {
        if (index < 1 || index > taskNumber) {
            return "Invalid task number.";
        }
        Task task = tasks[index - 1];
        task.setAsUndone();
        return "OK, I've marked this task as not done yet:\n" + task;
    }
}
