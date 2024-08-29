public class Task {
    private int id;
    private String task;
    private boolean isDone;

    public Task() {
        this.isDone = false;
    }

    public Task(int id, String task) {
        this.id = id;
        this.task = task;
        this.isDone = false;
    }

    public void markTaskDone() {
        isDone = true;
    }

    public void printTask() {
        if (isDone) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
        System.out.println(id + ": " + task);
    }
}
