package tyrone.task;

/**
 * Class to store information about single Todo task
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets a string representation of the Todo task with its status (Done or not done).
     *
     * @return A string in the format "[T][X] description" or "[T][ ] description",
     *         where T indicates it's a Todo task and X indicates a completed task.
     */
    @Override
    public String getNameWithStatus() {
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Gets a string representation of the save record of the todo task.
     *
     * @return A string representation of Todo task to record in save file.
     */
    @Override
    public String getSaveRecord() {
        return (isDone ? "1" : "0") + " todo " + description;
    }
}
