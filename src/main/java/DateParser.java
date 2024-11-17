import sleepy.Task.Deadline;
import sleepy.Task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Task parseCommand(String command) {
        try {
            String[] parts = command.split("/by");
            // Extract task description after "deadline "
            String description = parts[0].trim().substring(8);
            // Extract date and time part
            String by = parts[1].trim();

            // Parse the date-time string into LocalDateTime using the INPUT_FORMAT
            LocalDateTime deadlineDateTime = LocalDateTime.parse(by, INPUT_FORMAT);

            // Create a new Deadline task with the parsed description and date-time
            return new Deadline(description, deadlineDateTime);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date-time. Please use the format: yyyy-MM-dd HHmm");
            return null; 
        }
    }
}