package Glendon.task;

public class Todo extends Task {

    /**
     * Construct the todo task with given task description with the default completion status
     * of task is always set to false
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs the todo task with the given completion indicator, where 1 indicates that the
     * task is completed and 0 indicates that the task has yet to be completed, given task
     * description and due date
     *
     * @param completionIndicator an integer to show if the task has been completed
     * @param description the description of the task
     */
    public Todo(int completionIndicator, String description) {
        super(description);
        if (completionIndicator == 1) {
            super.isCompleted = true;
        }
    }

    /**
     * Returns the String format of saving a todo task
     *
     * @return the format for saving a todo task
     */
    @Override
    public String saveToFile() {
        return "T|" + super.saveToFile();
    }

    /**
     * Returns a string representation of the task type, [T] representing todo task,
     * task description and completion status of the task
     *
     * @return the task type, task description and completion status of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
