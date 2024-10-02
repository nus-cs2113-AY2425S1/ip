package freedom.tasks;

import freedom.ui.UiTask;

public class Task {
    protected static final UiTask ui = new UiTask();

    protected String description;
    protected boolean isDone;
    protected static final String LOGO = "\t________________________________________\n";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() throws Exception {
        try {
            if (isDone) {
                throw new Exception();
            }
            this.isDone = true;
        } catch (Exception e) {
            ui.printAlreadyDone();
            throw new Exception();
        }
    }

    public void markUndone() throws Exception {
        try {
            if (!isDone) {
                throw new Exception();
            }
            this.isDone = false;
        } catch (Exception e) {
            ui.printAlreadyUndone();
            throw new Exception();
        }
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    public String getDescription() {
        return description;
    }

    public String generateTaskLine() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public String generateStorageLine() {
        return getStatusIcon() + " | " + getDescription();
    }

    public String getDoneBy() {
        return "";
    }

    public String getFrom() {
        return "";
    }

    public String getTo() {
        return "";
    }
}
