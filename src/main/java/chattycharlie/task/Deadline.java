package chattycharlie.task;

import chattycharlie.commands.CommandType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Deadline Class
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description, CommandType.DEADLINE);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "[D]" + super.toSaveFormat() + " (by: " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }
}