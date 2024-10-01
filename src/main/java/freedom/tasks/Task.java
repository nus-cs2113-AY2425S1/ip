package freedom.tasks;

public class Task {
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
            System.out.print(LOGO);
            System.out.println("\tAlready done :) Time for other things");
            System.out.println(LOGO);
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
            System.out.print(LOGO);
            System.out.println("\tYou have not done it yet? >:(");
            System.out.println(LOGO);
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

    public String printLine() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public String generateStorageLine() {
        return getStatusIcon() + " | " + getDescription();
    }

    public void printEmptyDescriptionError() {
        System.out.print(LOGO);
        System.out.println("\tDescription cannot be empty!!");
        System.out.println(LOGO);
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
