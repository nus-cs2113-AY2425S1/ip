package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;
import chattycharlie.task.Deadline;
import chattycharlie.task.Task;

/**
 * Represents the command to be executed with the user inputs a deadline task.
 * This command will create a deadline task and save it to the task list
 */
public class DeadlineCommand implements Command {
    private String description;
    private String by;

    /**
     * Constructs a <code>DeadlineCommand</code> from the provided user input line.
     * Parses the input to extract the task description and deadline time.
     *
     * @param line the input line containing the command and task details.
     * @throws CharlieExceptions if the description or deadline time is missing.
     */
    public DeadlineCommand(String line) throws CharlieExceptions {
        String[] deadlineParts = line.substring(8).trim().split(" by ");
        if (deadlineParts[0].isEmpty()) {
            throw CharlieExceptions.missingDescription(CommandType.DEADLINE);
        } else if (deadlineParts.length < 2) {
            throw CharlieExceptions.missingDeadline();
        } else {
            this.description = deadlineParts[0].trim();
        }
        this.by = deadlineParts[1].trim();
    }

    /**
     * Executes the <code>DeadlineCommand</code> by adding the deadline task to the task list,
     * displaying a confirmation message to the user, and saving the updated task list.
     *
     * @param taskList the list of tasks to add the new deadline task to.
     * @param ui the user interface to display output to the user.
     * @param storage the storage system to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deadlineTask = new Deadline(description, by);
        taskList.addTask(deadlineTask);
        ui.displayTaskAdded(deadlineTask);
    }
}
