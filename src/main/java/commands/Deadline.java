package commands;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return ("[D]" + super.getStatusIcon());
    }

    public String createDeadlineList() {
        return (getStatusIcon()+ " " +  description + "(by:" + by + ")");
    }

    public String createDeadlineTxt() {
        return ("D | " + super.getStatus() + " | " + description + " | " + by);
    }
}
