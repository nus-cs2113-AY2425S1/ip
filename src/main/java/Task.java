public class Task {
    protected String description;
    protected boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusDescription() {
        String status = isMarked ? "X" : " ";
        return String.format("[%s] %s", status, getDescription());
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }
}
