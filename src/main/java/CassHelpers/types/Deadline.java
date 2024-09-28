package CassHelpers.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;
    protected LocalDateTime deadline;

    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        setDeadline(deadline);
        setBy(this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm")));
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public String getBy(){
        return this.by;
    }

    public void setBy(String by){
        this.by = by;
    }

    public String getDeadlineString(){
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadlineString() + ")";
    }

    public String toWritableString(){
        return "D" + super.toWritableString() + ","+this.by;
    }
}
