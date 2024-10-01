import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * The TaskList class manages a list of tasks. It allows tasks to be added, deleted, marked as done or not done,
 * and supports operations like loading tasks from a file or saving them to a file.
 */
public class TaskList {
    public ArrayList<Task> taskList;
    protected int taskIndex;
    public static final int INVALID_INDEX = -1;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        System.out.println("--------------------------------------------");
        this.taskList.add(task);
        System.out.println("Okay! I have added this task: ");
        System.out.println(task.toString());
        System.out.printf("You currently have %d tasks in your list \n", taskList.size());
        System.out.println("--------------------------------------------");
    }

    /**
     * Deletes a task from the task list.
     *
     * @param commandArguments A HashMap containing the command arguments, including the task index.
     */
    public void deleteTask(HashMap<String, String> commandArguments) {
        String argument = commandArguments.get("argument");
        getTaskIndex(argument);
        Task task = taskList.get(this.taskIndex);
        System.out.println("--------------------------------------------");
        this.taskList.remove(task);
        System.out.println("Okay! I have removed this task: ");
        System.out.println(task.toString());
        System.out.printf("You currently have %d tasks in your list \n", taskList.size());
        System.out.println("--------------------------------------------");
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        System.out.println("--------------------------------------------");
        System.out.println("Here are your current tasks: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + this.taskList.get(i));
        }
        System.out.println("--------------------------------------------");
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param commandArguments A HashMap containing the command arguments, including the task description.
     * @throws TulipTaskException.InvalidTaskDescriptionException if the task description is invalid.
     */
    public void addToDo(HashMap<String, String> commandArguments) throws TulipTaskException.InvalidTaskDescriptionException {
        String argument = commandArguments.get("argument");
        if (argument == null) {
            throw new TulipTaskException.InvalidTaskDescriptionException();
        }

        ToDo task = new ToDo(commandArguments.get("argument"));
        addTask(task);
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param commandArguments A HashMap containing the command arguments, including the task description and deadline.
     * @throws TulipTaskException.InvalidDeadlineException if the deadline is invalid.
     * @throws TulipTaskException.InvalidTaskDescriptionException if the task description is invalid.
     */
    public void addDeadline(HashMap<String, String> commandArguments) throws TulipTaskException.InvalidDeadlineException, TulipTaskException.InvalidTaskDescriptionException {
        String argument = commandArguments.get("argument");
        String by = commandArguments.get("/by");

        if (argument == null) {
            throw new TulipTaskException.InvalidTaskDescriptionException();
        }

        if (by == null) {
            throw new TulipTaskException.InvalidDeadlineException();
        }

        Deadline task = new Deadline(argument, by);
        addTask(task);
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param commandArguments A HashMap containing the command arguments, including the task description, start time, and end time.
     * @throws TulipTaskException.InvalidTaskDescriptionException if the task description is invalid.
     * @throws TulipTaskException.InvalidStartDateException if the start date is invalid.
     * @throws TulipTaskException.InvalidEndDateException if the end date is invalid.
     */
    public void addEvent(HashMap<String, String> commandArguments) throws TulipTaskException.InvalidTaskDescriptionException, TulipTaskException.InvalidStartDateException, TulipTaskException.InvalidEndDateException {
        String argument = commandArguments.get("argument");
        String from = commandArguments.get("/from");
        String to = commandArguments.get("/to");

        if (argument == null) {
            throw new TulipTaskException.InvalidTaskDescriptionException();
        }

        if (from == null) {
            throw new TulipTaskException.InvalidStartDateException();
        }

        if (to == null) {
            throw new TulipTaskException.InvalidEndDateException();
        }

        Event task = new Event(argument, from, to);
        addTask(task);
    }

    /**
     * Gets the index of a task based on the provided index string.
     *
     * @param indexString The string representing the task index.
     */
    public void getTaskIndex(String indexString) {
        int index = Integer.parseInt(indexString) - 1;

        if (index < 0 || index > taskList.size() - 1) {
            this.taskIndex = INVALID_INDEX;
            return;
        }

        this.taskIndex = index;
    }

    /**
     * Marks a task as done.
     *
     * @param commandArguments A HashMap containing the command arguments, including the task index.
     * @throws TulipTaskException.InvalidTaskIndexException if the task index is invalid.
     */
    public void markTaskAsDone(HashMap<String, String> commandArguments) throws TulipTaskException.InvalidTaskIndexException {
        String argument = commandArguments.get("argument");
        getTaskIndex(argument);

        if (this.taskIndex == INVALID_INDEX) {
            throw new TulipTaskException.InvalidTaskIndexException();
        }

        Task task = taskList.get(this.taskIndex);
        task.markAsDone();
        System.out.println("--------------------------------------------");
        System.out.println("Great job! I have marked this task as done: ");
        System.out.println(task);
        System.out.println("--------------------------------------------");
    }

    /**
     * Marks a task as not done.
     *
     * @param commandArguments A HashMap containing the command arguments, including the task index.
     * @throws TulipTaskException.InvalidTaskIndexException if the task index is invalid.
     */
    public void markTaskAsNotDone(HashMap<String, String> commandArguments) throws TulipTaskException.InvalidTaskIndexException {
        String argument = commandArguments.get("argument");
        getTaskIndex(argument);

        if (this.taskIndex == INVALID_INDEX) {
            throw new TulipTaskException.InvalidTaskIndexException();
        }

        Task task = taskList.get(this.taskIndex);
        task.markAsNotDone();
        System.out.println("--------------------------------------------");
        System.out.println("Okay, I have marked this task as not done: ");
        System.out.println(task);
        System.out.println("--------------------------------------------");
    }

    /**
     * Saves the tasks to a file.
     */
    public void saveTaskToFile() {
        System.out.println("--------------------------------------------");
        Storage.saveTasks(this.taskList);
        System.out.println("--------------------------------------------");
    }

    /**
     * Loads tasks from a file.
     */
    public void loadTaskFromFile() {
        List<String> list = Storage.loadTasks();
        for (int i = 0; i < Objects.requireNonNull(list).size(); i++) {
            parseTask(list.get(i));
        }
        System.out.println("--------------------------------------------");
        System.out.println("Tasks have been successfully loaded!");
        System.out.println("--------------------------------------------");
    }

    /**
     * Parses a task from a string.
     *
     * @param line The string containing the task information.
     */
    public void parseTask(String line) {
        String type = line.substring(1, 2);
        boolean completed = line.charAt(4) == 'X';

        switch (type) {
            case "T" -> {
                String description = line.substring(7);
                ToDo task = new ToDo(description);
                this.taskList.add(task);
                if (completed) {
                    task.markAsDone();
                }

            }
            case "D" -> {
                int deadlineIndex = line.indexOf("(by:");
                String description = line.substring(7, deadlineIndex).trim();
                String deadline = line.substring(deadlineIndex + 5, line.length() - 1);
                Deadline task = new Deadline(description, deadline);
                this.taskList.add(task);
                if (completed) {
                    task.markAsDone();
                }

            }
            case "E" -> {
                int eventIndex = line.indexOf("(from:");
                String description = line.substring(7, eventIndex).trim();
                String timeInfo = line.substring(eventIndex + 6, line.length() - 1); // Extract time info

                String[] timeParts = timeInfo.split(" to: ");
                String eventStart = timeParts[0].trim();
                String eventEnd = timeParts[1].trim();
                Event task = new Event(description, eventStart, eventEnd);
                this.taskList.add(task);
                if (completed) {
                    task.markAsDone();
                }
            }
        }
    }

    /**
     * Finds tasks that match a given argument.
     *
     * @param commandArguments A HashMap containing the command arguments, including the search keyword.
     */
    public void findTask(HashMap<String, String> commandArguments) {
        String argument = commandArguments.get("argument");

        System.out.println("--------------------------------------------");
        System.out.println("Here are the matching tasks in your list: ");

        for (Task task : taskList) {
            if (task.description.contains(argument)) {
                System.out.println(task);
            }
        }

        System.out.println("--------------------------------------------");

    }
}
