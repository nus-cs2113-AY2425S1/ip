import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    // Static variables
    private String taskName;
    private TaskType type;
    private boolean isDone;
    private LocalDate deadline;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Enum for different task types
     */
    public enum TaskType {
        TODO, EVENT, DEADLINE
    }

    /**
     * ToDo Task constructor
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.type = TaskType.TODO;

        //this.isDone = false;
    }

    /**
     * Deadline Task constructor
     * @param taskName
     * @param deadline
     */
    public Task(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.type = TaskType.DEADLINE;
        this.deadline = deadline;

        //this.isDone = false;
    }

    /**
     * Event Task constructor
     * @param taskName
     * @param startTime
     * @param endTime
     */
    public Task(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        this.taskName = taskName;
        this.type = TaskType.EVENT;
        this.startTime = startTime;
        this.endTime = endTime;

        //this.isDone = false;
    }

    /**
     * Getters and setters for the Tasks.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getTypeIcon() {
        switch (type) {
            case TODO:
                return "T";
            case DEADLINE:
                return "D";
            case EVENT:
                return "E";

            default:
                return "?";
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

    /**
    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }
     */

    // Toggles the isDone status of the specified Task.
    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    // Returns a String of the specified task.
    public String toString() {
        String base = String.format("[%s][%s] %s", getTypeIcon(), isDone ? "X" : " ", taskName);

        switch (type) {
            case DEADLINE:
                return String.format("%s (by: %s)", base, deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            case EVENT:
                return String.format("%s (from: %s to: %s)", base,
                        startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }
        return base;
    }
}

