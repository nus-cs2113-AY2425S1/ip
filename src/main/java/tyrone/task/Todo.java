package tyrone.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getNameWithStatus() {
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }

    @Override
    public String getSaveRecord() {
        return (isDone ? "1" : "0") + " todo " + description;
    }
}
