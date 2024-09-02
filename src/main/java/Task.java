public class Task {
    private final String contents;
    private boolean isDone;

    public Task(String contents) {
        this.contents = contents;
        this.isDone = false;
    }

    public void markDone(){
        this.isDone = true;
    }

    public void unmarkDone(){
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "X":" ");
    }

    public String getContents(){
        return contents;
    }
}
