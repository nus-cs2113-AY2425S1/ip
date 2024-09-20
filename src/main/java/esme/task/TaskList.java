package esme.task;

import esme.exceptions.EsmeException;

import java.util.ArrayList;

/**
 * Represents a list that stores the current tasks added by the user
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new task list
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Checks if the task list is empty
     *
     * @return True if the task list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in the task list
     *
     * @return The number of tasks in the task list
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    
    /**
     * Converts a given task into its input format for saving to a file.
     * The format is as follows:
     * For Todo tasks: "todo <description> /c <completed>"
     * For Event tasks: "event <description> /from <from> /to <to> /c <completed>"
     * For Deadline tasks: "deadline <description> /by <by> /c <completed>"
     * 
     * @param task The task to be converted
     * @return The task in its input format
     */
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

    /**
     * Returns an ArrayList containing all the tasks in the input format
     *
     * @return An ArrayList containing all the tasks in the input format
     */
    public ArrayList<String> getFormattedTasks() {
        ArrayList<String> taskCollection = new ArrayList<>();
        for (Task task : tasks) {
            taskCollection.add(convertTaskToInputFormat(task));
        }
        return taskCollection;
    }

    /**
     * Prints all the tasks in the task list to the user
     */
    public void printTaskList() {
        System.out.println("\tBy the light of the moon, these are the tasks that guide your path:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1) + "." + task);
        }
    }

    /**
     * Deletes a task from the task list
     *
     * @param index The index of the task to be deleted
     * @return The description of the task that was deleted
     * @throws EsmeException If the task list is empty
     */
    public String deleteTask(int index) throws EsmeException {
        if (isEmpty()) {
            throw new EsmeException("Task list is empty!!");
        }
        String description = tasks.get(index-1).getDescription();
        tasks.remove(index-1);
        return description;
    }

    /**
     * Adds a new Todo task to the task list
     *
     * @param input The description of the new Todo task
     * @return The description of the new Todo task
     */
    public String addTodoTask(String input) {
        String[] parts = input.split(" ", 2);
        tasks.add(new Todo(parts[1]));
        return parts[1];
    }

    /**
     * Adds a new Deadline task to the task list
     *
     * @param input The description and the deadline of the new Deadline task
     * @return The description of the new Deadline task
     * @throws EsmeException If the deadline format is incorrect
     */
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

    /**
     * Adds a new Event task to the task list
     *
     * @param input The description, start date and end date of the new Event task
     * @return The description of the new Event task
     * @throws EsmeException If the event format is incorrect
     */
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

    /**
     * Marks a task as completed
     *
     * @param taskIndex The index of the task to be marked
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex).setCompleted(true);
    }

    /**
     * Unmarks a task as completed
     *
     * @param taskIndex The index of the task to be unmarked
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).setCompleted(false);
    }

    /**
     * Returns a task from the task list
     *
     * @param taskIndex The index of the task to be returned
     * @return The task at the given index
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }
}
