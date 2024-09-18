package bean.task;

import bean.exceptions.InsufficientSpaceException;

public class Task {

    protected String description;
    protected boolean isDone;

    // Constant
    protected final static String SEPARATOR_LINE = "____________________________________________________________________\n";
    protected final static String INDENT = "  ";

    // Constructors
    public Task(String description) {

        this.description = description;
        this.isDone = false;
        System.out.println(SEPARATOR_LINE +
                INDENT + "Added task: '" + description +  "'!\n" +
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
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    // Other methods
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
