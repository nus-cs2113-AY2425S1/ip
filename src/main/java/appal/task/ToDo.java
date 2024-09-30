package appal.task;

public class ToDo extends Task {
    protected static final String command = "todo";

    /**
     * Class constructor.
     *
     * @param description Description of ToDo Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of a ToDo task printed in the task list,
     * with "[T]" indicating a Todo task, indication of completion and Todo details.
     *
     * @return String representation ToDo Task for printing.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns String representation of a ToDo Task when stored and saved in a text file,
     * including command type, task description and additional information based of type of task.
     *
     * @return String representation of a ToDo Task for saving.
     */
    @Override
    public String getTaskInfo() {
        return command + ", " + super.getTaskInfo();
    }
}