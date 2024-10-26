import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the PlopBot task management system.
 * Tasks can be of three types: TODO, EVENT, or DEADLINE.
 * Each task has a name and completion status, with additional
 * temporal attributes based on its type.
 */
public class Task {
    private String taskName;
    private TaskType type;
    private boolean isDone;
    private LocalDate deadline;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Enumeration of possible task types in the system.
     * TODO:     A basic task with just a description
     * EVENT:    A task with start and end times
     * DEADLINE: A task with a completion deadline
     */
    public enum TaskType {
        TODO, EVENT, DEADLINE
    }

    /**
     * Constructs a new TODO task with the specified name.
     * The task is initially marked as not done.
     *
     * @param taskName - The name or description of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.type = TaskType.TODO;
        this.isDone = false;
    }

    /**
     * Constructs a new DEADLINE task with the specified name and deadline.
     * The task is initially marked as not done.
     *
     * @param taskName - The name or description of the task
     * @param deadline - The date by which the task must be completed
     */
    public Task(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.type = TaskType.DEADLINE;
        this.deadline = deadline;
        this.isDone = false;
    }

    /**
     * Constructs a new EVENT task with the specified name, start time, and end time.
     * The task is initially marked as not done.
     *
     * @param taskName  - The name or description of the task
     * @param startTime - The date and time when the event starts
     * @param endTime   - The date and time when the event ends
     */
    public Task(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        this.taskName = taskName;
        this.type = TaskType.EVENT;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = false;
    }

    /**
     * Returns the type identifier of the task.
     * 'T' for TODO, 'D' for DEADLINE, 'E' for EVENT.
     *
     * @return - A single character string representing the task type
     */
    public String getTypeIcon() {
        switch (type) {
            case TODO: return "T";
            case DEADLINE: return "D";
            case EVENT: return "E";
            default: return "?";
        }
    }

    /**
     * Returns the name or description of the task.
     *
     * @return - The task's name
     */
    public String getName() {
        return taskName;
    }

    /**
     * Returns the deadline date for DEADLINE tasks.
     *
     * @return - The task's deadline date, or null if not a DEADLINE task
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Returns the start time for EVENT tasks.
     *
     * @return - The event's start time, or null if not an EVENT task
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time for EVENT tasks.
     *
     * @return - The event's end time, or null if not an EVENT task
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return - true if the task is marked as done, false otherwise
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Toggles the completion status of the task.
     * If the task was marked as done, it will be marked as not done, and vice versa.
     */
    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Marks the task as completed.
     *
     * @throws PlopBotException - If the task is already marked as done
     */
    public void markAsDone() throws PlopBotException {
        if (isDone) {
            throw new PlopBotException("Task is already marked as done.");
        }
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     *
     * @throws PlopBotException - If the task is already marked as not done
     */
    public void markAsUndone() throws PlopBotException {
        if (!isDone) {
            throw new PlopBotException("Task is not yet done.");
        }
        isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The format varies based on the task type:
     * - TODO:     [T][status] description
     * - DEADLINE: [D][status] description (by: YYYY-MM-DD)
     * - EVENT:    [E][status] description (from: YYYY-MM-DD HH:mm to: YYYY-MM-DD HH:mm)
     * where status is 'X' for done tasks and ' ' for not done tasks.
     *
     * @return - A formatted string representation of the task
     */
    @Override
    public String toString() {
        String base = String.format("[%s][%s] %s", getTypeIcon(), isDone ? "X" : " ", taskName);
        switch (type) {
        case DEADLINE:
            return String.format("%s (by: %s)", base,
                   deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        case EVENT:
            return String.format("%s (from: %s to: %s)", base,
                   startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                   endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        default:
            return base;
        }
    }
}
