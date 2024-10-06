import sleepy.Task.Deadline;
import sleepy.Task.Event;
import sleepy.Task.Task;
import sleepy.Task.Todo;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Manages a list of tasks, including the addition, deletion, and modification
 * of various types of tasks (Event, Deadline, Todo). Supports marking tasks as
 * done, unmarking, and searching for tasks by keyword.
 */
public class TaskManager {
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    public static final String TODO = "todo";
    private ArrayList<Task> tasks;
    private static final String LINE_SEPARATOR = "____________________________________________________________\n";

    /**
     * Initializes the TaskManager with a list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the current list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task based on the input command. Supports adding events, deadlines,
     * and todos based on the command prefix.
     *
     * @param input The input string for adding a task.
     * @throws SleepyException if the command is invalid or unknown.
     */
    public void addTask(String input) throws SleepyException {
        if (input.startsWith(EVENT)) {
            addEvent(input);
        } else if (input.startsWith(DEADLINE)) {
            addDeadline(input);
        } else if (input.startsWith(TODO)) {
            addTodo(input);
        } else {
            throw new SleepyException("Unknown command. "
                    + "Please start with 'list', 'mark', 'unmark', 'delete', 'event', 'deadline', or 'todo'.\n");
        }
    }

    /**
     * Adds an event to the task list based on the input.
     * The input should follow the format: "event description /from start_time /to end_time".
     *
     * @param input The input string for adding an event.
     * @throws SleepyException if the input format is invalid.
     */
    public void addEvent(String input) throws SleepyException {
        //splitting and checking if there are at least 2 parts (description and event time)
        String[] parts = input.substring(5).split(" /from ");
        if (parts.length != 2) {
            throw new SleepyException("Invalid event format. Please use: event description /from time /to time\n");
        }

        String description = parts[0].trim();
        String[] toParts = parts[1].split(" /to ");
        //splitting and checking if event has a from and to component
        if (toParts.length != 2 || toParts[0].trim().isEmpty() || toParts[1].trim().isEmpty()) {
            throw new SleepyException("Invalid event format. Please use: event description /from time /to time\n");
        }

        if (description.isEmpty()) {
            throw new SleepyException("Invalid event format. The event description cannot be empty\n");
        }

        String from = toParts[0].trim();
        String to = toParts[1].trim();
        Task task = new Event(description, from, to);
        addTaskToList(task);
    }

    /**
     * Adds a deadline to the task list based on the input.
     * The input should follow the format: "deadline description /by date_time".
     *
     * @param input The input string for adding a deadline.
     * @throws SleepyException if the input format is invalid or the date-time is incorrectly formatted.
     */
    public void addDeadline(String input) throws SleepyException {
        String[] parts = input.substring(8).split(" /by ");

        // Checking if the deadline has a description and by component
        if (parts.length != 2) {
            throw new SleepyException("Invalid deadline format. Please use: deadline description /by date\n");
        }

        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new SleepyException("Invalid deadline format. The deadline description cannot be empty. "
                    + "Please use: deadline description /by date\n");
        }

        String by = parts[1].trim();

