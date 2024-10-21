package appal.commands;

import appal.exception.AppalException;
import appal.exception.EmptyTaskException;
import appal.exception.UnspecifiedEventDurationException;
import appal.storage.Storage;
import appal.task.Event;
import appal.task.TaskList;
import appal.ui.Ui;

import static appal.common.Utils.COMMAND_EVENT;
import static appal.common.Utils.TASK_INDEX;
import static appal.common.Utils.FROM_INDEX;
import static appal.common.Utils.TO_INDEX;

/**
 * AddEventCommand class adds a Event task,
 * with the task description and event duration, to the task list.
 */
public class AddEventCommand extends Command {
    private String[] inputDetails;
    private boolean isFromUser;

    /**
     * Class constructor.
     *
     * @param inputDetails User's input that has been split into
     *                     command type, task description, start and end date parameters
     *                     by {@link appal.parser.Parser#extractInputDetails(String) extractInputDetails} method.
     * @param isFromUser Indicates whether instruction to add task is from the user or from pre-saved tasks.
     */
    public AddEventCommand(String[] inputDetails, boolean isFromUser) {
        super(COMMAND_EVENT);
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
     * Executes the command to add a new Event task to the task list.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @param storage Storage instance for Appal to handle task loading and storage.
     * @throws AppalException if task description or event duration is not specified.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        checkForTask();
        if (inputDetails[FROM_INDEX] == null || inputDetails[TO_INDEX] == null) {
            throw new UnspecifiedEventDurationException();
        }
        Event newEvent = new
                Event(inputDetails[TASK_INDEX], inputDetails[FROM_INDEX], inputDetails[TO_INDEX]);
        taskList.addTask(newEvent);
        saveTasks(storage, taskList);
        ui.updateUserOnAddedTask(taskList, isFromUser);
    }
}
