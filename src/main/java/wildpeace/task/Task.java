package wildpeace.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a description, a type (TODO, DEADLINE, or EVENT),
 * and optional dates for deadlines or events. Each task can be marked or unmarked as completed.
 */
public class Task {
    private String description;
    private String deadline;      // Deadline date for DEADLINE tasks
    private String fromDate;      // Start date for EVENT tasks
    private String toDate;        // End date for EVENT tasks
    private final TaskType taskType;
    private MarkStatus markStatus;

    /**
     * Represents the type of a task.
     * Can be TODO, DEADLINE, or EVENT.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * Represents the mark status of a task.
     * Can be MARKED (completed) or UNMARKED (not completed).
     */
    public enum MarkStatus {
        MARKED, UNMARKED
    }

    /**
     * Constructs a Task object with a description and a type.
     * The task is initialized as unmarked by default.
     *
     * @param description The description of the task.
     * @param taskType    The type of the task (TODO, DEADLINE, or EVENT).
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
        this.markStatus = MarkStatus.UNMARKED; // default status
    }

    /**
     * Constructs a Task object with a description, a deadline, and a type.
     * The task is initialized as unmarked by default.
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task (applicable to DEADLINE tasks).
     * @param taskType    The type of the task (DEADLINE).
     */
    public Task(String description, String deadline, TaskType taskType) {
        this.description = description;
        this.deadline = deadline;
        this.taskType = taskType;
        this.markStatus = MarkStatus.UNMARKED; // default status
    }

    /**
     * Constructs a Task object with a description, a start date, an end date, and a type.
     * The task is initialized as unmarked by default.
     *
     * @param description The description of the task.
     * @param fromDate    The start date of the task (applicable to EVENT tasks).
     * @param toDate      The end date of the task (applicable to EVENT tasks).
     * @param taskType    The type of the task (EVENT).
     */
    public Task(String description, String fromDate, String toDate, TaskType taskType) {
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.taskType = taskType;
        this.markStatus = MarkStatus.UNMARKED; // default status
    }

    // Getters and setters for description, deadline, fromDate, and toDate

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public MarkStatus getMarkStatus() {
        return markStatus;
    }

    public void mark() {
        this.markStatus = MarkStatus.MARKED;
    }

    public void unmark() {
        this.markStatus = MarkStatus.UNMARKED;
    }

    /**
     * Returns a string representation of the task.
     * Includes the task type, mark status, description, and (if applicable) the deadline or event dates.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String markStr = (markStatus == MarkStatus.MARKED) ? "[X]" : "[ ]";
        String taskTypeStr = "[" + taskType.toString().charAt(0) + "]";

        String dateStr = "";

        if (taskType == TaskType.DEADLINE && deadline != null && !deadline.isEmpty()) {
            try {
                LocalDate ddl = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                dateStr = " (by: " + ddl.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            } catch (DateTimeParseException e) {
                dateStr = " (invalid deadline format: " + deadline + ")";
            }
        } else if (taskType == TaskType.EVENT && fromDate != null && toDate != null) {
            try {
                LocalDate from = LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate to = LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                dateStr = " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                        " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            } catch (DateTimeParseException e) {
                dateStr = " (invalid event date format: " + fromDate + " to " + toDate + ")";
            }
        }

        return taskTypeStr + " " + markStr + " " + description + dateStr;
    }
}
