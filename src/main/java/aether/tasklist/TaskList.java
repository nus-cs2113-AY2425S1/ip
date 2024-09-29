package aether.tasklist;

import aether.task.Task;
import aether.ui.Ui;

import java.util.ArrayList;

/**
 * Manages the task list and task operations.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            Ui.response("Task list is empty.");
        } else {
            Ui.response("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        Ui.response("Added task: " + task);
    }

    public void deleteTask(int index) {
        Task task = tasks.remove(index);
        Ui.response("Removed task: " + task);
    }

    public void markTaskStatus(int index, boolean isMark) {
        Task task = tasks.get(index);
        task.setDone(isMark);
        String status = isMark ? "Done" : "Not done";
        Ui.response("Task " + (index + 1) + " marked as " + status + ": " + task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
