public class Todo extends Task {
    public Todo(String taskInfo) {
        super(taskInfo);
    }

    /**
     * Prints a todo task
     * Adds the type in front of the parent's printTask method
     * Prints in the format [T] [done] taskInfo
     */
    @Override
    public void printTask() {
        System.out.print("[T]");
        super.printTask();
        System.out.println();
    }

    /**
     * Converts a todo task to a valid save format
     * Adds the type in front of the parent's printTask method
     * Converts into a string of the format T | done | taskInfo
     * @return The converted todo task
     */
    @Override
    public String convertToSaveFormat() {
        return "T | " + super.convertToSaveFormat();
    }
}
