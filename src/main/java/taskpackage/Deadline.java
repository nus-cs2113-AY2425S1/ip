package taskpackage; // Package for Task-related classes

import customexceptions.DeadlineConstructorException; // Import custom exception for Deadline tasks

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline, extending the Task class.
 */
public class Deadline extends Task {

    private final LocalDateTime deadlineDateTime; // Stores the deadline date/time
    private final static String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm"; // Format for date and time
    private static final String DEADLINE_COMMAND = "deadline ";

    /**
     * Constructor for creating a Deadline task.
     *
     * @param inputString        The input string containing the task description and deadline.
     * @param tasks              The task list to which the task is added.
     * @param constructorMessage A flag to indicate whether to display a constructor message.
     * @throws DeadlineConstructorException if the task description or deadline is invalid.
     */
    public Deadline(String inputString, TaskList tasks, boolean constructorMessage) throws DeadlineConstructorException {
        super(inputString.split(" /by ")[0], tasks);
        this.inputString = inputString;

        this.storageString = DEADLINE_COMMAND + inputString;

        // Check if the "/by" keyword is present and if the task description is valid
        if (!(inputString.contains(" /by ")) || this.taskString.isEmpty()) {
            throw new DeadlineConstructorException(inputString);
        }

        // Parse the deadline date/time
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            this.deadlineDateTime = LocalDateTime.parse(inputString.replace("deadline ", "").split(" /by ")[1], dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DeadlineConstructorException(inputString);
        }

        // Display a confirmation message if the constructorMessage flag is true
        if (constructorMessage) {
            constructorMessage();
        }
    }

    /**
     * Returns the task's string with a checkbox and deadline details.
     *
     * @return The formatted task string with deadline information.
     */
    @Override
    public String checkboxString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return "[D]" + super.checkboxString() + " (by: " + deadlineDateTime.format(dateTimeFormatter) + ")";
    }
}
