package tyrone.task;

import tyrone.constants.Constants;
import tyrone.exceptions.MissingTimeInfoException;
import tyrone.exceptions.TyroneException;
import tyrone.exceptions.WrongDeadlineFormatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline specific functions.
 */

public class Deadline extends Task {
    protected String doBy;
    protected LocalDateTime dueDateTime;

    public Deadline(String description, LocalDateTime dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
        this.doBy = dueDateTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu h:mma"));
    }

    public String getDoBy() {
        return doBy;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public static void createDeadline(String userInput) throws TyroneException {
        if (!userInput.contains("/by")) {
            throw new WrongDeadlineFormatException();
        }
    
        String[] parts = userInput.split(" /by ");
    
        // Check if the description part exists and is long enough
        if (parts[0].length() < 9) {
            throw new WrongDeadlineFormatException();
        }
    
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingTimeInfoException();
        }
    
        String description = parts[0].substring(9); // Extracting description after "deadline "
        String byString = parts[1].trim(); // Extracting the due date and time
        System.out.println(byString);
    
        try {
            // Parse the due date and time into LocalDateTime
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dueDateTime = LocalDateTime.parse(byString, inputFormatter);
    
            // Create and add the new deadline task
            Deadline deadlineTask = new Deadline(description, dueDateTime);
            Constants.toDoList.add(deadlineTask);
            Task.listCount++;
    
            // Print success message with formatted date using formatDoBy()
            System.out.println(Constants.LINE);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      [D][ ] " + deadlineTask.getDescription() + " (by: " + deadlineTask.getDoBy() + ")");
            System.out.println("    Now you have " + Constants.toDoList.size() + " tasks in the list.");
            System.out.println(Constants.LINE);
    
        } catch (DateTimeParseException e) {
            throw new TyroneException("Invalid date format! Please use d/M/yyyy HHmm (e.g., 2/12/2019 1800).");
        }
    }    
}
