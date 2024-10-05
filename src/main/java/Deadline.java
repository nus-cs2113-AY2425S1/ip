public class Deadline extends Task {
    String dueBy;

    public Deadline(String taskInfo, String dueBy) {
        super(taskInfo);
        this.dueBy = dueBy;
    }

    /**
     * Prints an event task
     * Adds the type in front of the parent's printTask method and the to,from at the end
     * Prints in the format [T] [done] taskInfo (from:) (to:)
     */
    @Override
    public void printTask() {
        System.out.print("[D]");
        super.printTask();
        System.out.println("(by: " + dueBy + ")");
    }

    /**
     * Converts a event task to a valid save format
     * Adds the type in front of the parent's printTask method and the to, from at the end
     * Converts into a string of the format T | done | taskInfo | from | to
     * @return The converted event task
     */
    @Override
    public String convertToSaveFormat() {
        return "D | " + super.convertToSaveFormat() + " | " + dueBy;
    }
}
