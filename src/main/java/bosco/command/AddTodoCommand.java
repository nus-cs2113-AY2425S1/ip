package bosco.command;

import bosco.task.Todo;
import bosco.ui.Ui;

/**
 * Represents the command to add a new Todo.
 */
public class AddTodoCommand extends Command {
    private final Todo newTodo;

    /**
     * Class constructor.
     *
     * @param description Description of the new Todo.
     */
    public AddTodoCommand(String description) {
        this.newTodo = new Todo(description, false);
    }

    /**
     * Overrides the default execute method to add the new Todo.
     * Prints message for the newly added Todo.
     */
    @Override
    public void execute() {
        tasks.addTask(newTodo);
        ui.printMessages(Ui.MESSAGE_ADDED_TASK, Ui.INDENT_EXTRA + newTodo,
                ui.getTaskCountMessage(tasks.getSize()));
    }
}
