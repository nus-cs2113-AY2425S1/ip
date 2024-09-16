package melchizedek.task;

import java.util.Arrays;

public class TaskList {
    private Task[] allTasks;
    private int taskCount;
    private final int MAX_TASKS = 100;

    public TaskList() {
        allTasks = new Task[MAX_TASKS];
        taskCount = 0;
    }


    public static String joinStringArray(String[] array, int from, int to, String delimiter) {
        String[] arrayCopy = Arrays.copyOfRange(array, from, to);
        return String.join(delimiter, arrayCopy);
    }

    public void printTaskList() {
        if (taskCount == 0) {
            System.out.println("\tNo tasks added!");
            return;
        }

        System.out.println("\tSure! Here are the tasks on your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print("\t" + (i + 1) +".");
            System.out.println(allTasks[i]);
        }
    }

    public void printAddedTask() {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + allTasks[taskCount - 1]);
        if (taskCount > 1) {
            System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("\tNow you have " + taskCount + " task in the list.");
        }
    }

    public void markTaskAsDone(int id) {
        allTasks[id - 1].markTaskAsDone();

        System.out.println("\tGreat! I've marked this task as done:");
        System.out.println("\t  " + allTasks[id - 1]);
    }

    public void unmarkTaskAsDone(int id) {
        allTasks[id - 1].unmarkTaskAsDone();

        System.out.println("\tOK, I've marked this task as undone:");
        System.out.println("\t  " + allTasks[id - 1]);
    }

    public void addTodo(String[] tokens) {
        String description = String.join(" ", tokens);

        allTasks[taskCount] = new Todo(taskCount + 1, description);
        taskCount++;

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

        allTasks[taskCount] = new Deadline(taskCount + 1, description, by);
        taskCount++;

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

        allTasks[taskCount] = new Event(taskCount + 1, description, from, to);
        taskCount++;

        printAddedTask();
    }
}
