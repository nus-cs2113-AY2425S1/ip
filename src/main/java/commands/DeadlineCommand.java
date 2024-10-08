package commands;

import tasks.Deadlines;
import tasks.TaskList;
import ui.Ui;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private Deadlines deadline;
    public DeadlineCommand(Deadlines deadline) {
        this.deadline = deadline;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(deadline);
        ui.printAddMessage(taskList.get(taskList.size() - 1));
    }
}
