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
            String[] deadlineParts = parts[2].split(" \\(by: ");
            String taskName = deadlineParts[0].trim();
            String dueDate = deadlineParts[1].replace(")", "").trim();
            task = new Deadline(taskName, dueDate);
            break;
        case "E":
            String[] eventParts = parts[2].split(" \\(from: | to: ");
            String eventName = eventParts[0].trim();
            String fromDate = eventParts[1].replace(")", "").trim();
            String toDate = eventParts[2].replace(")", "").trim();
            task = new Event(eventName, fromDate, toDate);
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
