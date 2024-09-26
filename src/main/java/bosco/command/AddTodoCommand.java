package bosco.command;

import bosco.task.Todo;
import bosco.ui.Ui;

public class AddTodoCommand extends Command {
    private final Todo newTodo;

    public AddTodoCommand(String description) {
        this.newTodo = new Todo(description, false);
    }

    @Override
    public void execute() {
        tasks.addTask(newTodo);
        ui.printMessages(Ui.MESSAGE_ADDED_TASK, Ui.INDENT_EXTRA + newTodo,
                ui.getTaskCountMessage(tasks.getSize()));
    }
}
