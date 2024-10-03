public class Task {
    private boolean done;
    private final String taskInfo;

    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        done = false;
    }

    public boolean getStatus() {
        return done;
    }

    public String getInfo() {
        return taskInfo;
    }

    public void markAsDone() {
        done = true;
    }

    public void markAsNotDone() {
        done = false;
    }

    public void printTask() {
        System.out.printf("[%s] %s", done ? "X" : " ", taskInfo);
    }

    public String convertToSaveFormat() {
        return (done ? "1" : "0") + " | " + taskInfo;

    }

}
