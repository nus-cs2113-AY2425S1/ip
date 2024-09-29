package task;

public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    /**
     * Retrieves the task type marker for ToDo tasks.
     *
     * @return The type marker for ToDo tasks.
     */
    @Override
    public String getTaskMarker() {
        return "T";
    }

    /**
     * Converts the ToDo task for saving to a file.
     *
     * @return A string representation of the ToDo task in file format.
     */
    @Override
    public String toFileFormat() {
        String status;
        if (isDone) {
            status = "1";
        } else {
            status = "0";
        }
        return "T | " + status + " | " + description;
    }
}
