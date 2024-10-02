public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Display the list of tasks to the user
        ui.showTaskList(tasks);
    }
}
