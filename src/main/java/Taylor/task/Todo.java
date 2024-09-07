package Taylor.task;

/**
 * Represents a Todo task, which is a type of task that only has a description without a deadline or time.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo task with the specified description and completion status.
     *
     * @param description The description of the Todo task.
     * @param isCompleted The completion status of the Todo task (true if completed, false otherwise).
     */
    public Todo(String description, boolean isCompleted){
        super(description, isCompleted);
    }

    /**
     * Returns a string representation of the Todo task, which includes the task type and the description.
     *
     * @return A string in the format "[T][ ] task description" where "[T]" indicates a Todo task and
     *         "[ ]" or "[X]" indicates whether the task is completed or not.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string suitable for saving to a file, representing the Todo task's data.
     *
     * @return A string in the format "T | 0 | task description" where "T" indicates a Todo task,
     *         "0" or "1" indicates whether the task is completed, and the task description follows.
     */
    @Override
    public String write(){
        return "T | " + (isCompleted? "1": "0") + " | " + description;
    }
}