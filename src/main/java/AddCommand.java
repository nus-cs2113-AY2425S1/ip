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
        try {
            switch (taskType) {
                case "todo":
                    task = new Todo(description);
                    break;
                case "deadline":
                    String[] parts = description.split(" /by ");
                    if (parts.length < 2) throw new BebeException("The deadline command requires a /by argument.");
                    task = new Deadline(parts[0], parts[1]);
                    break;
                case "event":
                    String[] eventParts = description.split(" /from | /to ");
                    if (eventParts.length < 3) throw new BebeException("The event command requires a /from and /to argument.");
                    task = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    break;
                default:
                    throw new BebeException("Unknown task type.");
            }
            tasks.addTask(task);
            storage.save(tasks.getTasks());

            // Show confirmation of added task
            ui.show("Got it. I've added this task:");
            ui.show("  " + task);
            ui.show("Now you have " + tasks.getTaskCount() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BebeException("Invalid input format. Please check your command.");
        }
    }
}
