package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

import Task.Task;

/**
 * Represents a command to find tasks that match a given description.
 */
public class FindCommand extends Command {

    private TaskList taskList;

    /**
     * Constructs a FindCommand with the specified instruction.
     *
     * @param instruction The description to search for in tasks.
     */
    public FindCommand(String instruction) {
        super(instruction);
        this.taskList = new TaskList();
    }

    /**
     * Executes the find command by searching through the given task list
     * for tasks containing the specified description.
     *
     * @param taskList The list of tasks to search through.
     * @param ui The user interface to display results.
     * @param storage The storage to manage task data.
     * @throws AlyException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        if (instruction.isEmpty()) {
            throw new AlyException("How to find anything without details bro...");
        }
        for (Task task : taskList.getList()) {
            if (task.getDescription().toLowerCase().contains(instruction.trim().toLowerCase())) {
                this.taskList.addTask(task);
            }
        }
        if (this.taskList.getSize() == 0) {
            throw new AlyException("There is nothing to find!");
        }
        ui.showList(this.taskList);
    }
}