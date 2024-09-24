package medea.command.updateDone;

import medea.Storage;
import medea.TaskList;
import medea.Ui;
import medea.command.Command;

public class UpdateDoneCommand extends Command {
    private boolean isDone;

    public UpdateDoneCommand(int index, boolean isDone) {
        super(index);
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage ){
        String updatedTask = tasks.updateTaskDoneStatus(this.getTaskIndex(), isDone);

        String message = isDone ? "Got it! I've marked this task as done" : "Ok. I've unmarked this task";
        ui.showMsg(String.format("%s:\n  %s", message, updatedTask));
    }
}
