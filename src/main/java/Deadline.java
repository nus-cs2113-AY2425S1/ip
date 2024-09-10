public class Deadline extends Task {

    protected String by;

    public Deadline(String description) {
        super(description);
    }
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static void createNewDeadline(String userInput) {
        String[] deadlineInfo = userInput.split("/by");
        Deadline deadline = new Deadline(deadlineInfo[0]);
        if (deadlineInfo.length >= 2) {
            deadline.by = deadlineInfo[1].strip();
        }
        Aerus.tasks[Task.tasksCount-1] = deadline;
        UI.printContent("Added Deadline: " + deadline.toString());
    }

    @Override
    public void printMark() {
        UI.printContent("Nice! You have done this deadline:\n\t" + this.toString());
    }

    @Override
    public void printUnmark() {
        UI.printContent("I have unmarked this deadline:\n\t" + this.toString());
    }

    @Override
    public String toString() {
        String output = "[D]" + super.toString();
        if (by != null) {
            output += " (by: " + by + ")";
        }
        return output;
    }
}
