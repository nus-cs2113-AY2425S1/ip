package wildpeace.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
public class Task {
    private String description;
    private String deadline;
    private final TaskType taskType;
    private MarkStatus markStatus;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public enum MarkStatus {
        MARKED, UNMARKED
    }

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
        this.markStatus = MarkStatus.UNMARKED; // default status
    }

    public Task(String description, String deadline, TaskType taskType) {
        this.description = description;
        this.deadline = deadline;
        this.taskType = taskType;
        this.markStatus = MarkStatus.UNMARKED; // default status
    }

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
