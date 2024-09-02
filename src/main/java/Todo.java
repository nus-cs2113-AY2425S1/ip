public class Todo extends Task {
    /**
     * Constructs a new Task with the given description.
     *
     * @param description Describes the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public void printTask() {
        System.out.print("Got it. I've added this task:" + System.lineSeparator() + "  ");
        System.out.println("[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDescription());
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }
}
