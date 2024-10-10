/**
 * Represents a task with a description and an optional deadline.
 * The task can be marked as 'done' or 'not done', and its details can
 * be retrieved and formatted for display.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
  protected String description; //task description
  protected boolean isDone; //status of the task (done or not done yet)
  protected LocalDateTime deadline; //deadline of the task (optional information to add)

  public Task(String description) {
    /**
     * Constructs a new Task with the specified description.
     * A task is initially marked as not done and has no deadline.
     */
    this.description = description;
    this.isDone = false;
    this.deadline = null;
  }

  public Task(String description, LocalDateTime deadline) {
    /**
     * Constructs a new Task with the specified description and deadline.
     * The task is initially marked as not done by Ruhi.
     */
    this.description = description;
    this.isDone = false;
    this.deadline = deadline;
  }

  public String getStatusIcon() {
    // completion status of the task.
    return (isDone ? "X" : " ");
  }

  public void markAsDone() {
    // marks task as done.
    this.isDone = true;
  }

  public void markAsNotDone() {
    // marks task as not done.
    this.isDone = false;
  }

  public boolean isDone() {
    /**
     * checks if task is marked as done.
     */
    return isDone;
  }

  public String getDescription() {
    /**
     * Retrieves description of task.
     */
    return description;
  }

  public LocalDateTime getDeadline() {
    /**
     * Retrieves deadline of the task.
     */
    return deadline;
  }

  public String formatDeadline() {
    /**
     * Formats deadline of the task to display to the user.
     */
    if (deadline != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
      return deadline.format(formatter);
    }
    return "No deadline";
  }

  @Override
  public String toString() {
    /**
     * Returns a string representation of the task, including its
     * status icon, description, and deadline if available.
     */
    String deadlineStr = (deadline != null) ? " (by: " + formatDeadline() + ")" : "";
    return "[" + getStatusIcon() + "] " + description + deadlineStr;
  }
}
