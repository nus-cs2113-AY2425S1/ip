public class Task {

    String taskName;
    boolean isDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void updateCompletion(boolean status) {
        this.isDone = status;
    }

    public String retrieveTaskName() {
        return this.taskName;
    }
}
