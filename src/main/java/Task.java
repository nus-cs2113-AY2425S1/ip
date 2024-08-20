public class Task {
    private final String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "X":" ");
    }

    public String getContent(){
        return content;
    }
}
