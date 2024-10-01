public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Todo(description);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.getTaskCount());
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
