package bob.command;
import bob.task.Deadline;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.storage.Storage;

public class AddDeadlineCommand extends Command {
    private final String input;

    private static final String COMMAND_DEADLINE = "deadline";
    private static final String DEADLINE_BY = " /by ";

    public AddDeadlineCommand(String command) {
        this.input = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] components = this.input.split(DEADLINE_BY);
        String description = components[0].substring(COMMAND_DEADLINE.length()).trim();
        if (description.isEmpty()) {
            ui.printEmptyDescription("deadline");
        } else {
            try {
                String deadlineBy = components[1];
                Task newTask = new Deadline(description, deadlineBy);
                tasks.addTask(newTask);
                ui.printAddedTask(tasks);
                storage.appendTask(newTask);
                storage.save(tasks.getTaskList());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printInvalidDeadline();
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
