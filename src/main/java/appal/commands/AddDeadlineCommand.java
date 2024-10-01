package appal.commands;

import appal.exception.AppalException;
import appal.exception.EmptyTaskException;
import appal.exception.UnspecifiedDeadlineException;
import appal.storage.Storage;
import appal.task.Deadline;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.Utils.COMMAND_DEADLINE;
import static appal.common.Utils.TASK_INDEX;
import static appal.common.Utils.BY_INDEX;

/**
 * AddDeadlineCommand class adds a Deadline task,
 * with the task description and deadline date, to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String[] inputDetails;
    private boolean isFromUser;

    /**
     * Class constructor.
     *
     * @param inputDetails User's input that has been split into
     *                     command type, task description and deadline date parameters
     *                     by {@link appal.parser.Parser#extractInputDetails(String) extractInputDetails} method.
     * @param isFromUser Indicates whether instruction to add task is from the user or from pre-saved tasks.
     */
    public AddDeadlineCommand(String[] inputDetails, boolean isFromUser) {
        super(COMMAND_DEADLINE);
        this.inputDetails = inputDetails;
        this.isFromUser = isFromUser;
    }

    /**
     * Checks if task description is added as input by the user.
     *
     * @throws EmptyTaskException if task description is not specified.
     */
    public void checkForTask() throws EmptyTaskException {
        if (inputDetails[TASK_INDEX] == null) {
            throw new EmptyTaskException();
        }
    }

    /**
     * Executes the command to add a new Deadline task to the task list.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @param storage Storage instance for Appal to handle task loading and storage.
     * @throws AppalException if task description or deadline date is not specified.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        checkForTask();
        if (inputDetails[BY_INDEX] == null) {
            throw new UnspecifiedDeadlineException();
        }
        Deadline newDeadline = new Deadline(inputDetails[TASK_INDEX], inputDetails[BY_INDEX]);
        taskList.addTask(newDeadline);
        ui.updateUserOnAddedTask(taskList, isFromUser);
    }
}
