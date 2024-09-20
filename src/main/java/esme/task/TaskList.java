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

    public boolean isEmpty() {
        return tasks.isEmpty();
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


    public void printTaskList() {
        System.out.println("\tBy the light of the moon, these are the tasks that guide your path:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1) + "." + task);
        }
    }

    public String deleteTask(int index) throws EsmeException {
        if (isEmpty()) {
            throw new EsmeException("Task list is empty!!");
        }
        String description = tasks.get(index-1).getDescription();
        tasks.remove(index-1);
        return description;
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

    public ArrayList<Task> findTask(String line) throws EsmeException {
        String[] parts = line.split(" ", 2);
        if (parts.length != 2) {
            throw new EsmeException("Error: The find format is incorrect. Use 'find <keyword>'");
        }
        ArrayList<Task> taskArray = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(parts[1])) {
                taskArray.add(task);
            }
        }
        return taskArray;
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
