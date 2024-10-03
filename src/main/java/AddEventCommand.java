public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.getTaskCount());
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
