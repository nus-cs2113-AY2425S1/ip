package commands;

import java.io.IOException;

import storage.TaskEncoder;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command {
    private final Deadline deadline;
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws IOException {
        taskList.addTask(deadline);
        TaskEncoder.addTask(deadline.toString());
        ui.printAddMessage(taskList.get(taskList.size() - 1));
    }
}
