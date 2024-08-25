public class Task {
    private String description;
    private boolean isDone;
    private int id;

    public Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void markDone() {
        this.isDone = true;
    }

    public void markDel() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getId() {
        return id;
    }

}
