package nus.edu.rizzler.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline, including a description and a due date.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the specified name, completion status, and due date.
     *
     * @param taskName The name of the task.
     * @param isDone The completion status of the task.
     * @param by The due date of the task.
     */
    public Deadline(String taskName, Boolean isDone, String by) {
        super(taskName, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(DateTimeFormatter.ofPattern("d MMM yyyy, ha")));
    }

    /**
     * Returns a CSV-formatted string representation of the deadline task.
     *
     * @return A CSV string representing the deadline task.
     */
    @Override
    public String toCSV(){
        return String.format("D, %s, %s", super.toCSV(),
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
