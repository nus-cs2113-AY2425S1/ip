package tommi;

import java.util.ArrayList;

public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();  // Array to store tasks

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void listTasks() {
        Tommi.printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". "  + tasks.get(i));
        }
        Tommi.printLine();
    }

    public static void addTask(Task task) {
        tasks.add(task);
        Tommi.printLine();
        System.out.println("Sure. I've added the task: " + System.lineSeparator()
                + task + System.lineSeparator()
                + "There are now " + tasks.size() + " tasks in the list.");
        Tommi.printLine();
    }

    public static void addTaskWithoutMessage(Task task) {
        tasks.add(task);
    }

    public static void deleteTask(int index) {
        Tommi.printLine();
        System.out.println("I've removed the task: " + System.lineSeparator()
                + tasks.get(index) + System.lineSeparator());
        tasks.remove(index);
        System.out.println( "There are now " + tasks.size() + " tasks in the list.");
        Tommi.printLine();
    }

    public static void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Tommi.printLine();
            Task updatedTask = tasks.get(index).updateIsDone(true);
            tasks.set(index, updatedTask);
            System.out.println("Awesomesauce! I've marked this task as done:");
            System.out.println(tasks.get(index));
            Tommi.printLine();
        }
    }

    public static void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Tommi.printLine();
            Task updatedTask = tasks.get(index).updateIsDone(false);
            tasks.set(index, updatedTask);
            System.out.println("OK, I've marked this task as undone:");
            System.out.println(tasks.get(index));
            Tommi.printLine();
        }
    }
}
