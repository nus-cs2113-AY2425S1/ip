package appal.commands;

import appal.exception.AppalException;
import appal.exception.EmptyTaskException;
import appal.storage.Storage;
import appal.task.TaskList;
import appal.task.ToDo;
import appal.ui.Ui;

import static appal.common.Utils.COMMAND_TODO;
import static appal.common.Utils.TASK_INDEX;

/**
 * AddToDoCommand class adds a ToDo task,
 * with the task description, to the task list.
 */
public class AddToDoCommand extends Command {
    private String[] inputDetails;
    private boolean isFromUser;

    /**
     * Class constructor.
     *
     * @param inputDetails User's input that has been split into
     *                     command type and task description parameters
     *                     by {@link appal.parser.Parser#extractInputDetails(String) extractInputDetails} method.
     * @param isFromUser Indicates whether instruction to add task is from the user or from pre-saved tasks.
     */
    public AddToDoCommand(String[] inputDetails, boolean isFromUser) {
        super(COMMAND_TODO);
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
     * Executes the command to add a new ToDo task to the task list.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @param storage Storage instance for Appal to handle task loading and storage.
     * @throws AppalException if task description is not specified.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AppalException {
        checkForTask();
        ToDo newToDo = new ToDo(inputDetails[TASK_INDEX]);
        taskList.addTask(newToDo);
        saveTasks(storage, taskList);
        ui.updateUserOnAddedTask(taskList, isFromUser);
    }
}
