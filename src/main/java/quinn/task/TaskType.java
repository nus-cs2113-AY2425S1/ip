package quinn.task;

/**
 * Enumerates the types of tasks available in the Quinn task management application.
 * This enum defines the different categories of tasks that can be created and managed within the system.
 *
 */
public enum TaskType {
    /**
     * Represents a ToDo task.
     * A ToDo task is a simple task without any specific date or time constraints.
     */
    TODO("T"),

    /**
     * A Deadline task is associated with a specific due date and time.
     */
    DEADLINE("D"),

    /**
     * An Event task is associated with both a start and end date and time.
     */
    EVENT("E");

    /** The single-character abbreviation used to represent the task type. */
    private final String abbreviation;

    /**
     * Constructs a TaskType with the specified abbreviation.
     *
     * @param abbreviation the single-character string used to represent this task type
     */
    TaskType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Retrieves the abbreviation associated with this task type.
     *
     * @return the single-character string abbreviation of this task type
     */
    public String getAbbreviation() {
        return abbreviation;
    }
}
