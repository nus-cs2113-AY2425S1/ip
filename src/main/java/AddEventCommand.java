

public class AddEventCommand extends Command {
    private String taskName;
    private String from;
    private String to;

    public AddEventCommand(String userInput) {
        taskName = userInput.substring("event ".length(), userInput.indexOf("/from "));
        from = userInput.substring(userInput.indexOf("/from ") + "/from ".length(), userInput.indexOf("/to "));
        to = userInput.substring(userInput.indexOf(" /to ") + " /to ".length());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task task = new Event(taskName, from, to);
        tasks.addTask(task);
        ui.showEventAdded(tasks);
        storage.save(tasks);
    }
}
