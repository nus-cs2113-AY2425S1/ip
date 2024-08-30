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
        System.out.println("Nice! I've marked this task as done:");
        printTask();
    }

    public void markAsNotDone() {
        done = false;
        System.out.println("OK, I've marked this task as not done yet:");
        printTask();
    }

    public void printTask() {
        System.out.printf("[%s] %s%n", done ? "X" : " ", taskInfo);
    }

}
