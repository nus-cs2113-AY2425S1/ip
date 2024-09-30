public class ListCommand extends Command {

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