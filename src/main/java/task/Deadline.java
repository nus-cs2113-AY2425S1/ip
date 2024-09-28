package task;

import java.time.LocalDateTime;
import main.TaskList;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return ("[D][" + getDoneStatusIcon() + "] " + description + " (by: " + TaskList.convertDeadlineDateAsString(by) + ")");
    }

    @Override
    public String formattedTask() {
        return ("D | " + getDoneStatusIcon() + " | " + description + " | " + TaskList.convertDeadlineDateAsString(by));
    }

}
