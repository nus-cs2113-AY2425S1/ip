package tyrone.task;

import tyrone.constants.Constants;

/**
 * Base Class for the command class
 */

public class Task {
    protected String description;
    protected boolean isDone;
    public static int listCount = Constants.toDoList.size();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }
}
