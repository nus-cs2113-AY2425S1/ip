public class Task {
    protected String description;
    protected boolean isDone;
    static final String LOGO = "\t________________________________________\n";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
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

    public String printLine() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public void printEmptyDescriptionError() {
        System.out.print(LOGO);
        System.out.println("\tDescription cannot be empty!!");
        System.out.println(LOGO);
    }
}
