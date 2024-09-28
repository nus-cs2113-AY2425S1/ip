package jeremy.command;

import jeremy.exception.EmptyArgumentException;
import jeremy.task.Todo;
import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

public class TodoCommand extends Command {
    private final String argument;

    public TodoCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyArgumentException {
        tasks.addTask(new Todo(argument));
    }
}
