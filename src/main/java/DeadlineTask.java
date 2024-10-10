import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
  protected LocalDateTime by;

  public DeadlineTask(String description, LocalDateTime by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
  }
}
