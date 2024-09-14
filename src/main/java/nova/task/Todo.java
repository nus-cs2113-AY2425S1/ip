package nova.task;

public class Todo extends Task{

    public Todo (String description) {
        super(description);
        printAcknowledgementMessage(getTaskInfo());
    }

    public Todo (String isDone, String description) {
        super(description);
        if (isDone.equals("X")) {
            this.isDone = true;
        }
    }

    public String getTaskInfo() {
        return "[T][" + this.getStatusIcon() + "] " + description;
    }

    public String getTaskStorageInfo() {
        return "T" + DIVIDER + this.getStatusIcon() + DIVIDER + description;
    }
}
