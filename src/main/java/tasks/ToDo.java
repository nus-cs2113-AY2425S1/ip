package tasks;

public class ToDo extends Task {

    protected String by;

    /**
     * Returns a new instance of the ToDo object with the given description.
     *
     * @param description description of the ToDo object
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string represenation of the ToDo object
     *
     * @return String representation of the ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
