package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.userinteractions.Storage;
import chattycharlie.TaskList;
import chattycharlie.userinteractions.Ui;
import chattycharlie.task.Deadline;
import chattycharlie.task.Task;

public class DeadlineCommand implements Command {
    private String description;
    private String by;

    public DeadlineCommand(String line) throws CharlieExceptions {
        String[] deadlineParts = line.substring(8).trim().split(" by ");
        if (deadlineParts[0].isEmpty()) {
            throw CharlieExceptions.missingDescription(CommandType.DEADLINE);
        } else if (deadlineParts.length < 2) {
            throw CharlieExceptions.missingDeadline();
        } else {
            this.description = deadlineParts[0].trim();
        }
        this.by = deadlineParts[1].trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deadlineTask = new Deadline(description, by);
        taskList.addTask(deadlineTask);
        ui.displayTaskAdded(deadlineTask);
    }
}
