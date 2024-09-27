package bob.command;
import bob.task.Task;
import bob.task.TaskList;
import bob.task.ToDo;
import bob.ui.Ui;
import bob.storage.Storage;

public class AddTodoCommand extends Command {

    private static final String COMMAND_TODO = "todo";
    private final String input;

    public AddTodoCommand(String command) {
        this.input = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String description = this.input.substring(COMMAND_TODO.length()).trim();

        if (description.isEmpty()) {
            ui.printEmptyDescription("todo");
        } else {
            Task newTask = new ToDo(description);
            tasks.addTask(newTask);
            ui.printAddedTask(tasks);
            storage.appendTask(newTask);
            storage.save(tasks.getTaskList());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
