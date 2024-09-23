import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private String taskName;
    private String from;
    private String to;

    public AddEventCommand(String userInput) {
        taskName = userInput.substring("event ".length(), userInput.indexOf("/from "));
        from = userInput.substring(userInput.indexOf("/from ") + "/from ".length(), userInput.indexOf(" /to "));
        to = userInput.substring(userInput.indexOf(" /to ") + " /to ".length());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate dateFrom = LocalDate.parse(from, formatter);
            LocalDate dateTo = LocalDate.parse(to, formatter);
            Task task = new Event(taskName, dateFrom, dateTo);
            tasks.addTask(task);
        } catch (DateTimeParseException e) {
            Task task = new Event(taskName, from, to);
            tasks.addTask(task);
        }
        ui.showEventAdded(tasks);
        storage.save(tasks);
    }
}
