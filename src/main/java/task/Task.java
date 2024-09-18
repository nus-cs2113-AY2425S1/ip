package task;

import others.TaskOperations;

public abstract class Task implements TaskOperations {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    // Constructor for Task
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    public String getMarker() {
        return isDone ? "X" : " ";
    }

    public String getTaskMarker() {
        return taskType;
    }

    // Method to mark as undone
    public void setAsUndone() {
        this.isDone = false;
    }

    // Method to mark as done
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Converts the task to a string format for display.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getTaskMarker() + "] " + "[" + getMarker() + "] " + description;
    }

    public abstract String toFileFormat();

    public static Task getFileFormat(String fileString) {
        String[] parts = fileString.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        Task task = null;

        switch (parts[0]) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], "");
            break;
        case "E":
            task = new Event(parts[2], "", "");
            break;
        default:
            System.out.println("Invalid task type encountered: " + parts[0]);
        }

        if (isDone) {
            task.setAsDone();
        }
        return task;
    }
}
