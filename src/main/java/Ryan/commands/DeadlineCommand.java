package Ryan.commands;

import Ryan.exceptions.RyanException;
import Ryan.utility.TaskList;
import Ryan.utility.Ui;
import Ryan.tasks.*;

public class DeadlineCommand extends Command {
    private final String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        String[] splitCommand = command.split("/by", 2);

        if (splitCommand.length < 2) {
            throw new RyanException("Deadline tasks should be in the format 'description /by deadline'.");
        }

        String description = splitCommand[0].trim();
        String by = splitCommand[1].trim();
        Task task = new Deadline(description, by);
        tasks.addTask(task);

        ui.showTaskAdded(task, tasks.size());
    }
}

