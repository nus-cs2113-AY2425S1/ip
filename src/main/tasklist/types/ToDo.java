package tasklist.types;

public class ToDo extends Task {
    // Constructor for Todo
    public ToDo(String description) {
        super(description);
    }
    
    /**
     * Returns a string representation of the task.
     * The representation includes the status icon and the task description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString(); // Add [T] tag for to-dos
    }
}
