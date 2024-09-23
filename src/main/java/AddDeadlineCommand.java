import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private String taskName;
    private String by;

    public AddDeadlineCommand(String userInput) {
        taskName = userInput.substring("deadline ".length(), userInput.indexOf("/by "));
        by = userInput.substring(userInput.indexOf("/by ") + "/by ".length());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate date = LocalDate.parse(by, formatter);
            Task task = new Deadline(taskName, date);
            tasks.addTask(task);
        } catch (DateTimeParseException e) {
            Task task = new Deadline(taskName, by);
            tasks.addTask(task);
        }
        ui.showDeadlineAdded(tasks);
        storage.save(tasks);
    }
}
