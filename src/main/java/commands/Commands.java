package commands;

import tasks.Task;
import java.util.ArrayList;

public class Commands {

    /**
     * Marks a task as done in the task list.
     *
     * @param list The list of tasks.
     * @param str  An array where the second element is the index of the task to be marked as done.
     *             The index is 1-based, so it's adjusted for 0-based indexing within the method.
     */
    public static void markAsDone(ArrayList<Task> list, String[] str) {
        int index = Integer.parseInt(str[1]) - 1;
        if (index < 0 || index >= list.size()) {
            System.out.println("Sorry, list number does not exist");
        } else {
            list.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(index).taskType() + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
        }
    }

    /**
     * Marks a task as not done in the task list.
     */
    public static void markAsNotDone(ArrayList<Task> list, String[] str) {
        int index = Integer.parseInt(str[1]) - 1;
        if (index < 0 || index >= list.size()) {
            System.out.println("Sorry, list number does not exist");
        } else {
            list.get(index).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list.get(index).taskType() + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
        }
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param list The list of tasks to be displayed.
     */
    public static void showList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                System.out.println((i + 1) + ". " + list.get(i).toString());
            }
        }
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param taskList The list of tasks.
     * @param index    The 1-based index of the task to be deleted, adjusted for 0-based indexing.
     */
    public static void delete(ArrayList<Task> taskList, int index) {
        try {
            if (index < 0 || index > taskList.size()) {
                System.out.println("Task number out of range.");
                return;
            }
            taskList.remove(index - 1);
            System.out.println("Task removed. You now have " + taskList.size() + " tasks.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number.");
        }
    }
}

