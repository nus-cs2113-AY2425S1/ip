package commands;

import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command {
    private Deadline deadline;
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            taskList.addTask(deadline);
            ui.printAddMessage(taskList.get(taskList.size() - 1));
        } catch (StringIndexOutOfBoundsException e) {
            ui.invalidTask(deadline);
        }
    }
}
