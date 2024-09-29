package task;

import others.TaskOperations;

public abstract class Task implements TaskOperations {
    public static final String TODO = "T";
    public static final String DEADLINE = "D";
    public static final String EVENT = "E";
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    /**
     * Gets the task description.
     *
     * @return The description of the task.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Returns a marker about if the task is done or not.
     *
     * @return "X" if the task is done.
     */
    public String getMarker() {
        return isDone ? "X" : " ";
    }

    /**
     * Gets the task type marker.
     *
     * @return The type marker for the task.
     */
    public String getTaskMarker() {
        return taskType;
    }

    /**
     * Marks the task as undone.
     */
    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the task and the status.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getTaskMarker() + "] " + "[" + getMarker() + "] " + description;
    }

    /**
     * Converts the task for saving to a file.
     *
     * @return A string representation of the task in file format.
     */
    public abstract String toFileFormat();

    /**
     * Obtains the various tasks commands from the file.
     *
     * @param fileString The tasklist of tasks from a file.
     * @return A Task object created from the file string.
     */
    public static Task getFileFormat(String fileString) {
        String[] parts = fileString.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        Task task = null;

        switch (parts[0]) {
        case TODO:
            task = new ToDo(parts[2]);
            break;
        case DEADLINE:
            String[] deadlineParts = parts[2].split(" \\(by: ");
            String taskName = deadlineParts[0].trim();
            String dueDate = deadlineParts[1].replace(")", "").trim();
            task = new Deadline(taskName, dueDate);
            break;
        case EVENT:
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