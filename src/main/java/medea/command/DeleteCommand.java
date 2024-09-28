package medea.command;

import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;

public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(int index){
        super(index);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage ){
        String deletedTask = tasks.deleteTask(this.getTaskIndex());
        int taskSize = tasks.getSize();

        String message = String.format("Noted. I've removed this task:%n  %s%nNow you have %d tasks in the list.", deletedTask, taskSize);
        ui.showMsg(message);
    }
}
