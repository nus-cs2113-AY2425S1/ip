public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone(){
        isDone = true;
    }
    public void markAdUndone(){
        isDone = false;
    }
    public String toString(){
        return("[" + getStatusIcon() + "] " + description);
    }
    public String saveAsString(){
        return(String.join(" | ", new String[]{description, String.valueOf(isDone)}));
    }
}
