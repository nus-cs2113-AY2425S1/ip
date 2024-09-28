package medea.command.createTask;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;
import medea.command.Command;

public abstract class TaskCommand extends Command {

    public abstract String addTask(TaskList tasks);

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage ){
        String createdTask = addTask(tasks);
        int taskSize = tasks.getSize();

        String message = String.format("Noted. I've added this task:%n  %s%nNow you have %d tasks in the list.", createdTask, taskSize);
        ui.showMsg(message);
    }
}
