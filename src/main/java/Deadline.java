import exception.InvalidCreateDeadlineException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description) {
        super(description);
    }
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static void createNewDeadline(String userInput) throws InvalidCreateDeadlineException {
        String[] deadlineInfo = userInput.split("/by");

        if (deadlineInfo.length == 2) {
            Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1].strip());
            Aerus.tasks.add(deadline);
            UI.printContent("Added Deadline: " + deadline.toString());
        } else {
            throw new InvalidCreateDeadlineException();
        }

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

    @Override
    public String toSaveString() {
        return "D" + this.getStatusIcon() + "//" + this.description + "//" + this.by;
    }
}
