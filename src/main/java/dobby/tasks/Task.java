package dobby.tasks;
public class Task {

    private String description;
    private boolean isDone;
    public static int numberOfTasks;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String toString() {
        if (isDone){
            return "[X] " + description;
        }
        return "[ ] " + description;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public boolean isDone() {
        return isDone;
    }
}
