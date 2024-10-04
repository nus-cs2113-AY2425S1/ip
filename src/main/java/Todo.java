public class Todo extends Task {
    /**
     * Represents a to-do task, which is a simple task without any date or time constraints.
     * Extends the Task class to inherit task-related properties and behaviors.
     *
     * @param task the description of the to-do task
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Returns a string representation of the to-do task in the format "[T] task description",
     * including the task description and its completion status.
     *
     * @return a formatted string representing the to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
