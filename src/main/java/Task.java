public class Task {

    protected String description;
    protected boolean isDone;
    protected static int numberOfTasks = 0;

    // Constant
    protected final static String SEPARATOR_LINE = "____________________________________________________________________\n";
    protected final static String INDENT = "  ";

    // Constructors
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
        System.out.println(SEPARATOR_LINE +
                INDENT + "Added task: '" + description +  "'!\n" +
                INDENT + "Now, you have " + numberOfTasks + " tasks in your to do list.\n" +
                SEPARATOR_LINE);
    }

    // Getters and Setters
    public void setDescription(String string) {
        this.description = string;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(Boolean status) {
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    // Other methods
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
