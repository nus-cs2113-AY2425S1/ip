import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public AddDeadlineCommand(String input) {
        String[] parts = input.split("/by");
        this.description = parts[0].trim().substring(9).trim();
        this.by = parts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDateTime.parse(by, INPUT_FORMAT); // Validate the date format
            Task newTask = new Deadline(description, by);
            tasks.add(newTask);
            ui.showAddedTask(newTask, tasks.getTaskCount());
            storage.saveTasksToFile(tasks.getTasks(), tasks.getTaskCount());
        } catch (DateTimeParseException e) {
            ui.showError("Invalid date format. Please use yyyy-MM-dd HHmm format.");
        }
    }
}
