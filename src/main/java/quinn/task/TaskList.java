package quinn.task;

import quinn.command.CommandType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in the Quinn task management application.
 * This class manages the collection of tasks, including filtering and various operations on tasks.
 * The TaskList maintains both a main list of all tasks and a filtered list of tasks.
 * The filter is applied when searching for tasks, and is reset in the following scenarios:
 *   When a new task is added
 *   When the list command is executed
 *   When the exit command is executed
 *   When a delete command is executed and the filtered list becomes empty
 */
public class TaskList {
    private final List<Task> tasks;
    private List<Task> filteredTasks;
    private CommandType filterCommandType;
    private String filterInfo;

    /**
     * Constructs an empty TaskList.
     * Initializes the main task list and the filtered task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        filteredTasks = new ArrayList<>();
        filterCommandType = null;
        filterInfo = null;
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return the number of tasks in the main task list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the number of tasks in the filtered list.
     *
     * @return the number of tasks in the filtered task list
     */
    public int getNumOfFilteredTasks() {
        return filteredTasks.size();
    }

    /**
     * Retrieves a task from the main task list by its index.
     *
     * @param index the index of the task to retrieve (0-based)
     * @return the Task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves a task from the filtered task list by its index.
     *
     * @param index the index of the task to retrieve from the filtered list (0-based)
     * @return the Task at the specified index in the filtered list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getFilteredTask(int index) {
        return filteredTasks.get(index);
    }

    /**
     * Checks if there is an active filter applied to the task list.
     *
     * @return true if there are tasks in the filtered list, false otherwise
     */
    public boolean hasFilter() {
        return !filteredTasks.isEmpty();
    }

    /**
     * Retrieves the type of command that was used to apply the current filter.
     *
     * @return the CommandType of the current filter, or null if no filter is applied
     */
    public CommandType getFilterCommandType() {
        return filterCommandType;
    }

    /**
     * Retrieves the information used for the current filter.
     *
     * @return the filter information (e.g., keyword for search), or null if no filter is applied
     */
    public String getFilterInfo() {
        return filterInfo;
    }

    /**
     * Resets the filtered task list and clears filter information.
     * This method is typically called when a new operation that affects all tasks is performed.
     * This method is called in the following scenarios:
     * When a new task is added
     * When the list command is executed
     * When the exit command is executed
     * When a delete command is executed and the filtered list becomes empty
     */
    public void resetFilteredTasks() {
        filteredTasks = new ArrayList<>();
        filterCommandType = null;
        filterInfo = null;
    }

    /**
     * Filters the tasks based on a given keyword and updates the filtered task list.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public void setFilteredTasksByKeyword(String keyword) {
        filteredTasks = new ArrayList<>();

        // Iterate through each task and filter those that contain the keyword
        for (Task task : tasks) {
            if (task.hasKeyword(keyword)) {
                filteredTasks.add(task);
            }
        }

        filterCommandType = CommandType.FIND;
        filterInfo = keyword;
    }

    /**
     * Adds a new ToDo task to the task list.
     * also resets any active filter.
     * @param description the description of the ToDo task
     * @return the newly created ToDo task
     */
    public Task addToDoTask(String description) {
        Task toDoTask = new ToDo(description);
        addTask(toDoTask);
        return toDoTask;
    }

    /**
     * Adds a new Deadline task to the task list.
     * also resets any filter
     * @param description the description of the Deadline task
     * @param dueDateTime the due date and time of the Deadline task. This should be in the format "yyyy-MM-dd HHmm".
     * @return the newly created Deadline task
     */
    public Task addDeadlineTask(String description, String dueDateTime) {
        Task deadlineTask = new Deadline(description, dueDateTime);
        addTask(deadlineTask);
        return deadlineTask;
    }

    /**
     * Adds a new Event task to the task list.
     * also resets any filter
     * @param description the description of the Event task
     * @param startDateTime the start date and time of the Event task. This should be in the format "yyyy-MM-dd HHmm".
     * @param endDateTime the end date and time of the Event task. This should be in the format "yyyy-MM-dd HHmm".
     * @return the newly created Event task
     */
    public Task addEventTask(String description, String startDateTime, String endDateTime) {
        Task eventTask = new Event(description, startDateTime,endDateTime);
        addTask(eventTask);
        return eventTask;
    }

    /**
     * Adds a task to the main task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }


    /**
     * Marks a task as done.
     *
     * @param taskNum the number of the task to be marked as done (1-based index)
     * @return the task that was marked as done
     * @throws IndexOutOfBoundsException if the task number is out of range
     */
    public Task markDone(int taskNum) {
        Task task = !hasFilter() ? getTask(taskNum - 1) : getFilteredTask(taskNum - 1);
        task.setDone();
        return task;
    }

    /**
     * Marks a task as not done.
     *
     * @param taskNum the number of the task to be marked as not done (1-based index)
     * @return the task that was marked as not done
     * @throws IndexOutOfBoundsException if the task number is out of range
     */
    public Task markNotDone(int taskNum) {
        Task task = !hasFilter() ? getTask(taskNum - 1) : getFilteredTask(taskNum - 1);
        task.setNotDone();
        return task;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNum the number of the task to be deleted (1-based index)
     * @return the task that was deleted
     * @throws IndexOutOfBoundsException if the task number is out of range
     */
    public Task deleteTask(int taskNum) {
        Task task = !hasFilter() ? getTask(taskNum - 1) : getFilteredTask(taskNum - 1);
        tasks.remove(task);

        if (hasFilter()) {
            filteredTasks.remove(task);
        }

        return task;
    }

    /**
     * Generates a string representation of all tasks in the main task list.
     *
     * @return a formatted string containing all tasks, each on a new line
     */
    public String listOfTasksString() {
        StringBuilder listBuilder = new StringBuilder();

        for (int i = 0; i < getNumOfTasks(); i++) {
            String listItem = (i + 1) + "." + "\t" + getTask(i);

            if (i != 0) {
                listBuilder.append(System.lineSeparator());
            }

            listBuilder.append("\t").append(listItem);
        }

        return listBuilder.toString();
    }

    /**
     * Generates a string representation of all tasks in the filtered task list.
     *
     * @return a formatted string containing all filtered tasks, each on a new line
     */
    public String listOfFilteredTasksString() {
        StringBuilder listBuilder = new StringBuilder();

        for (int i = 0; i < getNumOfFilteredTasks(); i++) {
            String listItem = (i + 1) + "." + "\t" + getFilteredTask(i);

            if (i != 0) {
                listBuilder.append(System.lineSeparator());
            }

            listBuilder.append("\t").append(listItem);
        }

        return listBuilder.toString();
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return a string representing all tasks or filtered tasks if a filter is applied
     */
    @Override
    public String toString() {
        return !hasFilter() ? listOfTasksString() : listOfFilteredTasksString();
    }
}
