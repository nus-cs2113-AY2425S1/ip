/**
 * Represents a task with a deadline. DeadlineTask class extends the Task class and includes a
 * deadline, as the name suggests, by which the task needs to be completed.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

  protected LocalDateTime by;

  public DeadlineTask(String description, LocalDateTime by) {
    /**
     * Constructs a DeadlineTask with the specified description and deadline.
     */
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    /**
     * Returns a string representation of the DeadlineTask.
     * The format includes the task type, description, and deadline.
     */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
  }
}
