package melchizedek.task;

import java.util.Arrays;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> allTasks;

    public TaskList() {
        allTasks = new ArrayList<>();
    }

    public static String joinStringArray(String[] array, int from, int to, String delimiter) {
        String[] arrayCopy = Arrays.copyOfRange(array, from, to);
        return String.join(delimiter, arrayCopy);
    }

    public void printTaskList() {
        if (allTasks.isEmpty()) {
            System.out.println("\tNo tasks added!");
            return;
        }

        System.out.println("\tSure! Here are the tasks on your list:");

        for (int i = 0; i < allTasks.size(); i++) {
            System.out.print("\t" + (i + 1) +".");
            System.out.println(allTasks.get(i).toString());
        }
    }

    public void printAddedTask() {
        int taskCount = allTasks.size();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + allTasks.get(taskCount - 1).toString());
        printNumberOfTasks(taskCount);
    }

    private static void printNumberOfTasks(int taskCount) {
        if (taskCount > 1) {
            System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("\tNow you have " + taskCount + " task in the list.");
        }
    }

    public void markTaskAsDone(int id) {
        allTasks.get(id - 1).markTaskAsDone();

        System.out.println("\tGreat! I've marked this task as done:");
        System.out.println("\t  " + allTasks.get(id - 1).toString());
    }

    public void unmarkTaskAsDone(int id) {
        allTasks.get(id - 1).unmarkTaskAsDone();

        System.out.println("\tOK, I've marked this task as undone:");
        System.out.println("\t  " + allTasks.get(id - 1).toString());
    }

    public void addTodo(String[] tokens) {
        String description = String.join(" ", tokens);

        allTasks.add(new Todo(description));

        printAddedTask();
    }

    public void addDeadline(String[] tokens) {
        int byIndex = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/by")) {
                byIndex = i;
                break;
            }
        }
        if (byIndex == -1) {
            //handleIncompleteInput();
        }

        String description = joinStringArray(tokens, 0, byIndex, " ");
        String by = joinStringArray(tokens, byIndex + 1, tokens.length, " ");

        allTasks.add(new Deadline(description, by));

        printAddedTask();
    }

    public void addEvent(String[] tokens) {
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/from")) {
                fromIndex = i;
            } else if (tokens[i].equalsIgnoreCase("/to")) {
                toIndex = i;
                break;
            }
        }
        if (fromIndex == -1 || toIndex == -1) {
            //handleIncompleteInput();
        }

        String description = joinStringArray(tokens, 0, fromIndex, " ");
        String from = joinStringArray(tokens, fromIndex + 1, toIndex, " ");
        String to = joinStringArray(tokens, toIndex + 1, tokens.length, " ");

        allTasks.add(new Event(description, from, to));

        printAddedTask();
    }

    public void deleteTask(int id) {
        String taskString = allTasks.get(id - 1).toString();
        allTasks.remove(id - 1);

        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + taskString);
        printNumberOfTasks(allTasks.size());
    }
}
