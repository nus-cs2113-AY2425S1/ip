package Yukee.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * Represents an event with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event in the format "d/M/yyyy HHmm" (e.g., 10/12/2019 0900)
     * @param to the end time of the event in the format "d/M/yyyy HHmm" (e.g., 10/12/2019 1100)
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from, true);
        this.to = parseDateTime(to, true);
    }

    /**
     * Constructs an Event task for loading from storage.
     *
     * @param description the description of the event
     * @param from the start time string from storage
     * @param to the end time string from storage
     * @param isLoading flag indicating loading mode
     */
    public Event(String description, String from, String to, boolean isLoading) {
        super(description);
        this.from = parseDateTime(from, !isLoading);
        this.to = parseDateTime(to, !isLoading);
    }

    /**
     * Parses the date and time from the input string.
     *
     * @param dateTime the date and time string to parse
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
                    // During loading, return null or log a warning
                    System.out.println("Warning: Unable to parse date in storage file:" + dateTime);
                    return null;
                }
                System.out.println("Invalid date format. Please use the format:d/M/yyyy HHmm（such as：10/12/2019 0900）");
                Scanner scanner = new Scanner(System.in);
                dateTime = scanner.nextLine().trim();
            }
        }
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the start time of the event.
     *
     * @return the LocalDateTime object representing the start time
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the LocalDateTime object representing the end time
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        String fromString = (from != null) ? from.format(formatter) : "invalid date";
        String toString = (to != null) ? to.format(formatter) : "invalid date";
        return "[E]" + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
