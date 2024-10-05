public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String taskInfo, String from, String to) {
        super(taskInfo);
        this.from = from;
        this.to = to;
    }

    /**
     * Prints an event task
     * Adds the type in front of the parent's printTask method and the to,from at the end
     * Prints in the format [T] [done] taskInfo (from:) (to:)
     */
    @Override
    public void printTask() {
        System.out.print("[E]");
        super.printTask();
        System.out.println("(from: " + from + " to: " + to + ")");
    }

    /**
     * Converts a event task to a valid save format
     * Adds the type in front of the parent's printTask method and the to, from at the end
     * Converts into a string of the format T | done | taskInfo | from | to
     * @return The converted event task
     */
    @Override
    public String convertToSaveFormat() {
        return "E | " + super.convertToSaveFormat() + " | " + from + " | " + to;
    }
}
