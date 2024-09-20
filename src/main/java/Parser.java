import sleepy.task.Deadline;
import sleepy.task.Task;

public class Parser {
    public Task parseCommand(String command) {
        String[] parts = command.split("/by");
        String description = parts[0].trim().substring(8);
        String by = parts[1].trim();
        return new Deadline(description, by);
    }
}
