public class ToDo extends Task {
    /**
     * Creates todo
     * @param description Description of todo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the todo.
     * Includes the Task Type ("T" for ToDo), Status Icon and Description.
     * @return String representation of the todo.
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}