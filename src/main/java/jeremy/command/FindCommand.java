package jeremy.command;

import jeremy.exception.EmptyArgumentException;
import jeremy.exception.InvalidCommandFormatException;
import jeremy.exception.InvalidTaskNumberException;
import jeremy.exception.TaskNotFoundException;

import jeremy.task.Task;
import jeremy.util.Storage;
import jeremy.util.TaskList;
import jeremy.util.Ui;

public class FindCommand extends Command {
    private final String argument;

    public FindCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNotFoundException, InvalidTaskNumberException, EmptyArgumentException, InvalidCommandFormatException {
        if (this.argument.isBlank()) {
            throw new EmptyArgumentException("Find argument cannot be empty");
        }

        int index = 1;
        ui.lineBreak();
        ui.println("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.contains(argument)) {
                ui.println(index + "." + task);
                index++;
            }
        }
        ui.lineBreak();
    }
}
