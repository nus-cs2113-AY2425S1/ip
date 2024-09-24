import exception.LeginEmptyTaskException;
import exception.LeginMissingParamsException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.util.ArrayList;

/**
 * Keeps track of the number of task the user has and stores all task information in an array of {@code Task}
 */
public class TaskList {
    private final int DECREMENT_FOR_ZERO_INDEX = 1;
    private ArrayList<Task> tasks;
    private int currentTaskCount;
    private Ui ui = new Ui();

    public TaskList(ArrayList<Task> tasks, int currentTaskCount) {
        this.currentTaskCount = currentTaskCount;
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return currentTaskCount;
    }

    /**
     * Prints out all the currently stored {@code Task} that the user has in the order of oldest to newest
     */
    public void list() {
        ui.horizontalLine();
        if (currentTaskCount == 0) {
            System.out.println("You have no tasks right now YIPPEE!");
        }
        ui.printTaskList(currentTaskCount, tasks);
        ui.horizontalLine();
    }

    /**
     * Adds a {@code Todo} to the current array of {@code Task} stored in private member {@code tasks} <br>
     * Prints out the confirmation of addition of a {@code Todo} task
     *
     * @param input User input in command line
     */
    public void addTodo(String input) {
        try {
            tasks.add(new Todo(input, false));
            ui.printAddedTaskMessage(tasks, currentTaskCount);
            currentTaskCount++;
        } catch(LeginEmptyTaskException e) {
            ui.printExceptionMessage(e);
        }
    }

    /**
     * Adds a {@code Deadline} to the current array of {@code Task} stored in private member {@code tasks} <br>
     * Prints out the confirmation of addition of a {@code Deadline} task
     *
     * @param input User input in command line
     */
    public void addDeadline(String input) {
        try {
            tasks.add(new Deadline(input));
            ui.printAddedTaskMessage(tasks, currentTaskCount);
            currentTaskCount++;
        } catch (LeginEmptyTaskException | LeginMissingParamsException e) {
            ui.printExceptionMessage(e);
        }
    }

    /**
     * Adds a {@code Event} to the current array of {@code Task} stored in private member {@code tasks} <br>
     * Prints out the confirmation of addition of a {@code Event} task
     *
     * @param input User input in command line
     */
    public void addEvent(String input) {
        try {
            tasks.add(new Event(input));
            ui.printAddedTaskMessage(tasks, currentTaskCount);
            currentTaskCount++;
        } catch (LeginEmptyTaskException | LeginMissingParamsException e) {
            ui.printExceptionMessage(e);
        }
    }

    /**
     * Searches for the task at index {@code index} in the {@code tasks} Array and change the task to done<br>
     * Prints a confirmation statement on the task that has been changed to done
     *
     * @param index Index of task in {@code tasks} Array to mark
     */
    public void markTask(int index) {
        ui.horizontalLine();
        Task taskToMark = tasks.get(index - DECREMENT_FOR_ZERO_INDEX);
        taskToMark.markTask();
        ui.printMarkTaskMessage(taskToMark);
        ui.horizontalLine();
    }

    /**
     * Searches for the task at index {@code index} in the {@code tasks} Array and change the task to undone<br>
     * Prints a confirmation statement on the task that has been changed to undone
     *
     * @param index Index of task in {@code tasks} Array to unmark
     */
    public void unmarkTask(int index) {
        ui.horizontalLine();
        Task taskToUnmark = tasks.get(index - DECREMENT_FOR_ZERO_INDEX);
        taskToUnmark.unmarkTask();
        ui.printUnmarkTaskMessage(taskToUnmark);
        ui.horizontalLine();
    }

    /**
     * Deletes a task from the task list at the index {@code index} <br>
     * Prints a confirmation of the task that the task has been deleted
     *
     * @param index Index of task in the {@code tasks} array
     */
    public void deleteTask(int index) {
        ui.horizontalLine();
        Task taskToRemove = tasks.get(index - DECREMENT_FOR_ZERO_INDEX);
        tasks.remove(index - DECREMENT_FOR_ZERO_INDEX);
        currentTaskCount--;
        ui.printDeleteTaskMessage(taskToRemove, currentTaskCount);
        ui.horizontalLine();
    }

    /**
     * Retrieves the words that the user want to find in the task list and removes whitespace from the ends of the input
     * <br> Calls {@code printAllMatchingTask} from the {@code Ui} class to print all matching tasks
     *
     * @param input User input in the command line
     */
    public void findAllMatchingTask(String input) {
        ui.horizontalLine();
        int indexOfFirstSpace = input.indexOf(" ");
        String matchingWords = input.substring(indexOfFirstSpace).trim();
        ui.printAllMatchingTask(matchingWords, currentTaskCount, tasks);
        ui.horizontalLine();
    }
}
