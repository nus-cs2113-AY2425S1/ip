package doug.Commands;

import doug.TaskList;
import doug.tasks.Deadline;

/**
 * Represents the actions of the Deadline command.
 * Adds a new Deadline task to the ArrayList of tasks
 */
public class AddDeadlineCommand extends Command {

    /**
     * Creates a new Deadline object and adds it to the existing TaskList
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param deadlineName Name of the new deadline task to be added
     * @param deadlineBy Deadline of the new deadline task to be added
     */
    public static void newDeadline(TaskList tasks, String deadlineName, String deadlineBy) {
        Deadline deadlineTask = new Deadline(deadlineName, deadlineBy);
        addNewTask(tasks, deadlineTask);
    }
}
