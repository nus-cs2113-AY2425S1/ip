package command;

import data.Storage;
import exceptions.IrisException;
import task.Task;
import task.type.Deadline;
import task.type.Event;
import task.TaskList;
import task.type.Todo;

/**
 * The AddCommand class handles the addition of new tasks (Todo, Deadline, or Event)
 * to the task list based on the command provided by the user. It also saves the
 * updated task list to storage.
 * <p>
 * The class supports adding:
 * <li>Todo tasks</li>
 * <li>Deadline tasks</li>
 * <li>Event tasks</li>
 *
 * @author Tan Ping Hui
 */
public class AddCommand extends Command {
    private final String command;
    private final String description;

    /**
     * Constructs an AddCommand object, storing the command type and task description.
     *
     * @param command The type of task to add ("todo", "deadline", or "event").
     * @param description The description of the task, including any additional details like dates.
     */
    public AddCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * Executes the command to add a new task (Todo, Deadline, or Event) to the task list.
     * The task is created based on the command type and added to the provided task list.
     *
     * @param tasks The task list to which the new task is added.
     * @param storage The storage to save the updated task list.
     * @throws IrisException If there is an issue creating the task (e.g., invalid format).
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws IrisException {
        Task newTask;
        switch (command) {
        case "todo":
            newTask = new Todo(description);
            break;
        case "deadline":
            newTask = new Deadline(description);
            break;
        default:
            newTask = new Event(description);
        }
        tasks.add(newTask);
        printAddTaskMessage(tasks);
    }

    /**
     * Prints a message confirming that a task has been added to the task list,
     * displaying the newly added task and the current number of tasks in the list.
     *
     * @param tasks The task list to which the new task has been added.
     */
    private static void printAddTaskMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:\n"
                + tasks.get(tasks.size() - 1)
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list");
    }
}
