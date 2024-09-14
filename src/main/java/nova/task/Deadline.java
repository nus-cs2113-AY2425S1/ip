package nova.task;

import nova.Storage;

public class Deadline extends Task{

    String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
        printAcknowledgementMessage(getTaskInfo());
    }

    public Deadline (String isDone, String description, String by) {
        super(description);
        this.by = by;
        if (isDone.equals("X")) {
            this.isDone = true;
        }
    }

    public String getTaskInfo() {
        return "[D][" + this.getStatusIcon() + "] " + description + " (by: " + by + ")";
    }

    public String getTaskStorageInfo() {
        return "D" + DIVIDER + this.getStatusIcon() + DIVIDER + description + DIVIDER + by;
    }
}
