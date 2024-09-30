public class AddCommand extends Command {
    private final String taskType;
    private final String description;

    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BebeException {
        Task task;
        switch (taskType) {
            case "todo":
                task = new Todo(description);
                break;
            case "deadline":
                String[] parts = description.split(" /by ");
                task = new Deadline(parts[0], parts[1]);
                break;
            case "event":
                String[] eventParts = description.split(" /from | /to ");
                task = new Event(eventParts[0], eventParts[1], eventParts[2]);
                break;
            default:
                throw new BebeException("Unknown task type.");
        }
        tasks.addTask(task);
        storage.save(tasks.getTasks());
    }
}
