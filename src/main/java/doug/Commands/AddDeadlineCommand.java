package doug.Commands;

import doug.Storage;
import doug.TaskList;
import doug.UI;
import doug.tasks.Deadline;

/**
 * Represents the actions of the Deadline command.
 * Adds a new Deadline task to the ArrayList of tasks
 */
public class AddDeadlineCommand extends Command {

    private static String deadlineName;
    private static String deadlineBy;

    public AddDeadlineCommand(String deadlineName, String deadlineBy) {
        AddDeadlineCommand.deadlineName = deadlineName;
        AddDeadlineCommand.deadlineBy = deadlineBy;
    }

    /**
     * Creates a new Deadline object and adds it to the existing TaskList
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param ui The UI object
     * @param storage The Storage object
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Deadline deadlineTask = new Deadline(deadlineName, deadlineBy);
        addNewTask(tasks, deadlineTask, ui, storage);
    }
}
