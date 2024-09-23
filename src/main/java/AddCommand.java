public class AddCommand extends Command {
    private String taskName;

    public AddCommand(String userInput) {
        taskName = userInput.substring("todo ".length());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task task = new Todo(taskName);
        tasks.addTask(task);
        ui.showTodoAdded(tasks);
        storage.save(tasks);
    }
}
