public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
        return "[" + (isDone ? "X" : " ") + "] "; // mark done task with X
    }

    public char getType() {
        return 'u';
    }

    public String getFrom() {
        return "";
    }

    public String getTo() {
        return "";
    }

    public String formattedDeadline(){
        return "";
    }

    public String formattedEvent(){
        return "";
    }
}
