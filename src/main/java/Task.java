public class Task {
    private String taskName;
    private boolean isCompleted;

    Task(String taskName){
        this.taskName=taskName;
        this.isCompleted=false;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskName() {
        return this.taskName;
    }

    public String getStatusIcon() {
        return (this.isCompleted ? "X" : " ");
    }

    public String printTask(){
        return ("["+(this.getStatusIcon())+"] "+this.getTaskName());
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

}
