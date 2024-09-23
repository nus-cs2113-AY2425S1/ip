public class AddDeadlineCommand extends Command {
    private String taskName;
    private String by;

    public AddDeadlineCommand(String userInput) {
        taskName = userInput.substring("deadline ".length(), userInput.indexOf("/by "));
        by = userInput.substring(userInput.indexOf("/by ") + "/by ".length());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task task = new Deadline(taskName, by);
        tasks.addTask(task);
        ui.showDeadlineAdded(tasks);
        storage.save(tasks);
    }
}
