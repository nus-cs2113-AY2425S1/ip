package Yukee.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description the description of the task
     * @param by          the deadline of the task in the format "d/M/yyyy HHmm" (e.g., 2/12/2019 1800)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by, true);
    }

    /**
     * Constructs a Deadline task for loading from storage.
     *
     * @param description the description of the task
     * @param by          the deadline string from storage
     * @param isLoading   flag indicating loading mode
     */
    public Deadline(String description, String by, boolean isLoading) {
        super(description);
        this.by = parseDateTime(by, !isLoading);
    }

    /**
     * Parses the date and time from the input string.
     *
     * @param dateTime      the date and time string to parse
     * @param isInteractive whether to prompt the user for input
     * @return the parsed LocalDateTime object, or null if parsing fails during loading
     */
    private LocalDateTime parseDateTime(String dateTime, boolean isInteractive) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        while (true) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException e) {
                if (!isInteractive) {
                    // During loading, return null or throw an exception
                    return null;
                }
                System.out.println("invalid date。please use format：d/M/yyyy HHmm（such as：2/12/2019 1800）");
                Scanner scanner = new Scanner(System.in);
                dateTime = scanner.nextLine().trim();
            }
        }
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the deadline of the task.
     *
     * @return the LocalDateTime object representing the deadline
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        String byString = (by != null) ? by.format(formatter) : "无效的日期";
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
