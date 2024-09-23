package task;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected String date;

    private static String formatDate(String date) {
		String[] textParts = date.split(" ", 2);
		return textParts[0] + ": " + textParts[1];
    }

    /**
     * Separates description from the dates in details and initialise accordingly
     * @param details is the user input without command
     */
    public Task(String details) {
        this.isDone = false;
		String[] textParts = details.split("/");
		this.description = textParts[0];

		int numOfParts = textParts.length;
        if (numOfParts == 1) {
            this.date = "";
            return;
        }
        String date = "(";
        for (int i = 1; i < numOfParts; i++) {
            date = date.concat(formatDate(textParts[i]));
        }
        this.date = date.concat(")");
    }

    /**
     * Getter for task status
     * @return a string that represents whether the task is done or not
     */
    public String getStatus() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * @return a string representation of task
     */
    @Override
    public String toString() {
        return getStatus() + description + date;
    }

    /**
     * Setter for isDone
     * @param status is the current task status
     */
    public void mark(boolean status) {
        isDone = status;
    }
}
