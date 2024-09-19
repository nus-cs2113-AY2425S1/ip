package bean.task;

import bean.exceptions.InsufficientSpaceException;

public class Task {

    protected String description;
    protected boolean isDone;
    private final static int MAX_LIST_COUNT = 100;

    // Constant
    protected final static String SEPARATOR_LINE = "____________________________________________________________________\n";
    protected final static String INDENT = "  ";
    private static final String DELIMITER = "//";

    // Constructors
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println(SEPARATOR_LINE +
                INDENT + "Added task: '" + description +  "'!\n" +
                SEPARATOR_LINE);
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    // Getters and Setters
    public void setDescription(String string) {
        this.description = string;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setStatus(Boolean status) {
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType () {
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else if (this instanceof Event) {
            return "E";
        } else {
            // Handle unexpected task types
            throw new IllegalStateException("Unknown task type");
        }
    }

    // Other methods
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    // Formats task into string consisting of the task's class name, a boolean indicating
    // whether it's done, the task description -- for storage in data.txt
    public String serialise() {
        String status = isDone? "1" : "0";
        return getTaskType() + DELIMITER + status + DELIMITER + description;
    }

}



