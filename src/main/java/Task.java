public class Task {
    private String line;
    private boolean isDone;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String line, boolean isDone) {
        this.line = line;
        this.isDone = isDone;
    }

    public Task(String line) {
        this.line = line;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone? "X" : " ") + "]"
                + line;
    }
}
