public class Task {
    protected String description;
    protected boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public String getStatusDescription() {
        String status = " ";
        if (this.isMarked) {
            status = "X";
        }
        return String.format("[%s] %s", status, description);
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }
}
