package tyrone.task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static boolean isValidTaskId(int taskId) {
        return taskId >= 0 && taskId < tasks.size();
    }

    public static void markTaskAsDone (int taskId) {
        tasks.get(taskId).markAsDone();
    }

    public static void markTaskAsUndone (int taskId) {
        tasks.get(taskId).markAsUndone();
    }

    public static String getSingleTaskDetails (int taskId) {
        return tasks.get(taskId).getNameWithStatus();
    }

    public static void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).getNameWithStatus());
        }
    }
}
