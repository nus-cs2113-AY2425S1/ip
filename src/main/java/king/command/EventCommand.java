package king.command;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;

import java.io.IOException;

public class EventCommand extends Command {
    private String taskDescription;

    public EventCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException, IOException {
        tasks.addEventTask(taskDescription);
    }
}
