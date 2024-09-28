package jeremy.command;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;
import jeremy.task.Deadline;
import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

public class DeadlineCommand extends Command {
    private final String argument;

    public DeadlineCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyArgumentException, InvalidCommandFormatException {
        tasks.addTask(new Deadline(argument));
    }
}
