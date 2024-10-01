public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String input) {
        String[] parts = input.split("/by");
        this.description = parts[0].trim().substring(9).trim();
        this.by = parts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadline(description, by);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.getTaskCount());
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
