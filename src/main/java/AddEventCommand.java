public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String input) {
        String[] parts = input.split("/from|/to");
        this.description = parts[0].trim().substring(6).trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks.getTaskCount());
        storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
    }
}
