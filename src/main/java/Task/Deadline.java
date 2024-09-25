package Task;

public class Deadline extends Task{

    protected String dueTime;

    public Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    @Override
    public String toFile(String taskDescription, char status) {
        int byIndex = taskDescription.indexOf("(by: ");
        return taskDescription.charAt(1) + "|" + status + "|" + taskDescription.substring(6, byIndex-1) + "|" + taskDescription.substring(byIndex+5,taskDescription.length()-1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueTime + ")";
    }
}