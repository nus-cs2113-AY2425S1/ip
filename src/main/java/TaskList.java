import java.util.ArrayList;

/**
 * The TaskList class manages a collection of tasks.
 * It supports adding tasks of different types (ToDo, Deadline, Event),
 * marking them as done/undone, deleting tasks, and finding tasks by keyword.
 */
public class TaskList {
    // List to store tasks
    private static ArrayList<Task> taskList;

    // Number of tasks
    private static int taskCount;

    // UI instance for user interaction
    private static UI uiInstance;

    // Parser instance for input handling
    private Parser parserInstance;

    /**
     * Constructor for TaskList.
     * Initializes an empty task list and sets task count to 0.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
        uiInstance = null;
    }

    /**
     * Adds a new task to the task list and increments the task count.
     *
     * @param newTask The task to be added.
     */
    public static void add(Task newTask) {
        taskList.add(newTask);
        taskCount++;
    }

    /**
     * Sets the UI instance for task list.
     *
     * @param ui The UI instance to be set.
     */
    public void setUI(UI ui) {
        uiInstance = ui;
    }

    /**
     * Sets the Parser instance for task list.
     *
     * @param parser The Parser instance to be set.
     */
    public void setParser(Parser parser) {
        parserInstance = parser;
    }

    /**
     * Adds a ToDo task to the task list.
     *
     * @param inputComponent Array of input components where the description is built from.
     * @throws DukeException if there is an error in the ToDo input.
     */
    public void addToDo(String[] inputComponent) throws DukeException {
        String description = "";

        for (int i = 1; i < inputComponent.length; i++) {
            description += inputComponent[i];
            description += " ";
        }

        try {
            parserInstance.checkTodoInput(description);
            taskList.add(new ToDo(description.trim()));
            taskCount++;
            uiInstance.addTaskMessage(taskCount);
        } catch (DukeException e) {
            e.displayMessage();
        }
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param inputComponent Array of input components where description and deadline date are parsed.
     * @throws DukeException if there is an error in the Deadline input.
     */
    public void addDeadline(String[] inputComponent) throws DukeException {
        String description = "";
        String by = "";
        int state = 0; // transition from "description" to "by" string

        for (int i = 1; i < inputComponent.length; i++) {
            if (inputComponent[i].equals("/by")) {
                state += 1;
            } else {
                if (state == 1) {
                    by += inputComponent[i];
                    by += " ";
                } else {
                    description += inputComponent[i];
                    description += " ";
                }
            }
        }

        try {
            parserInstance.checkDeadlineInput(description, state);
            taskList.add(new Deadline(description.trim(), by.trim()));
            taskCount++;
            uiInstance.addTaskMessage(taskCount);
        } catch (DukeException e) {
            e.displayMessage();
        }
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param inputComponent Array of input components where description, start time, and end time are parsed.
     */
    public void addEvent(String[] inputComponent) {
        String description = "";
        String from = "";
        String to = "";
        int state = 0; // transition from "description" to "from" to "to" string

        for (int i = 1; i < inputComponent.length; i++) {
            if (inputComponent[i].equals("/from")) {
                state = 1;
            } else if (inputComponent[i].equals("/to")) {
                state = 2;
            } else {
                if (state == 1) {
                    from += inputComponent[i];
                    from += " ";
                } else if (state == 2) {
                    to += inputComponent[i];
                    to += " ";
                } else {
                    description += inputComponent[i];
                    description += " ";
                }
            }
        }

        try {
            parserInstance.checkEventInput(description, state);
            taskList.add(new Event(description.trim(), from.trim(), to.trim()));
            taskCount++;
            uiInstance.addTaskMessage(taskCount);
        } catch (DukeException e) {
            e.displayMessage();
        }
    }

    /**
     * Deletes a task at the specified index from the task list.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException if the index is invalid.
     */
    public void deleteTask(int index) throws DukeException {
        if (index - 1 < 0 || index - 1 >= taskCount) {
            uiInstance.Warning("You have input an invalid index");
            return;
        }

        String deleted = taskList.get(index - 1).toString();
        taskList.remove(index - 1);
        taskCount--;

        uiInstance.deleteTaskMessage(taskCount, deleted);
    }

    /**
     * Lists all tasks in the task list.
     * If no tasks are present, a message is displayed.
     */
    public void list() {
        if (taskCount == 0) {
            uiInstance.displayMessage("No tasks found");
        } else {
            for (int i = 0; i < taskCount; i++) {
                uiInstance.displayTask(i);
            }
        }
    }

    /**
     * Gets the current number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws DukeException if the index is invalid.
     */
    public static void markAsDone(int index) throws DukeException {
        if (index < 0 || index > taskCount) {
            uiInstance.Warning("You have input an invalid index");
            return;
        }
        taskList.get(index - 1).markAsDone();
        uiInstance.markTaskMessage(index);
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws DukeException if the index is invalid.
     */
    public void markAsNotDone(int index) throws DukeException {
        if (index < 0 || index > taskCount) {
            uiInstance.Warning("You have input an invalid index");
            return;
        }
        taskList.get(index - 1).markAsNotDone();
        uiInstance.unmarkTaskMessage(index);
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param inputComponent Array of input components where the keyword is located.
     * @throws DukeException if no tasks match the keyword.
     */
    public void findTaskKeyword(String[] inputComponent) throws DukeException {
        String keyword = "";
        for (int i = 1; i < inputComponent.length; i++) {
            keyword += inputComponent[i];
            if (i != inputComponent.length - 1) {
                keyword += " ";
            }
        }
        int count = 0;
        uiInstance.displayMessageForFind();
        for (int i = 0; i < taskCount; i++) {
            if (taskList.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                uiInstance.displayTaskForFind(i, count);
            }
        }
        if (count == 0) {
            uiInstance.displayMessage("No tasks found matching the keyword");
        }
    }
}
