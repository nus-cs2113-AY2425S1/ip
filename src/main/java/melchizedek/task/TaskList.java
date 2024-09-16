package melchizedek.task;

import melchizedek.exceptions.InvalidTaskNumberException;

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
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + allTasks.get(allTasks.size() - 1).toString());
        printNumberOfTasks();
    }

    public void printNumberOfTasks() {
        int taskCount = allTasks.size();
        if (taskCount != 1) {
            System.out.println("\tNow you have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("\tNow you have " + taskCount + " task in the list.");
        }
    }

    public void markTaskAsDone(int id) throws InvalidTaskNumberException {
        if (id > allTasks.size() || id < 1) {
            throw new InvalidTaskNumberException();
        }

        if (allTasks.get(id - 1).isMarkAsDone()) {
            System.out.println("\tSure! But it was already marked as done?");
        } else {
            allTasks.get(id - 1).markTaskAsDone();
            System.out.println("\tGreat! I've marked this task as done:");
        }
        System.out.println("\t  " + allTasks.get(id - 1).toString());
    }

    public void unmarkTaskAsDone(int id) throws InvalidTaskNumberException {
        if (id > allTasks.size() || id < 1) {
            throw new InvalidTaskNumberException();
        }
        if (!allTasks.get(id - 1).isMarkAsDone()) {
            System.out.println("\tHmmm... it was undone in the first place.");
        } else {
            allTasks.get(id - 1).unmarkTaskAsDone();
            System.out.println("\tOK, I've marked this task as undone:");
        }
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

        String description = null;
        String by = null;
        try {
            description = joinStringArray(tokens, 0, byIndex, " ");
            by = joinStringArray(tokens, byIndex + 1, tokens.length, " ");
        } catch (IllegalArgumentException e) {
            if (byIndex == -1) {
                System.out.println("\tUh oh! I cannot process this task without the key \"/by\"!");
                System.out.println("\tExample: deadline coding assignment /by 12pm");
                return;
            }
        }

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

        String description = null;
        String from = null;
        String to = null;
        try {
            description = joinStringArray(tokens, 0, fromIndex, " ");
            from = joinStringArray(tokens, fromIndex + 1, toIndex, " ");
            to = joinStringArray(tokens, toIndex + 1, tokens.length, " ");
        } catch (IllegalArgumentException e) {
            if (fromIndex == -1) {
                System.out.println("\tUh oh! I cannot process this task without the key \"/from\"!");
            } else if (toIndex == -1) {
                System.out.println("\tUh oh! I cannot process this task without the key \"/to\"!");
            } else {
                System.out.println("\tUh oh! I cannot process this task without the key \"/from\" and \"/to\"!");
            }
            System.out.println("\tExample: event coding lecture /from 2pm /to 4pm");
            return;
        }

        allTasks.add(new Event(description, from, to));
        printAddedTask();
    }

    public void deleteTask(int id) throws InvalidTaskNumberException {
        if (id > allTasks.size() || id < 1) {
            throw new InvalidTaskNumberException();
        }

        String taskString = allTasks.get(id - 1).toString();
        allTasks.remove(id - 1);

        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + taskString);
        printNumberOfTasks();
    }

    public void loadTodoFromFile(String[] tokens) {
        boolean isDone = tokens[0].equals("1");
        String description = tokens[1];
        allTasks.add(new Todo(description, isDone));
    }

    public void loadDeadlineFromFile(String[] tokens) {
        boolean isDone = tokens[0].equals("1");
        String description = tokens[1];
        String by  = tokens[2];
        allTasks.add(new Deadline(description, isDone, by));
    }

    public void loadEventFromFile(String[] tokens) {
        boolean isDone = tokens[0].equals("1");
        String description = tokens[1];
        String from  = tokens[2];
        String to = tokens[3];
        allTasks.add(new Event(description, isDone, from, to));
    }

    public String taskListToFile() {
        String output = "";
        for (Task task : allTasks) {
            output = output + task.taskToFile() + System.lineSeparator();
        }
        return output;
    }
}
