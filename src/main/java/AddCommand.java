public class AddCommand extends Command {
    private String taskType;
    private String taskDetails;

    public AddCommand(String taskType, String taskDetails) {
        // Store the type of task and its details
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AirBorderException {
        // Add a new task based on the task type and details
        Task newTask;
        switch (taskType) {
            case "todo":
                if (taskDetails.isEmpty()) {
                    throw new AirBorderException("Task description cannot be empty.");
                }
                newTask = new ToDo(taskDetails);
                break;
            case "deadline":
                String[] deadlineDetails = taskDetails.split(" /by ");
                if (deadlineDetails.length < 2 || deadlineDetails[0].trim().isEmpty() || deadlineDetails[1].trim().isEmpty()) {
                    throw new AirBorderException("Invalid deadline format. Use: deadline <description> /by <time>");
                }
                newTask = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
                break;
            case "event":
                String[] eventDetails = taskDetails.split(" /from ");
                if (eventDetails.length < 2 || eventDetails[0].trim().isEmpty()) {
                    throw new AirBorderException("Invalid event format. Use: event <description> /from <start> /to <end>");
                }
                String[] timeDetails = eventDetails[1].split(" /to ");
                if (timeDetails.length < 2 || timeDetails[0].trim().isEmpty() || timeDetails[1].trim().isEmpty()) {
                    throw new AirBorderException("Invalid event time format. Use: event <description> /from <start> /to <end>");
                }
                newTask = new Event(eventDetails[0].trim(), timeDetails[0].trim(), timeDetails[1].trim());
                break;
            default:
                throw new InvalidCommandException();
        }
        // Add the new task to the task list
        tasks.addTask(newTask);
        // Display a message to the user
        ui.showTaskAdded(newTask, tasks.size());
        // Save the updated task list to storage
        storage.save(tasks);
    }
}
