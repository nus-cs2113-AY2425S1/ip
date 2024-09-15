package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    //Constructor for task.TaskList
    public TaskList(){
        tasks = new ArrayList<>();
    }

    public int getTaskNumber() {
        return tasks.size();
    }

    /**
     * Storage of new task in the task list.
     *
     * @param task Name of the task is added.
     */
    public void storeTask(Task task) {
        tasks.add(task);
    }

    /**
     * Displays all tasks in the array.
     *
     * @return A string representing all the tasks.
     */
    public String displayTasks() {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i += 1) {
            taskList += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return taskList;
    }

    /**
     * Marks the task as done.
     *
     * @param index The task number to be marked as done.
     * @return A confirmation message.
     */
    public String markTaskAsDone(int index) {
        if (index < 1 || index > tasks.size()) {
            return "Invalid task number.";
        }
        Task task = tasks.get(index - 1);
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
        if (index < 1 || index > tasks.size()) {
            return "Invalid task number.";
        }
        Task task = tasks.get(index - 1);
        task.setAsUndone();
        return "OK, I've marked this task as not done yet:\n" + task;
    }
}
