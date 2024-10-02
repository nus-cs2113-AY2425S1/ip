package freedom.tasks;

import freedom.exceptions.DescriptionEmpty;
import freedom.exceptions.TimeEmpty;

public class Deadline extends Task{
    protected String doneBy;

    public Deadline(String description) throws Exception {
        super(description);
        final int DESCRIPTION_INDEX = 0;
        final int DONE_BY_INDEX = 1;

        String[] components = description.split("/by");
        updateDescription(components[DESCRIPTION_INDEX].trim());
        try {
            if (getDescription().isEmpty()) {
                throw new DescriptionEmpty();
            }
            setDoneBy(components[DONE_BY_INDEX].trim());
            if (getDoneBy().isEmpty()) {
                throw new TimeEmpty();
            }
        } catch (DescriptionEmpty e) {
            ui.printEmptyDescriptionError();
            throw new Exception("Description is empty");
        } catch (TimeEmpty | ArrayIndexOutOfBoundsException e) {
            ui.printNoDeadline();
            throw new Exception("No deadline given");
        }
    }

    public Deadline(String description, boolean isDone, String doneBy) {
        super(description);
        this.isDone = isDone;
        setDoneBy(doneBy);
    }

    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public String generateTaskLine() {
        return "[D]" + super.generateTaskLine() + " (by: " + doneBy + ")";
    }

    public String generateStorageLine() {
        return "D | " + super.generateStorageLine() + " | " + getDoneBy() + "\n";
    }
}
