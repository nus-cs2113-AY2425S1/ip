package wildpeace.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Represents a task with a description, a type (TODO, DEADLINE, or EVENT),
 * and an optional deadline. Each task can be marked or unmarked as completed.
 */


public class Task {
    private String description;
    private String deadline;
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
     * @param taskType    The type of the task (TODO, DEADLINE, or EVENT).
     */
    public Task(String description, String deadline, TaskType taskType) {
        this.description = description;
        this.deadline = deadline;
        this.taskType = taskType;
        this.markStatus = MarkStatus.UNMARKED; // default status
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline of the task, or null if not applicable.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline of the task.
     *
     * @param deadline The new deadline of the task.
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns the type of the task (TODO, DEADLINE, or EVENT).
     *
     * @return The type of the task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Returns the mark status of the task (MARKED or UNMARKED).
     *
     * @return The mark status of the task.
     */
    public MarkStatus getMarkStatus() {
        return markStatus;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.markStatus = MarkStatus.MARKED;
    }

    /**
     * Unmarks the task as not completed.
     */
    public void unmark() {
        this.markStatus = MarkStatus.UNMARKED;
    }

    /**
     * Returns a string representation of the task.
     * Includes the task type, mark status, description, and (if applicable) the deadline.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        String markStr = (markStatus == MarkStatus.MARKED) ? "[X]" : "[ ]";
        String taskTypeStr = "[" + taskType.toString().charAt(0) + "]";
        String deadlineStr = "";

        if (deadline != null && !deadline.isEmpty()) {
            try {
                LocalDate ddl = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                deadlineStr = " (by: " + ddl.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            } catch (DateTimeParseException e) {
                deadlineStr = " (invalid deadline format: " + deadline + ")";
            }
        }

        return taskTypeStr + " " + markStr + " " + description + deadlineStr;
    }

}
