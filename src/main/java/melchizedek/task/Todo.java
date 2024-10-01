package melchizedek.task;

public class Todo extends Task {

    /**
     * Constructor of the Todo class.
     *
     * @param description Todo description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor of the Todo class,
     * mainly used when loading tasks from save file only.
     *
     * @param description Todo description
     * @param isDone Truth value of whether todo has been marked as done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Method to convert todo to the save file format.
     *
     * @return Todo in formatted String
     */
    @Override
    public String taskToFile() {
        return "T | " + super.taskToFile();
    }

    /**
     * Override method to format todo to printable String
     *
     * @return Todo in formatted String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
