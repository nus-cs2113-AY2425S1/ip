package task;

public class Task{
    private String taskName;
    private boolean isMarked;

    public Task() {
        this.taskName = "";
        this.isMarked = false;
    }
    public Task(String taskName, boolean isMarked) {
        this.taskName = taskName;
        this.isMarked = isMarked;
    }
    public String getTaskName() {
        return taskName;
    }
    public boolean isMarked() {
        return isMarked;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    public String toString(){
        if (isMarked){
            return "[X] " + taskName;
        }
        else{
            return "[ ] " + taskName;
        }
    }
}
