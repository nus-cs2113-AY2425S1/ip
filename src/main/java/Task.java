import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String taskName;
    private TaskType type;
    private boolean isDone;
    private LocalDate deadline;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public enum TaskType {
        TODO, EVENT, DEADLINE
    }

    /**
     * ToDo task constructor
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.type = TaskType.TODO;
        this.isDone = false;
    }

    /**
     * Deadline task constructor
     * @param taskName
     * @param deadline
     */
    public Task(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.type = TaskType.DEADLINE;
        this.deadline = deadline;
        this.isDone = false;
    }

    /**
     * Event task constructor
     * @param taskName
     * @param startTime
     * @param endTime
     */
    public Task(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        this.taskName = taskName;
        this.type = TaskType.EVENT;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = false;
    }

    /**
     * Getter methods for tasks.
     * @return
     */
    public String getTypeIcon() {
        switch (type) {
            case TODO: return "T";
            case DEADLINE: return "D";
            case EVENT: return "E";
            default: return "?";
        }
    }

    public String getName() {
        return taskName;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Marks specified task as done.
     * @throws PlopBotException
     */
    public void markAsDone() throws PlopBotException {
        if (isDone) {
            throw new PlopBotException("Task is already marked as done.");
        }
        isDone = true;
    }

    /**
     * Unmarks specified task as not-done.
     * @throws PlopBotException
     */
    public void markAsUndone() throws PlopBotException {
        if (!isDone) {
            throw new PlopBotException("Task is not yet done.");
        }
        isDone = false;
    }

    /**
     * Converts tasks into strings and returns them.
     * @return
     */
    @Override
    public String toString() {
        String base = String.format("[%s][%s] %s", getTypeIcon(), isDone ? "X" : " ", taskName);
        switch (type) {
            case DEADLINE:
                return String.format("%s (by: %s)", base, deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            case EVENT:
                return String.format("%s (from: %s to: %s)", base,
                        startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            default:
                return base;
        }
    }
}
