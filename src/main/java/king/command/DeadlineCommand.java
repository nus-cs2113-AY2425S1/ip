package king.command;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;

import java.io.IOException;

public class DeadlineCommand extends Command {
    private String taskDescription;

    public DeadlineCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException, IOException {
        tasks.addDeadlineTask(taskDescription);
    }
}
