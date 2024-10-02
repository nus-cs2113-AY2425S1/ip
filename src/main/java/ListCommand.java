/**
 * Represents a command to list all tasks in the task list.
 * When executed, this command displays all tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   The list of tasks to display.
     * @param ui      The UI object to display the list of tasks.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTasks().isEmpty()) {
            ui.show("No tasks in your list.");
        } else {
            ui.show("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                Task task = tasks.getTasks().get(i);
                ui.show((i + 1) + ". " + task);
            }
        }
    }
}