        try {
            // Define the expected date-time format for parsing
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

            // Parse the 'by' string into a LocalDateTime object
            LocalDateTime byDateTime = LocalDateTime.parse(by, formatter);

            // Create the Deadline task with the parsed LocalDateTime
            Task task = new Deadline(description, byDateTime);
            addTaskToList(task);
        } catch (DateTimeParseException e) {
            System.out.println(LINE_SEPARATOR + "Invalid date-time format. Please use: yyyy-MM-dd HHmm\n" + LINE_SEPARATOR);
        } catch (IllegalArgumentException e) {
            System.out.println(LINE_SEPARATOR + e.getMessage() + "\n" + LINE_SEPARATOR);
        }
    }

    /**
     * Adds a todo task to the task list based on the input.
     * The input should follow the format: "todo description".
     *
     * @param input The input string for adding a todo.
     * @throws SleepyException if the todo description is empty.
     */
    public void addTodo(String input) throws SleepyException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new SleepyException("Invalid todo format. Todo description cannot be empty\n");
        }
        Task task = new Todo(description);
        addTaskToList(task);
    }

    /**
     * Adds a task to the list and prints a message indicating the task has been added.
     *
     * @param task The task to add to the list.
     */
    public void addTaskToList(Task task) {
        tasks.add(task);
        System.out.println(LINE_SEPARATOR
                + "added...\n"
                + " " + tasks.get(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list...all the best, im going back to bed\n"
                + LINE_SEPARATOR);
    }

    /**
     * Deletes a task from the list by its index and prints a message indicating the task has been removed.
     *
     * @param taskIndex The index of the task to delete (1-based index).
     * @throws SleepyException if the task index is invalid.
     */
    public void deleteTask(int taskIndex) throws SleepyException{
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            throw new SleepyException("Invalid task number.");
        }

        System.out.println(LINE_SEPARATOR
                + "I've removed the task...\n"
                + " " + tasks.get(taskIndex - 1).toString() + "\n"
                + "Now you have " + (tasks.size() - 1) + " tasks in the list...can I go and sleep now\n"
                +LINE_SEPARATOR);
        tasks.remove(taskIndex - 1);
    }

    /**
     * Prints the list of tasks. If the list is empty, a message indicating no tasks remain is printed.
     */
    public void listTasks() {
        if (!tasks.isEmpty()) {
            System.out.println(LINE_SEPARATOR
                    + "Stop bothering me, u have unfinished tasks...:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println(LINE_SEPARATOR);
        } else {
            System.out.println(LINE_SEPARATOR
                    + "You're done for the day...go snooze");
            System.out.println(LINE_SEPARATOR);
        }
    }

    /**
     * Marks a task as done by its index and prints a message indicating the task has been marked.
     *
     * @param taskNumber The index of the task to mark as done (1-based index).
     * @throws SleepyException if the task index is invalid.
     */
    public void markTask(int taskNumber) throws SleepyException {
        if (taskNumber <= tasks.size() && taskNumber > 0) {
            tasks.get(taskNumber - 1).markAsDone();
            System.out.println(LINE_SEPARATOR
                    + "Yay...good for you:\n"
                    + " " + tasks.get(taskNumber - 1).toString() + "\n"
                    + LINE_SEPARATOR);
        } else {
            throw new SleepyException("this tasks doesn't even exist...\n");

        }
    }

    /**
     * Unmarks a task by its index and prints a message indicating the task has been unmarked.
     *
     * @param taskNumber The index of the task to unmark (1-based index).
     * @throws SleepyException if the task index is invalid.
     */
    public void unmarkTask(int taskNumber) throws SleepyException {
        if (taskNumber <= tasks.size() && taskNumber > 0) {
            tasks.get(taskNumber - 1).markAsNotDone();
            System.out.println(LINE_SEPARATOR
                    + "Could you stop making me do extra work:\n"
                    + " " + tasks.get(taskNumber - 1).toString() + "\n"
                    + LINE_SEPARATOR);
        } else {
            throw new SleepyException("ummm...the task doesn't even exist...maybe you should get some sleep\n");
        }
    }

    /**
     * Finds tasks that contain the given keyword in their description and prints the matching tasks.
     *
     * @param keyword The keyword to search for within the task descriptions.
     * @throws SleepyException if the keyword is empty.
     */
    public void findTask(String keyword) throws SleepyException {
        if (keyword.isEmpty()) {
            throw new SleepyException("Keyword cannot be empty.");
        }

        System.out.println(LINE_SEPARATOR + "Here are the matching tasks in your list:");

        boolean taskFound = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            //making it easier to find keywords by changing all to lowercase
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println((i + 1) + "." + task);
                taskFound = true;
            }
        }

        if (!taskFound) {
            System.out.println("No tasks matching the keyword were found.");
        }

        System.out.println(LINE_SEPARATOR);
    }
}
