package CassHelpers.types;

public class Task {
    protected String taskName;
    protected boolean isCompleted;

    public Task(String taskName){
        setTaskName(taskName);
        setCompleted(false);
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

    @Override
    public String toString(){
        return ("["+(this.getStatusIcon())+"] "+this.getTaskName());
    }

    public String toWritableString(){
        return ","+(this.isCompleted?1:0)+","+this.getTaskName();
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

}
