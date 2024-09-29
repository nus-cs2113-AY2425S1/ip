/**
 * The UI class handles the display of messages and user interactions.
 * It provides methods for showing warnings, task-related messages, and status updates.
 */
public class UI {

    // Reference to the TaskList instance
    private TaskList taskListInstance;

    /**
     * Constructor for UI.
     * Associates the UI with a TaskList instance to display information related to tasks.
     *
     * @param taskListInstance The TaskList instance to be used by the UI.
     */
    public UI(TaskList taskListInstance) {
        this.taskListInstance = taskListInstance;
    }

    /**
     * Displays a warning message and throws a DukeException.
     *
     * @param message The warning message to be displayed.
     * @throws DukeException When a warning condition occurs.
     */
    public void Warning(String message) throws DukeException {
        throw new DukeException(message);
    }

    /**
     * Displays a general message.
     *
     * @param message The message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message when a task is added.
     * Shows the task details and the updated task count.
     *
     * @param taskCount The current number of tasks in the list after the addition.
     */
    public void addTaskMessage(int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskListInstance.getTask(taskCount - 1).toString());
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    /**
     * Displays a message when a task is deleted.
     * Shows the deleted task details and the updated task count.
     *
     * @param taskCount The current number of tasks after deletion.
     * @param deleted The details of the deleted task.
     */
    public void deleteTaskMessage(int taskCount, String deleted) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deleted);
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    /**
     * Displays a message when a task is marked as done.
     * Shows the task details with its marked status.
     *
     * @param taskIndex The index of the task that was marked as done.
     */
    public void markTaskMessage(int taskIndex) {
        System.out.println("Noted. I've marked this task:");
        System.out.print(taskIndex + ". ");
        System.out.println(taskListInstance.getTask(taskIndex - 1).toString());
    }

    /**
     * Displays a message when a task is marked as not done.
     * Shows the task details with its unmarked status.
     *
     * @param taskIndex The index of the task that was marked as not done.
     */
    public void unmarkTaskMessage(int taskIndex) {
        System.out.println("Noted. I've unmarked this task:");
        System.out.print(taskIndex + ". ");
        System.out.println(taskListInstance.getTask(taskIndex - 1).toString());
    }

    /**
     * Displays a task in the task list.
     * Shows the task index and details.
     *
     * @param index The index of the task to be displayed.
     */
    public void displayTask(int index) {
        System.out.print((index + 1) + ". ");
        System.out.println(taskListInstance.getTask(index).toString());
    }

    /**
     * Displays a found task matching a keyword search.
     * Shows the found task index in the list and its details.
     *
     * @param taskIndex The index of the found task in the task list.
     * @param displayIndex The index of the task in the found list (for display purposes).
     */
    public void displayTaskForFind(int taskIndex, int displayIndex) {
        System.out.print(displayIndex + ". ");
        System.out.println(taskListInstance.getTask(taskIndex).toString());
    }

    /**
     * Displays a message indicating the start of a keyword search.
     * Lists tasks that match the search keyword.
     */
    public void displayMessageForFind() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
