package Ryan.commands;

import Ryan.utility.TaskList;
import Ryan.utility.Ui;

import Ryan.tasks.Task;
import Ryan.tasks.Deadline;

import Ryan.exceptions.RyanException;

public class DeadlineCommand extends Command {
    private final String command;
    private static final String DEADLINE_SPLIT_KEYWORD = "/by";

    public DeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws RyanException {
        String[] splitCommand = command.split(DEADLINE_SPLIT_KEYWORD, 2);

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

