public class Deadline extends Task {
    protected String by;
    /**
     * Constructs a new Task with the given description.
     *
     * @param description Describes the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void printTask() {
        System.out.print("Got it. I've added this task:" + System.lineSeparator() + "  ");
        System.out.print("[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDescription());
        System.out.println("(by: " + by + ")");
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }
}
