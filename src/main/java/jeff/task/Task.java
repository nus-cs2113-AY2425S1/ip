package jeff.task;

public class Task {

    protected String description;
    private boolean isDone;

    Task(String description) { //Constructor
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() { //Returns X or " " depending on completion status
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getStatusNumber(){ //Returns 1 if task is marked as finished
        return (isDone ? 1 : 0);
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String fileContent(){
        return " | " + getStatusNumber() + " | " + description;
    }

    @Override
    public String toString() {
        return  "[" + getStatusIcon() + "] " + description;
    }
}
