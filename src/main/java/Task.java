public class Task {
    protected String description;
    protected boolean isDone;

    private static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }
    public void printAcknowledgementMessage(String message) {
        Nova.displayMessage("Got it. I've added this task:" + Nova.NEW_LINE + "  " + message +
                Nova.NEW_LINE + "Now you have " + numberOfTasks + " tasks in the list.");
    }

    public String getTaskInfo() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
