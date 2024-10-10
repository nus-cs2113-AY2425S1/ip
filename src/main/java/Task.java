import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
  protected String description;
  protected boolean isDone;
  protected LocalDateTime deadline;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
    this.deadline = null;
  }

  public Task(String description, LocalDateTime deadline) {
    this.description = description;
    this.isDone = false;
    this.deadline = deadline;
  }

  public String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsNotDone() {
    this.isDone = false;
  }

  public boolean isDone() {
    return isDone;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getDeadline() {
    return deadline;
  }

  public String formatDeadline() {
    if (deadline != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
      return deadline.format(formatter);
    }
    return "No deadline";
  }

  @Override
  public String toString() {
    String deadlineStr = (deadline != null) ? " (by: " + formatDeadline() + ")" : "";
    return "[" + getStatusIcon() + "] " + description + deadlineStr;
  }
}
