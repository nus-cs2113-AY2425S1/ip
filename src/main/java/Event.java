public class Event extends Task {
    protected String start;
    protected String end;
    /**
     * Constructs a new Task with the given description.
     *
     * @param description Describes the task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public void printTask() {
        System.out.print("Got it. I've added this task:" + System.lineSeparator() + "  ");
        System.out.print("[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDescription());
        System.out.println("(from: " + start + "to: " + end + ")");
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }
}
