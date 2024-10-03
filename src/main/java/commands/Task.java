package commands;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public int getStatus() {
        return isDone ? 1 : 0;
    }

    public String getStatusIcon() {
        return ("[" + (isDone ? "X" : " ") + "]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String createTaskList(){
        return "";
    }

    public String createTaskTxt(){ return "";}
}
