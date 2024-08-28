public class Task {
    private String task;
    private boolean isDone;

    public Task(String contents){
        this.task = contents;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "[X]" : "[ ]");
    }

    public void setStatus(){
        isDone = true;
    }

    public void unsetStatus(){
        isDone = false;
    }

    public String getContents(){
        return task;
    }

    @Override
    public String toString() {
        return this.task;
    }

}
