package esme.task;

import esme.exceptions.EsmeException;

import java.util.ArrayList;

/**
 * Represents a list that stores the current tasks added by the user
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public <T> String convertTaskToInputFormat(T task) {
        String formattedTask = "";
        if (task instanceof Todo) {
            formattedTask = "todo " + ((Todo) task).getDescription() + " /c "
                    + ((Todo) task).hasCompleted();
        }
        else if (task instanceof Event) {
            formattedTask = "event " + ((Event) task).getDescription() + " /from " + ((Event) task).getFrom()
                    + " /to " + ((Event) task).getTo() + " /c " + ((Event) task).hasCompleted();
        }
        else if (task instanceof Deadline) {
            formattedTask = "deadline " + ((Deadline) task).getDescription() + " /by "
                    + ((Deadline) task).getBy() + " /c " + ((Deadline) task).hasCompleted();
        }
        return formattedTask;
    }

    public ArrayList<String> getFormattedTasks() {
        ArrayList<String> taskCollection = new ArrayList<>();
        for (Task task : tasks) {
            taskCollection.add(convertTaskToInputFormat(task));
        }
        return taskCollection;
    }

    /**
     * Prints out the task list. The output will be in the format:
     * By the light of the moon, these are the tasks that guide your path:
     * <index>. [X] <task name>
     * <index>. [ ] <task name>
     * ...
     */
    public void printTaskList() {
        System.out.println("\tBy the light of the moon, these are the tasks that guide your path:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1) + "." + task);
        }
    }

    public String addTodoTask(String input) {
        String[] parts = input.split(" ", 2);
        tasks.add(new Todo(parts[1]));
        return parts[1];
    }

    public String addDeadlineTask(String input) throws EsmeException {
        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            throw new EsmeException("Error: The deadline format is incorrect. Use 'deadline <task> /by <time>'");
        }
        String description = parts[0].replace("deadline ", "").trim();
        String by = parts[1].trim();
        tasks.add(new Deadline(description, by));
        return description;
    }

    public String addEventTask(String input) throws EsmeException {
        String[] parts = input.split(" /from | /to ");
        if (parts.length < 3) {
            throw new EsmeException("Error: The event format is incorrect. Use 'event <task> /from <time> /to <time>'");
        }
        String description = parts[0].replace("event ", "").trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        tasks.add(new Event(description, from, to));
        return description;
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex).setCompleted(true);
    }

    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).setCompleted(false);
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }
}
