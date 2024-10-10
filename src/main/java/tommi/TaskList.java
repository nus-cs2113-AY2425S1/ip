package tommi;

import tommi.Task.Task;

import java.util.ArrayList;

public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();  // Array to store tasks

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void listTasks() {
        Ui.printTaskList(tasks);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        Ui.printAddTask(task, tasks);
    }

    public static void addTaskWithoutMessage(Task task) {
        tasks.add(task);
    }

    public static void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            Ui.printDeleteTask(tasks, index);
        } else {
            throw new IllegalArgumentException(
                    "____________________________________________________________\n" +
                            "ERROR: Cannot delete task as it is out of scope!\n" +
                            "____________________________________________________________");
        }
    }

    public static void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task updatedTask = tasks.get(index).updateIsDone(true);
            tasks.set(index, updatedTask);
            Ui.printMarkTask(tasks, index);
        } else {
            throw new IllegalArgumentException(
                    "____________________________________________________________\n" +
                            "ERROR: Cannot mark task as it is out of scope!\n" +
                            "____________________________________________________________");
        }
    }

    public static void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task updatedTask = tasks.get(index).updateIsDone(false);
            tasks.set(index, updatedTask);
            Ui.printUnmarkTask(tasks, index);
        } else {
            throw new IllegalArgumentException(
                    "____________________________________________________________\n" +
                            "ERROR: Cannot unmark task as it is out of scope!\n" +
                            "____________________________________________________________");
        }
    }
}
