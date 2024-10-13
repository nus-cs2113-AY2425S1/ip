package quinn.task;

/**
 * Represents a ToDo task in the Quinn task management application.
 * A ToDo task is the simplest form of task, consisting of a description without any specific date or time constraints.
 *
 * This class extends the abstract {@link Task} class and inherits its basic properties and methods.
 * It does not add any additional fields or methods beyond those provided by the parent class.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task with the given description.
     * The task is initially marked as not done.
     *
     * @param description the description of the ToDo task
     */
    public ToDo(String description) {
        super(TaskType.TODO, description);
    }

    /**
     * Constructs a new ToDo task with the given description and completion status.
     * This constructor is primarily used when loading tasks from storage.
     *
     * @param description the description of the ToDo task
     * @param isDone the initial completion status of the task
     */
    public ToDo(String description, boolean isDone) {
        super(TaskType.TODO, description, isDone);
    }
}
