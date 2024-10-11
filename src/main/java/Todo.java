/**
 * The Todo class represents a task that needs to be done.
 * It is a subclass of the Task class, specifically for tasks that don't have deadlines or events.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified task name.
     * The task is initialized as not done by default.
     *
     * @param taskName The name of the todo task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a new Todo task with the specified completion status and formatted task information.
     *
     * @param isDone           Boolean value indicating whether the task is done.
     * @param formattedTaskInfo A string representing the formatted task information.
     */
    public Todo(boolean isDone, String formattedTaskInfo) {
        super(isDone, formattedTaskInfo);
    }

    /**
     * Returns the string representation of the Todo task, including its type and completion status.
     * The format includes a "[T]" to indicate the task is a Todo, followed by a "[X]" if the task is done,
     * or "[ ]" if it is not done.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        String taskStatus = "[T][" + (isDone ? "X" : " ") + "] ";
        if (formattedTaskInfo.isBlank()) {
            return taskStatus + taskName;
        }
        return taskStatus + formattedTaskInfo;
    }
}
