
public class Deadline extends Task {

    static final String typeIcon = "[D]";

    String by;

    public Deadline(String newName, String newDeadline) {
        super(newName);
        this.by = newDeadline;
    }

    @Override
    public String getBy() {
        return by;
    }

    public void setBy(String newBy) {
        this.by = newBy;
    }

    @Override
    public String getTypeIcon() {
        return typeIcon;
    }

    // Irrelevant abstract methods declared in "Task" superclass
    public String getEventStart() {
        return null;
    }
    public String getEventEnd() {
        return null;
    }


}
