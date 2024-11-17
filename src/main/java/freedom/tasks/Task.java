package freedom.tasks;

import freedom.ui.UiTask;

/**
 * Class for storing all related information to a specific task.
 */
public class Task {
    protected static final UiTask ui = new UiTask();

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for <code>Task</code>.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     *
     * @throws Exception If task is already marked as done.
     */
    public void markDone() throws Exception {
        try {
            if (isDone) {
                throw new Exception();
            }
            this.isDone = true;
        } catch (Exception e) {
            ui.printAlreadyDone();
            throw new Exception();
        }
    }

    /**
     * Marks the task as undone.
     *
     * @throws Exception If task is already marked as not done.
     */
    public void markUndone() throws Exception {
        try {
            if (!isDone) {
                throw new Exception();
            }
            this.isDone = false;
        } catch (Exception e) {
            ui.printAlreadyUndone();
            throw new Exception();
        }
    }

    /**
     * Generates the icon for task status.
     *
     * @return <code>String</code> format "X" or " ".
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Generates a <code>String</code> format of <code>Task</code> for printing in CLI.
     *
     * @return <code>String</code> with <code>Task</code> details.
     */
    public String generateTaskLine() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Generates a <code>String</code> format of <code>Task</code> for writing to storage text file.
     *
     * @return <code>String</code> with <code>Task</code> details.
     */
    public String generateStorageLine() {
        return getStatusIcon() + " | " + getDescription();
    }

    /**
     * Checks if the <code>Task</code> contains a specified keyword.
     *
     * @param keyword user-defined keyword.
     * @return <code>true</code> if keyword is found, <code>false</code> otherwise.
     */
    public boolean containKeyword(String keyword) {
        return getDescription().contains(keyword);
    }

    public String getDoneBy() {
        return "";
    }

    public String getFrom() {
        return "";
    }

    public String getTo() {
        return "";
    }
}
