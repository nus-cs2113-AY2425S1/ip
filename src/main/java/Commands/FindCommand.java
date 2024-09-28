package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

import Task.Task;

public class FindCommand extends Command {

    private TaskList taskList;

    public FindCommand(String instruction) {
        super(instruction);
        this.taskList = new TaskList();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        for (Task task : taskList.getList()) {
            if (task.getDescription().contains(instruction.trim())) {
                this.taskList.addTask(task);
            }
        }
        ui.showList(this.taskList);
    }

}
